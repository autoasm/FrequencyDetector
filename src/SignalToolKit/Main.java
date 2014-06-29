/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

/**
 *
 * @author doul
 */
public class Main {
    final static int SAMEPLE_RATE   = 8000;
    final static int FREQUENCY      = 1980;
    final static int SIGNAL_LENGTH  = 200;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	int power = 0;
	int[] signal = new int[SIGNAL_LENGTH];
        
        FrequencyDetector detector = new FrequencyDetector();
        SignalGenerator signalGenerator = new SignalGenerator();
        
        detector.setSampleRate(SAMEPLE_RATE);

	signalGenerator.generateSignal(signal, SIGNAL_LENGTH, FREQUENCY, SAMEPLE_RATE);

	int bandNumber = 100;
	int bandWidth = SAMEPLE_RATE/2/bandNumber;

	for(int i = 0; i < bandNumber; i++) {
		int frequency = bandWidth * i;
		power = detector.detectFrequency(signal, 0, SIGNAL_LENGTH, frequency);

		System.out.println("Power of Frequency " + frequency +" : " + power);
	}
    }    
}
