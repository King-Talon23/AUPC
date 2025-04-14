package TK.f_AUPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Point {
    double real, imag;

    public Point(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Point subtract(Point other) {
        return new Point(this.real - other.real, this.imag - other.imag);
    }

    public Point divide(Point other) {
        double denominator = other.real * other.real + other.imag * other.imag;
        return new Point((this.real * other.real + this.imag * other.imag) / denominator,
                (this.imag * other.real - this.real * other.imag) / denominator);
    }

    public Point multiply(Point other) {
        return new Point(this.real * other.real - this.imag * other.imag,
                this.real * other.imag + this.imag * other.real);
    }

    public Point add(Point other) {
        return new Point(this.real + other.real, this.imag + other.imag);
    }

    public double abs() {
        return Math.sqrt(real * real + imag * imag);
    }
}

public class biometrics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double EPS = 1e-10;

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n == 0) {
                break;
            }

            List<Point>[] p = new List[2];
            p[0] = new ArrayList<>();
            p[1] = new ArrayList<>();

            for (int t = 0; t < 2; t++) {
                for (int i = 0; i < n; i++) {
                    double x = scanner.nextDouble();
                    double y = scanner.nextDouble();
                    p[t].add(new Point(x, y));
                }
            }

            boolean sim = true;
            Point s = p[0].get(1).subtract(p[0].get(0)).divide(p[1].get(1).subtract(p[1].get(0)));

            for (int i = 0; i < n; i++) {
                Point v = p[1].get(i).subtract(p[1].get(0)).multiply(s).add(p[0].get(0));
                if (v.subtract(p[0].get(i)).abs() >= EPS) {
                    sim = false;
                    break;
                }
            }

            System.out.println(sim ? "similar" : "dissimilar");
        }
        scanner.close();
    }
}