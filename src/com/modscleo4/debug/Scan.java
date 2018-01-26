package com.modscleo4.debug;

import static com.modscleo4.readWrite.textReadWrite.print;

/**
 * Created by modsc on 19/05/2017.
 */
public class Scan {
    public static void main() {
        int i = 123456, sum = 0;
        while (i > 0) {
            sum += i % 10;
            i /= 10;
        }
        print(sum);
    }
}
