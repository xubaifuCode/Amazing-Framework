package org.amazing.framework.util;

/**
 * Created by alex on 17-7-11.
 */
public class PrintHelper {
    private static boolean DEBUG_OUTPUT = true;

    public static void print(Object msg) {
        if (DEBUG_OUTPUT) {
            System.out.println(msg);
        }
    }
}
