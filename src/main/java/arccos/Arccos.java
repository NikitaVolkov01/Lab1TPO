package arccos;

import static java.lang.Double.NaN;

public class Arccos {

    private final double ACCURATE = 0.001;

    public double getAccurate() {
        return this.ACCURATE;
    }

    private double arccosTailor(double x, int n) {
        double result = Math.PI / 2 - x;
        double chisl = 1;
        double znam = 2;
        for (int i = 2; i <= n; i++) {
            result -= Math.pow(x, 2 * i - 1) * chisl / (znam * (2 * i - 1));
            chisl *= (2 * i - 1);
            znam *= (2 * i);
        }
        return result;
    }

    public double arccos(double x) {
        double prev = 0;
        double curr = 2;
        int n = 0;
        if (Math.abs(x) > 1) return NaN;
        if (Math.abs(x) == 1) return 0.0;

        while (Math.abs(curr - prev) >= ACCURATE) {
            prev = curr;
            curr = arccosTailor(x, ++n);
        }
        return Math.ceil(curr * Math.pow(10, 2)) / Math.pow(10, 2);
    }

}