package com.kryspetrie.playingcards.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

class RandomUtilsTest {

    private static final long RANDOM_SEED = 1L;

    /** Parameters for {@link RandomUtilsTest#testRandomRangeUsesAllValuesInRange} */
    static Stream<Arguments> testRandomRangeUsesAllValuesInRangeParameters() {
        return Stream.of(

                // Demonstrate a random value from a single number range
                Arguments.of(5, 5, List.of(5, 5, 5, 5, 5)),

                // Demonstrate that we are producing sufficiently random numbers
                Arguments.of(0, 3, List.of(
                        2, 0, 1, 1, 0, 0, 1, 2, 3, 2, 0, 0, 3, 0, 3, 2, 3, 3, 3, 1,
                        1, 3, 1, 0, 1, 1, 2, 3, 0, 2, 3, 2, 2, 0, 0, 3, 1, 2, 0, 3,
                        2, 1, 3, 0, 0, 3, 2, 0, 2, 2, 0, 1, 1, 0, 2, 2, 2, 3, 0, 1,
                        2, 0, 0, 0, 0, 2, 0, 1, 0, 1, 2, 0, 3, 0, 0, 0, 1, 3, 0, 1,
                        1, 3, 0, 2, 3, 1, 0, 1, 2, 0, 2, 0, 0, 3, 2, 2, 3, 3, 0, 1)),

                // Demonstrate that we handle negatives and positives
                Arguments.of(-2, 2, List.of(-2, 1, 0, 1, 2, 2, 2, -1, 1, 1)),

                // Demonstrate that we can handle purely negative ranges
                Arguments.of(-4, 0, List.of(-4, -1, -2, -1, 0, 0, 0, -3, -1, -1)));
    }

    @ParameterizedTest
    @MethodSource("testRandomRangeUsesAllValuesInRangeParameters")
    void testRandomRangeUsesAllValuesInRange(final int lowerBound, final int upperBound, final List<Integer> expected) {
        // GIVEN we have the utility
        final var randomUtils = provideUtils();

        // WHEN we ask for a random value some number of times
        final var generated = Stream.generate(() -> randomUtils.getRandomIntegerWithinRangeInclusive(lowerBound, upperBound))
                .limit(expected.size())
                .toList();

        // THEN we get the expected values
        // Note: We set the seed, so the expected values should be identical
        Assertions.assertThat(generated).containsExactlyElementsOf(expected);
    }

    @Test
    void testRandomRangeThrowsForBadInput() {
        // GIVEN we have the utility
        final var randomUtils = provideUtils();

        // WHEN we try to generate values within the bounds
        // THEN we throw an exception describing invalid arguments
        Assertions.assertThatThrownBy(() -> randomUtils.getRandomIntegerWithinRangeInclusive(10, -10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Upper bound must be LARGER than lower bound.");
    }

    private static RandomUtils provideUtils() {
        var random = new Random(RANDOM_SEED);
        return new RandomUtils(random);
    }
}