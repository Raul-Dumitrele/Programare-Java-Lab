package teste;

import PB2.PerecheNumere;
import exemplul2.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPB2 {
    @Test
    void testGetSet() {
        PerecheNumere p = new PerecheNumere(2, 5);
        assertEquals(2, p.getNr1());
        assertEquals(5, p.getNr2());

        p.setNr1(10);
        p.setNr2(20);
        assertEquals(10, p.getNr1());
        assertEquals(20, p.getNr2());
    }

    @Test
    void testToString() {
        PerecheNumere p = new PerecheNumere(7, 9);
        assertTrue(p.toString().contains("nr1=7"));
        assertTrue(p.toString().contains("nr2=9"));
    }

    @Test
    void testFibonaciTrue() {
        assertTrue(PerecheNumere.Fibonaci(3, 5));
    }

    @Test
    void testFibonaciFalse() {
        assertFalse(PerecheNumere.Fibonaci(4, 6));
    }

    @Test
    void testCmmmcCommonDivisor() {
        assertEquals(2, PerecheNumere.cmmmc(6, 8));
    }

    @Test
    void testCmmmcNoCommonDivisor() {
        assertEquals(0, PerecheNumere.cmmmc(7, 9));
    }

    @Test
    void testSumaCifrelorTrue() {
        assertTrue(PerecheNumere.SumaCifrelor(123, 114));
    }

    @Test
    void testSumaCifrelorFalse() {
        assertFalse(PerecheNumere.SumaCifrelor(123, 789));
    }

    @Test
    void testCifrePareTrue() {
        assertTrue(PerecheNumere.CifrePare(246, 824));
    }

    @Test
    void testCifrePareFalse() {
        assertFalse(PerecheNumere.CifrePare(123, 456));
    }
}

