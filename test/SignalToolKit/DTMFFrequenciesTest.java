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
public class DTMFFrequenciesTest {
    
    public DTMFFrequenciesTest() {
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
     * Test of getDTMFFrequencies method, of class DTMFFrequencies.
     */
    @Test
    public void testGetDTMFFrequencies() {
        System.out.println("getDTMFFrequencies");
        DTMFFrequencies result = DTMFFrequencies.getDTMFFrequencies();
        Assert.assertNotNull(result);
    }

    /**
     * Test of getHighFrequency method, of class DTMFFrequencies.
     */
    @Test
    public void testGetHighFrequency() {
        System.out.println("getHighFrequency");

        Object[][] testcases = new Object[][] {
            new Object[] {
                "1",
                new Integer(1209)
            },
            new Object[] {
                "2",
                new Integer(1336)
            },
            new Object[] {
                "3",
                new Integer(1477)
            },
            new Object[] {
                "A",
                new Integer(1633)
            },      
            
            new Object[] {
                "4",
                new Integer(1209)
            },
            new Object[] {
                "5",
                new Integer(1336)
            },
            new Object[] {
                "6",
                new Integer(1477)
            },
            new Object[] {
                "B",
                new Integer(1633)
            },     
            
            new Object[] {
                "7",
                new Integer(1209)
            },
            new Object[] {
                "8",
                new Integer(1336)
            },
            new Object[] {
                "9",
                new Integer(1477)
            },
            new Object[] {
                "C",
                new Integer(1633)
            },    
            
            new Object[] {
                "*",
                new Integer(1209)
            },
            new Object[] {
                "0",
                new Integer(1336)
            },
            new Object[] {
                "#",
                new Integer(1477)
            },
            new Object[] {
                "D",
                new Integer(1633)
            },              
        };
        
        DTMFFrequencies instance = DTMFFrequencies.getDTMFFrequencies();
        
        for(Object[] testcase : testcases) {
            
            String key = (String)testcase[0];
            Integer expResult = (Integer)testcase[1];
            Integer result = instance.getHighFrequency(key);
            assertEquals(expResult, result);            
        }        
    }

    /**
     * Test of getLowFrequency method, of class DTMFFrequencies.
     */
    @Test
    public void testGetLowFrequency() {
        System.out.println("getLowFrequency");

        Object[][] testcases = new Object[][] {
            new Object[] {
                "1",
                new Integer(697 )
            },
            new Object[] {
                "2",
                new Integer(697 )
            },
            new Object[] {
                "3",
                new Integer(697 )
            },
            new Object[] {
                "A",
                new Integer(697 )
            },      
            
            new Object[] {
                "4",
                new Integer(770 )
            },
            new Object[] {
                "5",
                new Integer(770 )
            },
            new Object[] {
                "6",
                new Integer(770 )
            },
            new Object[] {
                "B",
                new Integer(770 )
            },     
            
            new Object[] {
                "7",
                new Integer(852 )
            },
            new Object[] {
                "8",
                new Integer(852 )
            },
            new Object[] {
                "9",
                new Integer(852 )
            },
            new Object[] {
                "C",
                new Integer(852 )
            },    
            
            new Object[] {
                "*",
                new Integer(941 )
            },
            new Object[] {
                "0",
                new Integer(941 )
            },
            new Object[] {
                "#",
                new Integer(941 )
            },
            new Object[] {
                "D",
                new Integer(941 )
            },              
        };
        
        DTMFFrequencies instance = DTMFFrequencies.getDTMFFrequencies();
        
        for(Object[] testcase : testcases) {
            
            String key = (String)testcase[0];
            Integer expResult = (Integer)testcase[1];
            Integer result = instance.getLowFrequency(key);
            assertEquals(expResult, result);            
        }        
    }
    
    public void testGetKey() {
        String[][] keyMap = new String[4][4];
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
        
        DTMFFrequencies instance = DTMFFrequencies.getDTMFFrequencies();
        
        for(int low = 0; low < 4; low++) {
            for(int high = 0; high < 4; high++) {
                Assert.assertEquals(keyMap[low][high], instance.getKey(high, low));
            }
        }
        
        Assert.assertEquals("?", instance.getKey(-1, 3));
        Assert.assertEquals("?", instance.getKey(0, 4));
    }
}
