/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalToolKit;

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
public class SignalUtilTest {
    
    public SignalUtilTest() {
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
     * Test of averageLevel method, of class SignalUtil.
     */
    @Test
    public void testAverageLevel() {
        System.out.println("averageLevel");
        byte[] samples1 = new byte[] {
            0,
            0,
            0,
            0,
            0,
            0
        };
        
        byte[] samples2 = new byte[] {
            127,
            127,
            127,
            127,
            127,
            127
        };
        
        byte[] samples3 = new byte[] {
            0,
            127,
            0,
            127,
            0,
            127
        };    
        
        byte[] samples4 = new byte[] {
            0,
            10,
            0,
            10,
            0,
            10
        }; 
        
        byte[] samples5 = new byte[] {
            0,
            10,
            0,
            10,
            0,
            20
        };         
        
        int startPos = 0;
        int numOfSample = samples1.length;
        SignalUtil instance = new SignalUtil();
        byte expResult = 0;
        byte result = instance.averageLevel(samples1, startPos, numOfSample);
        assertEquals(expResult, result);
        
        numOfSample = samples2.length;
        result = instance.averageLevel(samples2, startPos, numOfSample);
        assertEquals(127, result);      
        
        numOfSample = samples3.length;
        result = instance.averageLevel(samples3, startPos, numOfSample);
        assertEquals(63, result);     
        
        numOfSample = samples4.length;
        result = instance.averageLevel(samples4, startPos, numOfSample);
        assertEquals(5, result); 
        
        numOfSample = 2;
        result = instance.averageLevel(samples5, 4, numOfSample);
        assertEquals(10, result);         
        
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
