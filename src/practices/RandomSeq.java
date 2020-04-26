package practices;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

public class RandomSeq {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("N:");
            int N = scanner.nextInt();
            System.out.println("lo:");
            double lo = scanner.nextDouble();
            System.out.println("hi:");
            double hi = scanner.nextDouble();

            System.out.printf("N:%d,lo:%f,hi:%f\n",N,lo,hi);
            for (int i = 0; i < N; i++) {
                double x = StdRandom.uniform(lo, hi);
                StdOut.printf("%.2f\n", x);
            }

    }

    private static void test(){
        System.out.println("hello world");
    }
}
