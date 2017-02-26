package others.algorithm;

/**
 */
public class ApproximatePi {

    public static void main(String[] args) {
        System.out.println(approxPi());
    }

    // Tylor Series: Pi / 4 = 1 - 1/3 + 1/5 - 1/7 + 1/9 ...
    public static double approxPi() {
        double pi = 0;
        for (double i = 0; i < 100000000; i++) {
            if (i % 2 == 0) {
                pi += 1 / (2 * i + 1);
            } else {
                pi -= 1 / (2 * i + 1);
            }
        }
        return pi * 4;
    }

    // Monte Carlo method: check how many random points lay in circle with radius=1
    // Area of circle = Pi * r^2 = Pi
    // Area of square = 2 ^ 2 = 4. Therefore ratio = Pi : 4
    public static double approxPi2() {
        int in = 0, n = 10000000;
        for (int i = 0; i < n; i++) {
            double x = Math.random(), y = Math.random();
            if (Math.sqrt(x * x + y * y) <= 1) {
                in++;
            }
        }
        return in * 4.0 / n;
    }

}
