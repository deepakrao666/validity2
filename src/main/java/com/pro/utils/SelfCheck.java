package com.pro.utils;

import com.pro.models.User;

import java.util.Arrays;

public class SelfCheck {
    static int score = 0;

    /**
     * Function based on Levenshtein utility functions.
     * comparing all attributes in User Object.
     *
     * @param dup    the reference User Object
     * @param master the target User object for the comparison
     *               Score 0 if the object attributes are considered equal else Score n,
     *               n = sum of cost of comparison of all attributes strings from target to reference.
     */
    public static void computeScore(User dup, User master) {
        score = 0;
        if (!master.getFirstName().equals(dup.getFirstName()))
            score += SelfCheck.costCalc(master.getFirstName(), dup.getFirstName());

        if (!master.getLastName().equals(dup.getLastName()))
            score += SelfCheck.costCalc(master.getLastName(), dup.getLastName());

        if (!master.getEmail().equals(dup.getEmail()))
            score += SelfCheck.costCalc(master.getEmail(), dup.getEmail());

        if (!master.getCompany().equals(dup.getCompany()))
            score += SelfCheck.costCalc(master.getCompany(), dup.getCompany());

        if (!master.getAddressOne().equals(dup.getAddressOne()))
            score += SelfCheck.costCalc(master.getAddressOne(), dup.getAddressOne());

        if (!master.getAddressTwo().equals(dup.getAddressTwo()))
            score += SelfCheck.costCalc(master.getAddressTwo(), dup.getAddressTwo());

        if (!master.getCity().equals(dup.getCity()))
            score += SelfCheck.costCalc(master.getCity(), dup.getCity());

        if (!master.getStateShort().equals(dup.getStateShort()))
            score += SelfCheck.costCalc(master.getStateShort(), dup.getStateShort());

        if (!master.getStateLong().equals(dup.getStateLong()))
            score += SelfCheck.costCalc(master.getStateLong(), dup.getStateLong());

        if (master.getPhone() != dup.getPhone())
            score += 1;

        if (master.getZipCode() != dup.getZipCode())
            score += 1;

        master.setSelfScore(score);
        dup.setSelfScore(score);
    }

    /**
     * Method Dynamically stores cost of each step in converting string master to string dup.
     * Returns the value of minimum cost of conversion.
     *
     * @param master the reference text
     * @param dup    the target text for the comparison
     * @return 0 if the can be considered similar else return integer for number number of shifts
     */
    private static int costCalc(String master, String dup) {
        int[][] dp = new int[master.length() + 1][dup.length() + 1];
        for (int i = 0; i <= master.length(); i++) {
            for (int j = 0; j <= dup.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j - 1] + costOfSubstitution(master.charAt(i - 1), dup.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }
        return dp[master.length()][dup.length()];
    }

    /**
     * @param a, target character
     * @param b, reference character
     * @return 0 if equal else 1
     */
    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    /**
     * @param numbers, series of 1 or more integers.
     * @return minimum integer value from the multiple inputs
     */
    private static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

}
