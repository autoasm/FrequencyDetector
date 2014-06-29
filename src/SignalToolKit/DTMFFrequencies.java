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
public class DTMFFrequencies {
    
    public final static int INVALID_FREQUENCIES = 0;
    
    public static final String INVALID_DTMF_KEY   = "?";
    
    private static DTMFFrequencies frequencies = null;
    
    public final static int[] HIGH_FREQUENCIES = {
        1209,
        1336,
        1477,
        1633
    };
    
    public final static int[] LOW_FREQUENCIES = {
        697,
        770,
        852,
        941
    };
    
    private final static Object[] frequency_1 = new Object[] {
        new Integer(LOW_FREQUENCIES[0]),
        new Integer(HIGH_FREQUENCIES[0])
    };
    
    private final static Object[] frequency_2 = new Object[] {
        new Integer(LOW_FREQUENCIES[0]),
        new Integer(HIGH_FREQUENCIES[1])
    };    
    
    private final static Object[] frequency_3 = new Object[] {
        new Integer(LOW_FREQUENCIES[0]),
        new Integer(HIGH_FREQUENCIES[2])
    };   
    
    private final static Object[] frequency_A = new Object[] {
        new Integer(LOW_FREQUENCIES[0]),
        new Integer(HIGH_FREQUENCIES[3])
    };  
    
    private final static Object[] frequency_4 = new Object[] {
        new Integer(LOW_FREQUENCIES[1]),
        new Integer(HIGH_FREQUENCIES[0])
    };  
    
    private final static Object[] frequency_5 = new Object[] {
        new Integer(LOW_FREQUENCIES[1]),
        new Integer(HIGH_FREQUENCIES[1])
    };
    
    private final static Object[] frequency_6 = new Object[] {
        new Integer(LOW_FREQUENCIES[1]),
        new Integer(HIGH_FREQUENCIES[2])
    };  
    
    private final static Object[] frequency_B = new Object[] {
        new Integer(LOW_FREQUENCIES[1]),
        new Integer(HIGH_FREQUENCIES[3])
    };   
    
    private final static Object[] frequency_7 = new Object[] {
        new Integer(LOW_FREQUENCIES[2]),
        new Integer(HIGH_FREQUENCIES[0])
    };   
    
    private final static Object[] frequency_8 = new Object[] {
        new Integer(LOW_FREQUENCIES[2]),
        new Integer(HIGH_FREQUENCIES[1])
    };
    
    private final static Object[] frequency_9 = new Object[] {
        new Integer(LOW_FREQUENCIES[2]),
        new Integer(HIGH_FREQUENCIES[2])
    }; 
    
    private final static Object[] frequency_C = new Object[] {
        new Integer(LOW_FREQUENCIES[2]),
        new Integer(HIGH_FREQUENCIES[3])
    };     
    
    private final static Object[] frequency_STAR = new Object[] {
        new Integer(LOW_FREQUENCIES[3]),
        new Integer(HIGH_FREQUENCIES[0])
    };
    
    private final static Object[] frequency_0 = new Object[] {
        new Integer(LOW_FREQUENCIES[3]),
        new Integer(HIGH_FREQUENCIES[1])
    };    
    
    private final static Object[] frequency_SHARP = new Object[] {
        new Integer(LOW_FREQUENCIES[3]),
        new Integer(HIGH_FREQUENCIES[2])
    };
    
    private final static Object[] frequency_D = new Object[] {
        new Integer(LOW_FREQUENCIES[3]),
        new Integer(HIGH_FREQUENCIES[3])
    };     
    
    private Map<String, Object[]> dtmfFrequencies = new HashMap<String, Object[]>();
    
    private String[][] keyMap = new String[4][4];

    private DTMFFrequencies() {
        init();
    }
    
    protected void init() {
        dtmfFrequencies.put("1", frequency_1);
        dtmfFrequencies.put("2", frequency_2);
        dtmfFrequencies.put("3", frequency_3);
        dtmfFrequencies.put("A", frequency_A);
        
        dtmfFrequencies.put("4", frequency_4);
        dtmfFrequencies.put("5", frequency_5);
        dtmfFrequencies.put("6", frequency_6);
        dtmfFrequencies.put("B", frequency_B);  
        
        dtmfFrequencies.put("7", frequency_7);
        dtmfFrequencies.put("8", frequency_8);
        dtmfFrequencies.put("9", frequency_9);
        dtmfFrequencies.put("C", frequency_C);   
        
        dtmfFrequencies.put("*", frequency_STAR);
        dtmfFrequencies.put("0", frequency_0);
        dtmfFrequencies.put("#", frequency_SHARP);
        dtmfFrequencies.put("D", frequency_D);  
        
        keyMap[0][0] = "1";
        keyMap[0][1] = "2";
        keyMap[0][2] = "3";
        keyMap[0][3] = "A";
        
        keyMap[1][0] = "4";
        keyMap[1][1] = "5";
        keyMap[1][2] = "6";
        keyMap[1][3] = "B";     
        
        keyMap[2][0] = "7";
        keyMap[2][1] = "8";
        keyMap[2][2] = "9";
        keyMap[2][3] = "C";      
        
        keyMap[3][0] = "*";
        keyMap[3][1] = "0";
        keyMap[3][2] = "#";
        keyMap[3][3] = "D";        
    }
    
    public static DTMFFrequencies getDTMFFrequencies() {
        if(null == frequencies) {
            frequencies = new DTMFFrequencies();
        }
        return frequencies;
    }
    
    public Integer getHighFrequency(String key) {
        int res = INVALID_FREQUENCIES;
        
        Object[] keyFrequencys = dtmfFrequencies.get(key);
        if(keyFrequencys != null) {
            res = (Integer)keyFrequencys[1];
        }
        
        return res;
    }
    
    public Integer getLowFrequency(String key) {
        int res = INVALID_FREQUENCIES;
        
        Object[] keyFrequencys = dtmfFrequencies.get(key);
        if(keyFrequencys != null) {
            res = (Integer)keyFrequencys[0];
        }
        
        return res;        
    }
    
    public String getKey(int highFrequencyId, int lowFrequencyId) {
        
        String res = INVALID_DTMF_KEY;
        if((highFrequencyId >= 0 && highFrequencyId <= 3) && 
            (lowFrequencyId >= 0 && lowFrequencyId <= 3)) {
                res = keyMap[lowFrequencyId][highFrequencyId];
        }
        
        return res;
    }
}
