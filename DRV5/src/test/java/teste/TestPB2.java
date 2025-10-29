package teste;

import PB2.PerecheNumere;
import exemplul2.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPB2 {
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
        assertTrue(PerecheNumere.SumaCifrelor(12, 21));
    }

    @Test
    void testSumaCifrelorFalse() {
        assertFalse(PerecheNumere.SumaCifrelor(12, 34));
    }

    @Test
    void testCifrePareTrue() {
        assertTrue(PerecheNumere.CifrePare(24, 46));
    }

    @Test
    void testCifrePareFalse() {
        assertFalse(PerecheNumere.CifrePare(23, 46));
    }
}

