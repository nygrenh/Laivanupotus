/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henrik
 */
public class LogiikkaTest {
    
    public LogiikkaTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of silmukka method, of class Logiikka.
     */
    @Test
    public void testSilmukka() {
        System.out.println("silmukka");
        Logiikka instance = new Logiikka();
        instance.silmukka();
        fail("The test case is a prototype.");
    }
}