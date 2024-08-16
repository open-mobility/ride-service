package com.o4.mobility.common.utils.random;

import java.util.Random;

/**
 * Utility for Generating Random Long numbers
 *
 * @author M. Mazhar Hassan
 * @see Random
 * @since 1.0
 */
public interface RandomLongGenerator {

    Random RANDOM = new Random();

    /**
     * Generates a random long number.
     *
     * @return A random long number.
     */
    static long generateRandomLong() {
        return RANDOM.nextLong();
    }

    /**
     * Generates a random long number within a specific range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (inclusive).
     * @return A random long number within the specified range.
     */
    static long generateRandomLongInRange(long min, long max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be less than or equal to max");
        }

        return min + (long) (RANDOM.nextDouble() * (max - min + 1));
    }
}
