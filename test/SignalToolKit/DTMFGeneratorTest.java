/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

import org.junit.Test;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author doul
 */
public class DTMFGeneratorTest {
    
    public DTMFGeneratorTest() {
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

    @Test
    public void testGetDTMFSignal1() {
        int numOfSample = 200;
        byte[] signals = new byte[numOfSample];
        int highFreqencyInHz = 1209;
        int lowFreqencyInHz = 697;
        int sampleRateInHz = 8000;
        DTMFGenerator instance = new DTMFGenerator();
        int expResult = numOfSample;
        //int result = instance.getDTMFSignal(signals, numOfSample, highFreqencyInHz, lowFreqencyInHz, sampleRateInHz);
        int result = instance.getDTMFSignal("1", signals, 0, numOfSample);
        assertEquals(0, result);
        // TODO review the generated test code and remove the default call to fail.
        
        FrequencyDetector detector = new FrequencyDetector();
        SignalUtil util = new SignalUtil();
        
        int sample16Bits[] = new int[numOfSample];
        
        util.convert8BitsTo16Bits(signals, sample16Bits, 0, numOfSample);
        int powerOfLowFreq = detector.detectFrequency(sample16Bits, 0, numOfSample, lowFreqencyInHz);
        int powerOfHighFreq = detector.detectFrequency(sample16Bits, 0, numOfSample, highFreqencyInHz);
        int powerOf50Hz = detector.detectFrequency(sample16Bits, 0, numOfSample, 50);
        int powerOf135Hz = detector.detectFrequency(sample16Bits, 0, numOfSample, 135);
        int powerOf2000Hz = detector.detectFrequency(sample16Bits, 0, numOfSample, 2000);
        int powerOf3190Hz = detector.detectFrequency(sample16Bits, 0, numOfSample, 3190);
        
        Assert.assertTrue(powerOfHighFreq > 100*powerOf50Hz);
        Assert.assertTrue(powerOfLowFreq > 100*powerOf50Hz);
        
        Assert.assertTrue(powerOfHighFreq > 100*powerOf135Hz);
        Assert.assertTrue(powerOfLowFreq > 100*powerOf135Hz);  
        
        Assert.assertTrue(powerOfHighFreq > 100*powerOf2000Hz);
        Assert.assertTrue(powerOfLowFreq > 100*powerOf2000Hz);   
        
        Assert.assertTrue(powerOfHighFreq > 100*powerOf3190Hz);
        Assert.assertTrue(powerOfLowFreq > 100*powerOf3190Hz);         
    }
    
    @Test
    public void testGetDTMFSignal9() {
        int numOfSample = 200;
        byte[] signals = new byte[numOfSample];
        int highFreqencyInHz = 1477;
        int lowFreqencyInHz = 852;
        int sampleRateInHz = 8000;
        DTMFGenerator instance = new DTMFGenerator();
        int expResult = numOfSample;
        //int result = instance.getDTMFSignal(signals, numOfSample, highFreqencyInHz, lowFreqencyInHz, sampleRateInHz);
        int result = instance.getDTMFSignal("9", signals, 0, numOfSample);
        assertEquals(0, result);
        // TODO review the generated test code and remove the default call to fail.
        
        FrequencyDetector detector = new FrequencyDetector();
        SignalUtil util = new SignalUtil();
        
        int sample16Bits[] = new int[numOfSample];
        
        util.convert8BitsTo16Bits(signals, sample16Bits, 0, numOfSample);
        int powerOfLowFreq = detector.detectFrequency(sample16Bits, 0, numOfSample, lowFreqencyInHz);
        int powerOfHighFreq = detector.detectFrequency(sample16Bits, 0, numOfSample, highFreqencyInHz);
        int powerOf50Hz = detector.detectFrequency(sample16Bits, 0, numOfSample, 50);
        int powerOf135Hz = detector.detectFrequency(sample16Bits, 0, numOfSample, 135);
        int powerOf2000Hz = detector.detectFrequency(sample16Bits, 0, numOfSample, 2000);
        int powerOf3190Hz = detector.detectFrequency(sample16Bits, 0, numOfSample, 3190);
        
        Assert.assertTrue(powerOfHighFreq > 100*powerOf50Hz);
        Assert.assertTrue(powerOfLowFreq > 100*powerOf50Hz);
        
        Assert.assertTrue(powerOfHighFreq > 100*powerOf135Hz);
        Assert.assertTrue(powerOfLowFreq > 100*powerOf135Hz);  
        
        Assert.assertTrue(powerOfHighFreq > 100*powerOf2000Hz);
        Assert.assertTrue(powerOfLowFreq > 100*powerOf2000Hz);   
        
        Assert.assertTrue(powerOfHighFreq > 100*powerOf3190Hz);
        Assert.assertTrue(powerOfLowFreq > 100*powerOf3190Hz);         
    }    
}
