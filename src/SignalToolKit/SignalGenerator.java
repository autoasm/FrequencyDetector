/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

/**
 *
 * @author doul
 */
public class SignalGenerator {

    private int constantPower = 0;
    private int frequencyPower = 64;

    public int getConstantPower() {
        return constantPower;
    }

    public void setConstantPower(int constantPower) {
        this.constantPower = constantPower;
    }

    public int getFrequencyPower() {
        return frequencyPower;
    }

    public void setFrequencyPower(int frequencyPower) {
        this.frequencyPower = frequencyPower;
    }

    /**
     * Generate test signal
     * @param signals 
     * @param numOfSample
     * @param freqencyInHz
     * @param sampleRateInHz
     * @return 
     */
    int generateSignal(int signals[], int numOfSample, int freqencyInHz, int sampleRateInHz) {

        int i = 0;
        float normalizedFrequency = (float) freqencyInHz / (float) sampleRateInHz;

        for (i = 0; i < numOfSample; i++) {
            float radian = (float) i * (float) 2 * (float) Math.PI * (float) normalizedFrequency;
            float j = (float) ((float) frequencyPower * Math.sin(radian));
            signals[i] = constantPower + (int) j;
        }

        return 0;

        //sample(n) = 128 + 63*sin(n*2*pi*f1/8000) + 63*sin(n*2*pi*f2/8000)
    }

    int generateSignalInBytes(byte signals[], int numOfSample, int freqencyInHz, int sampleRateInHz) {

        int i = 0;
        float normalizedFrequency = (float) freqencyInHz / (float) sampleRateInHz;

        for (i = 0; i < numOfSample; i++) {
            float radian = (float) i * (float) 2 * (float) Math.PI * (float) normalizedFrequency;
            float j = (float) ((float) frequencyPower * Math.sin(radian));
            signals[i] = (byte) (constantPower + (byte) j);
        }

        return 0;

        //sample(n) = 128 + 63*sin(n*2*pi*f1/8000) + 63*sin(n*2*pi*f2/8000)
    }

    //TODO Further test
    int generateSignalInBytes(byte signals[], int numOfSample, int highFreqencyInHz, int lowFreqencyInHz, int sampleRateInHz) {

        float normalizedFrequencyHigh = (float) highFreqencyInHz / (float) sampleRateInHz;
        float normalizedFrequencyLow = (float) lowFreqencyInHz / (float) sampleRateInHz;

        for (int i = 0; i < numOfSample; i++) {
            float radianHigh = (float) i * (float) 2 * (float) Math.PI * (float) normalizedFrequencyHigh;
            float radianLow = (float) i * (float) 2 * (float) Math.PI * (float) normalizedFrequencyLow;
            float j = (float) (((float) frequencyPower * Math.sin(radianHigh)) + ((float) frequencyPower * Math.sin(radianLow)));
            signals[i] = (byte) (constantPower + (byte) j);
        }

        return numOfSample;
    }
}
