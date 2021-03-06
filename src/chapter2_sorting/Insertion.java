package chapter2_sorting;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        // 升序
        for (int i = 1; i < N; i++) {
            // 将 a[i]插入到 a[i-1]、a[i-2]、a[i-3]...之中，其实就是往回插入，逐个比较到合适的位置
            for (int j = i;j>0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        String[] a = StdIn.readAllStrings();
        Integer[] a = {3,23,5,0,8,12,45};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
