/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author doul
 */
public class DTMFGenerator {
    
    private DTMFFrequencies dtmfTable = null;
    
    public final static int INVALID_FREQUENCIES = 0;
    public final static int SAMPLE_RATE_8K = 8000;

   
    private SignalGenerator signalGenerator = new SignalGenerator();
    
    public DTMFGenerator() {
        dtmfTable = DTMFFrequencies.getDTMFFrequencies();
    }
    
    public int getDTMFSignal(String key, byte[] samples, int startPos, int numOfSample) {
        
        int res = 0;
        
        Integer highFrequency = dtmfTable.getHighFrequency(key);
        Integer lowFrequency = dtmfTable.getLowFrequency(key);
        
        if(null != highFrequency && null != lowFrequency) {
            signalGenerator.generateSignalInBytes(samples, numOfSample, highFrequency.intValue(), lowFrequency.intValue(), SAMPLE_RATE_8K);
        }
        
        return res;
    }    
}
