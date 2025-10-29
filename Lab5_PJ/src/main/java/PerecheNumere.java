import java.util.List;

public class PerecheNumere {
    private int nr1;
    private int nr2;

    public int getNr1() {
        return nr1;
    }

    public void setNr1(int nr1) {
        this.nr1 = nr1;
    }

    public int getNr2() {
        return nr2;
    }

    public void setNr2(int nr2) {
        this.nr2 = nr2;
    }

    public PerecheNumere(int nr1, int nr2) {
        this.nr1 = nr1;
        this.nr2 = nr2;
    }

    public PerecheNumere() {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    static boolean Fibonaci(int nr1, int nr2) {
        int x1 = 1, x2 = 1, x3;
        while (x1 <= nr1) {
            if (nr1 == x1)
                if (nr2 == x2) {
                    return true;
                }
            x3 = x1 + x2;
            x1 = x2;
            x2 = x3;

        }
        return false;
    }

    static int cmmmc(int nr1, int nr2) {
        for (int i = 2; i <= (nr1 + nr2) / 2; i++) {
            if (((nr1 % i) == 0) && ((nr2 % i) == 0))
                return i;
        }
        return 0;
    }

    static boolean SumaCifrelor(int nr1, int nr2) {
        int scif1 = 0, scif2 = 0;
        while (nr1 != 0) {
            scif1 += nr1 % 10;
            nr1 /= 10;
        }
        while (nr2 != 0) {
            scif2 += nr2 % 10;
            nr2 /= 10;
        }
        if (scif1 == scif2)
            return true;
        return false;
    }

    static boolean CifrePare(int nr1, int nr2) {
        int pcif1 = 0, pcif2 = 0;
        while (nr1 != 0) {
            if ((nr1 % 10) % 2 == 0)
                pcif1 += 1;
            nr1 /= 10;
        }
        while (nr2 != 0) {
            if ((nr2 % 10) % 2 == 0)
                pcif2 += 1;
            nr2 /= 10;
        }
        if (pcif1 == pcif2) {
            return true;
        }
        return false;
    }
}
