package com.modscleo4.debug;

import static com.modscleo4.readWrite.textReadWrite.print;

/**
 * Created by modsc on 19/05/2017.
 */
public class Scan {
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int a = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = a;
                }
            }
        }
        return array;
    }

    public static void main() {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (int anArr : arr)
            System.out.print(anArr + " ");
        bubbleSort(arr);
        System.out.println();
        for (int anArr : arr)
            System.out.print(anArr + " ");
    }
}
