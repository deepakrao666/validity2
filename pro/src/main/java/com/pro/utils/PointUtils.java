package com.validity.utils;

import com.validity.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/*
 * this file containes all the simple arithmetic computation.
 * Each method in this file should be fast and take basic computation to achieve its goal.
 */

public class PointUtils {
    private static Logger logger = LoggerFactory.getLogger(PointUtils.class);

    static int score = 0;
    static int fullness = 0;

    public static void computeScore( User dup,  User master) {
        score = 0;
        if (!master.getFirstName().equals(dup.getFirstName()))
            score += PointUtils.costCalc(master.getFirstName(), dup.getFirstName());

        if (!master.getLastName().equals(dup.getLastName()))
            score += PointUtils.costCalc(master.getLastName(), dup.getLastName());

        if (!master.getEmail().equals(dup.getEmail()))
            score += PointUtils.costCalc(master.getEmail(), dup.getEmail());

        if (!master.getCompany().equals(dup.getCompany()))
            score += PointUtils.costCalc(master.getCompany(), dup.getCompany());

        if (!master.getAddressOne().equals(dup.getAddressOne()))
            score += PointUtils.costCalc(master.getAddressOne(), dup.getAddressOne());

        if (!master.getAddressTwo().equals(dup.getAddressTwo()))
            score += PointUtils.costCalc(master.getAddressTwo(), dup.getAddressTwo());

        if (!master.getCity().equals(dup.getCity()))
            score += PointUtils.costCalc(master.getCity(), dup.getCity());

        if (!master.getStateShort().equals(dup.getStateShort()))
            score += PointUtils.costCalc(master.getStateShort(), dup.getStateShort());

        if (!master.getStateLong().equals(dup.getStateLong()))
            score += PointUtils.costCalc(master.getStateLong(), dup.getStateLong());

        if (master.getPhone() != dup.getPhone())
            score += 1;

        if (master.getZipCode() != dup.getZipCode())
            score += 1;

        master.setDupScore(score);
        dup.setDupScore(score);
    }

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

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

    public static void fullnessCalc(User user) {
        fullness = 11;

        if (user.getFirstName().equals(null)|| user.getFirstName().equals(""))
            fullness -= 1;

        if (user.getLastName().equals(null)|| user.getLastName().equals(""))
            fullness -= 1;

        if (user.getEmail().equals(null)||user.getEmail().equals(""))
            fullness -= 1;

        if (user.getCompany().equals(null) || user.getCompany().equals(""))
            fullness -= 1;

        if (user.getAddressOne().equals(null) || user.getAddressOne().equals(""))
            fullness -= 1;

        if (user.getAddressTwo().equals(null)|| user.getAddressTwo().equals(""))
            fullness -= 1;

        if (user.getCity().equals(null) || user.getCity().equals(""))
            fullness -= 1;

        if (user.getStateShort().equals(null) || user.getStateShort().equals(""))
            fullness -= 1;

        if (user.getStateLong().equals(null) || user.getStateLong().equals("") )
            fullness -= 1;

        if (user.getPhone().equals(null) || user.getPhone().equals(""))
            --fullness;

        if (user.getZipCode() == 0)
            fullness -= 1;
        user.setFullness(fullness);
    }

    public static float rate(float x, float y){
        float res = (x/y);
        logger.info("rate = {}", res);
        return res;
    }
}
