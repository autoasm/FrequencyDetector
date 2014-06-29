/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

/**
 *
 * @author doul
 */
public class DTMFDetector {

    /**
     * Frequencies of noise. Detector calculate average noise power from these
     * frequency points.
     */
    private static final int[] NOISE_FREQENCIES = new int[] {
        2150,
        2317,
        3107,
        326,
        276
    };
    
    private static final int SNR_HIGH              = 100;
    private static final int SNR_MED              = 60;
    private static final int SNR_LOW              = 15;
    
    public static enum Sensitivity {
        HIGH,
        MED,
        LOW
    };
    
    
    private DTMFFrequencies dtmfTable = null;
    
    private Sensitivity sensitivity = Sensitivity.MED;
    
    private FrequencyDetector detector = new FrequencyDetector();
    
    public DTMFDetector() {
        dtmfTable = DTMFFrequencies.getDTMFFrequencies();
    }
    
    public Sensitivity getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(Sensitivity sensitivity) {
        this.sensitivity = sensitivity;
    }
    
    protected int getSignalToNoiseRatio() {
        int res = SNR_MED;
        
        // If the detector work in high sensitivity mode, the reference gain should be low
        if(Sensitivity.HIGH == sensitivity) {
            res = SNR_LOW;
        }
        // If the detector work in low sensitivity mode, the reference gain should be low
        else if(Sensitivity.LOW == sensitivity) {
            res = SNR_HIGH;
        }
        
        return res;
    }
    
    protected int getMaxPowerId(int[] powerOfDTMFFrequencies) {
        int maxId = 0;
        
        int maxValue = powerOfDTMFFrequencies[maxId];
        for(int i = 0; i < powerOfDTMFFrequencies.length; i++) {
            if(maxValue < powerOfDTMFFrequencies[i]) {
                maxValue = powerOfDTMFFrequencies[i];
                maxId = i;
            }
        }
        
        return maxId;
    } 
    
    protected boolean isValidSignal(int power, int noise) {
        boolean res = false;
        
        int snr = getSignalToNoiseRatio();
        if(power >= snr * noise) {
            res = true;
        }
        
        return res;
    }
    
    protected int getNoisePower(int[] samples, int startPos, int numOfSample) {
        
        long powerSum = 0;
        
        for(int frequency: NOISE_FREQENCIES) {
            powerSum = powerSum +  detector.detectFrequency(samples, startPos, numOfSample, frequency);
        }
        int averagePower = (int)((float)powerSum/(float)(NOISE_FREQENCIES.length));
        
        return averagePower;
    }
    
    String decodeDTMFSignal(int[] samples, int startPos, int numOfSample) {
        
        int[] powerOfDtmfFreqHigh   = new int[4];
        int[] powerOfDtmfFreqLow    = new int[4];  
        
        String res = DTMFFrequencies.INVALID_DTMF_KEY;
        int[] dtmfHighFrequencies   = DTMFFrequencies.HIGH_FREQUENCIES;
        int[] dtmfLowFrequencies    = DTMFFrequencies.LOW_FREQUENCIES;        
        
        // Get average noise power
        int noisePower = getNoisePower(samples, startPos, numOfSample);
        
        // Get power of DTMF frequency
        for(int i = 0; i<dtmfHighFrequencies.length; i++) {
            powerOfDtmfFreqHigh[i] = detector.detectFrequency(samples, startPos, numOfSample, dtmfHighFrequencies[i]);
            powerOfDtmfFreqLow[i] = detector.detectFrequency(samples, startPos, numOfSample, dtmfLowFrequencies[i]);
        }
        
        // Get frequency id
        int highFrequencyId = getMaxPowerId(powerOfDtmfFreqHigh);
        int lowFrequencyId = getMaxPowerId(powerOfDtmfFreqLow);
        
        // The DTMF power should be much more larger than reference frequency
        boolean highFrequencyIsValid = isValidSignal(powerOfDtmfFreqHigh[highFrequencyId], noisePower);
        boolean lowFrequencyIsValid = isValidSignal(powerOfDtmfFreqLow[lowFrequencyId], noisePower);
        
        // Get DTMF key by tone
        if(highFrequencyIsValid && lowFrequencyIsValid) {
            res = dtmfTable.getKey(highFrequencyId, lowFrequencyId);
        }
        
        return res;
    }
    
}
