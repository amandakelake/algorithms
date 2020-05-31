package chapter2_sorting;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Shell {
    public static void sort(Comparable[] a) {
        // 对插入排序的优化，插入排序只会交换相邻的元素，而且只能一点一点的挪过去
        // 希尔排序以交换不相邻的元素来对数组局部进行排序
        int N = a.length;
        int h = 1;
        // 根据数组长度动态调整h大小
        while (h < N/3) {
            h = 3*h + 1;
        }
        while (h >=1) {
            // 将数组变为h有序
            for (int i = h; i < N; i++) {
                // 将a[i] 插入到 a[i-h]、a[i-2h]、a[i-3h]...中
                for (int j=i; j >=h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            // 不断缩小h直到1
            h = h/3;
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
