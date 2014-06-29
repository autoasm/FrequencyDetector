/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

/**
 * @class FrequencyDetector
 * FrequencyDetector class detect power of specified frequency with Goertzel algorithm.
 * @see http://en.wikipedia.org/wiki/Goertzel_algorithm
 * @author doul
 */
public class FrequencyDetector {

    /// Default sample rate
    private final static int DEFAULT_SAMEPLE_RATE = 8000;
    /// Sample rate of signal
    private int sampleRate = DEFAULT_SAMEPLE_RATE;

    // Get sample rate
    public int getSampleRate() {
        return sampleRate;
    }

    // Set sample rate
    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    /**
     * Detect power of specified frequency
     * @param signals Sample data of signal
     * @param startPos Start position of the signals
     * @param numOfSample NUmber of samples
     * @param frequencyInHz Specified frequency in HZ
     * @return Power of the specified frequency.
     */
    int detectFrequency(int signals[], int startPos, int numOfSample, int frequencyInHz) {

        float power = 0;
        int i = 0;

        float s_prev = 0;
        float s_prev2 = 0;
        float s = 0;

        float normalizedFrequency = (float) frequencyInHz / (float) sampleRate;
        float coeff = (float) ((float) 2 * Math.cos(2 * Math.PI * normalizedFrequency));
        for (i = startPos; i < startPos + numOfSample; i++) {
            s = signals[i] + coeff * s_prev - s_prev2;
            s_prev2 = s_prev;
            s_prev = s;
        }

        power = s_prev2 * s_prev2 + s_prev * s_prev - coeff * s_prev2 * s_prev;

        return (int) power;
    }
}
