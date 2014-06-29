/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

/**
 *
 * @author doul
 */
public class SignalUtil {
    
    public byte averageLevel(byte[] samples, int startPos, int numOfSample) {
        byte level = 0;
        long total = 0;
        
        for(int i = startPos; i < startPos + numOfSample; i++) {
            total = total + samples[i];
        }
        level = (byte)(total/numOfSample);
        
        
        return level;
    }
    
    public boolean convert8BitsTo16Bits(byte[] samples8Bits, int[] sample16Bits, int startPos, int numOfSample) {
        boolean res = true;
        
        for(int i = startPos; i < startPos + numOfSample; i++) {
            sample16Bits[i] = samples8Bits[i];
        }
        
        return res;
    }
    
}
