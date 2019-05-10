package com.pro.utils;

import com.pro.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This file contains all the simple arithmetic computation.
 * Each method in this file should be fast and take basic computation to achieve its goal.
 */

public class PointUtils {
    private static Logger logger = LoggerFactory.getLogger(PointUtils.class);

    static int fullness = 0;

    /**
     * Fullness checks for missing values in user object, for each missing values deduces a point of fullness.
     * Fullness is 11 at max, if all attributes hold some value and 0 if all are missing.
     *
     * @param user the reference User Object
     */
    public static void fullnessCalc(User user) {
        /*
         * Fullness :
         * for every attribute of the record that is missing
         * the fullness rate of the record drops by 1.
         */

        fullness = 11;
        if (user.getFirstName().equals("")) fullness -= 1;
        if (user.getLastName().equals("")) fullness -= 1;
        if (user.getEmail().equals("")) fullness -= 1;
        if (user.getCompany().equals("")) fullness -= 1;
        if (user.getAddressOne().equals("")) fullness -= 1;
        if (user.getAddressTwo().equals("")) fullness -= 1;
        if (user.getCity().equals("")) fullness -= 1;
        if (user.getStateShort().equals("")) fullness -= 1;
        if (user.getStateLong().equals("")) fullness -= 1;
        if (user.getPhone().equals("")) fullness -= 1;
        if (user.getZipCode() == 0) fullness -= 1;
        user.setFullness(fullness);
    }

    /**
     * Simple calculation for rate
     * Rate of dictates the amount of redundancy within the input file.
     *
     * @param x the actual Fullness of file
     * @param y the total Fullness of file
     */
    public static float rate(float x, float y) {
        float res = (x / y);
        logger.info("rate = {}", res);
        return res;
    }
}
