/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author doul
 */
public class DTMFDetectorTest {
    
    public DTMFDetectorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMaxPowerId method, of class DTMFDetector.
     */
    @Test
    public void testGetMaxPowerId() {
        System.out.println("getMaxPowerId");
        int[] powerOfDTMFFrequencies = new int[] {
            100,
            200,
            300,
            400
        };
        
        int[] powerOfDTMFFrequencies2 = new int[] {
            100,
            5000,
            300,
            400
        };        
        
        int[] powerOfDTMFFrequencies3 = new int[] {
            100,
            5000,
            11300,
            400
        }; 
        
        int[] powerOfDTMFFrequencies4 = new int[] {
            100000,
            5000,
            11300,
            400
        };        
        
        DTMFDetector instance = new DTMFDetector();
        int result = instance.getMaxPowerId(powerOfDTMFFrequencies);
        assertEquals(3, result);
        
        result = instance.getMaxPowerId(powerOfDTMFFrequencies2);
        assertEquals(1, result);
        
        result = instance.getMaxPowerId(powerOfDTMFFrequencies3);
        assertEquals(2, result);       
        
        result = instance.getMaxPowerId(powerOfDTMFFrequencies4);
        assertEquals(0, result);      

    }

    /**
     * Test of isValidSignal method, of class DTMFDetector.
     */
    @Test
    public void testIsValidSignal() {
        System.out.println("isValidSignal");
        DTMFDetector instance = new DTMFDetector();
        boolean expResult = true;
        boolean result = instance.isValidSignal(10000, 50);
        assertEquals(true, result);
        
        result = instance.isValidSignal(10000, 100);
        assertEquals(true, result);
        
        instance.setSensitivity(DTMFDetector.Sensitivity.LOW);
        result = instance.isValidSignal(10000, 101);
        assertEquals(false, result);    
        
        instance.setSensitivity(DTMFDetector.Sensitivity.MED);
        result = instance.isValidSignal(10000, 101);
        assertEquals(true, result);      
        
        instance.setSensitivity(DTMFDetector.Sensitivity.MED);
        result = instance.isValidSignal(10000, 200);
        assertEquals(false, result);         

    }

    @Test
    public void testNonDTMFSignal() {
        for(int high = 1700; high <=5000; high++) {
            for(int low = 300; low <= 500; low++) {
                testNonDTMFFrequencies(high, low);
            }
        }
    }
    
    public void testNonDTMFFrequencies(int highFrequency, int lowFrequency) {
        int numOfSample = 100;
        byte[] signals = new byte[numOfSample];
        int highFreqencyInHz = highFrequency;
        int lowFreqencyInHz = lowFrequency;
        int sampleRateInHz = 8000;
        DTMFDetector instance = new DTMFDetector();
        SignalGenerator generator = new SignalGenerator();
        
        int expResult = numOfSample;
        int result = generator.generateSignalInBytes(signals, numOfSample, highFreqencyInHz, lowFreqencyInHz, sampleRateInHz);
        //int result = instance.getDTMFSignal("9", signals, 0, numOfSample);
        // TODO review the generated test code and remove the default call to fail.
        
        FrequencyDetector detector = new FrequencyDetector();
        SignalUtil util = new SignalUtil();
        
        int sample16Bits[] = new int[numOfSample];
        util.convert8BitsTo16Bits(signals, sample16Bits, 0, numOfSample);
        
        String key = instance.decodeDTMFSignal(sample16Bits, 0, numOfSample);
        Assert.assertEquals("?", key);        
    }
    
    /**
     * Test of decodeDTMFSignal method, of class DTMFDetector.
     */
    @Test
    public void testDecodeDTMFSignal() {
        System.out.println("decodeDTMFSignal");
        
        String[] dtmfKeys = new String[] {
            "1",
            "2",
            "3",
            "A",

            "4",
            "5",
            "6",
            "B",     

            "7",
            "8",
            "9",
            "C",   
        };
        
        for(String key: dtmfKeys) {
            testDecodeDTMFSignalByKeyWithHighSensitive(key);
            testDecodeDTMFSignalByKeyWithLowSensitive(key);
            testDecodeDTMFSignalByKeyWithMedSensitive(key);
        }
    }
    
    public void testDecodeDTMFSignalByKeyWithHighSensitive(String key) {
        int startPos = 0;
        
        int numOfSample = 40;
        byte[] signals = new byte[numOfSample];
        DTMFGenerator dtmfGenerator = new DTMFGenerator();
        DTMFDetector instance = new DTMFDetector();
        
        dtmfGenerator.getDTMFSignal(key, signals, 0, numOfSample);
        
        SignalUtil util = new SignalUtil();
        int sample16Bits[] = new int[numOfSample];
        util.convert8BitsTo16Bits(signals, sample16Bits, 0, numOfSample);        
        
        String expResult = key;
        instance.setSensitivity(DTMFDetector.Sensitivity.HIGH);
        String result = instance.decodeDTMFSignal(sample16Bits, 0, numOfSample);
        Assert.assertEquals(expResult, result);        
    }
    
    public void testDecodeDTMFSignalByKeyWithLowSensitive(String key) {
        int startPos = 0;
        
        int numOfSample = 200;
        byte[] signals = new byte[numOfSample];
        DTMFGenerator dtmfGenerator = new DTMFGenerator();
        DTMFDetector instance = new DTMFDetector();
        
        dtmfGenerator.getDTMFSignal(key, signals, 0, numOfSample);
        
        SignalUtil util = new SignalUtil();
        int sample16Bits[] = new int[numOfSample];
        util.convert8BitsTo16Bits(signals, sample16Bits, 0, numOfSample);        
        
        String expResult = key;
        instance.setSensitivity(DTMFDetector.Sensitivity.LOW);
        String result = instance.decodeDTMFSignal(sample16Bits, 0, numOfSample);
        Assert.assertEquals(expResult, result);        
    }    
    
    public void testDecodeDTMFSignalByKeyWithMedSensitive(String key) {
        int startPos = 0;
        
        int numOfSample = 80;
        byte[] signals = new byte[numOfSample];
        DTMFGenerator dtmfGenerator = new DTMFGenerator();
        DTMFDetector instance = new DTMFDetector();
        
        dtmfGenerator.getDTMFSignal(key, signals, 0, numOfSample);
        
        SignalUtil util = new SignalUtil();
        int sample16Bits[] = new int[numOfSample];
        util.convert8BitsTo16Bits(signals, sample16Bits, 0, numOfSample);        
        
        String expResult = key;
        instance.setSensitivity(DTMFDetector.Sensitivity.MED);
        String result = instance.decodeDTMFSignal(sample16Bits, 0, numOfSample);
        Assert.assertEquals(expResult, result);        
    }       
}
