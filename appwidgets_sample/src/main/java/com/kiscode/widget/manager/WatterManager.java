package com.kiscode.widget.manager;

/**
 * 喝水数量.
 */
public class WatterManager {

    private static int count = 0;

    public static int plus() {
        count++;
        return count;
    }

    public static int reduce() {
        count--;
        return count;
    }

}
