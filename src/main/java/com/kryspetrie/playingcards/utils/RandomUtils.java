package com.kryspetrie.playingcards.utils;

import lombok.RequiredArgsConstructor;

import java.util.Random;

/**
 * Basic randomness utilities.
 * This is not a static class because we want to be able to modify the randomness
 * by setting the seed, for testing purposes.
 */
@RequiredArgsConstructor
public class RandomUtils {

    /** External randomness supplier. Can be used to force a seed. */
    private final Random randomSupplier;

    /**
     * Return a random integer within the provided range, inclusive of range values.
     * @param lowerBound Lower boundary of the range, inclusive
     * @param upperBound Upper boundary of the range, inclusive
     * @return A random integer.
     */
    public int getRandomIntegerWithinRangeInclusive(final int lowerBound, final int upperBound) {
        if (lowerBound == upperBound) {
            return lowerBound;
        }

        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Upper bound must be LARGER than lower bound.");
        }

        return randomSupplier.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }
}
