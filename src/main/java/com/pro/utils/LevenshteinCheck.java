package com.pro.utils;

import com.pro.models.User;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class LevenshteinCheck {
    static int score = 0;

    private LevenshteinCheck() {
        // this utils class should not be instantiated
    }

    /**
     * Levenshtein utility functions. Each function takes one or more Strings as input and produces the result after
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
            score += LevenshteinCheck.dist(master.getFirstName(), dup.getFirstName());

        if (!master.getLastName().equals(dup.getLastName()))
            score += LevenshteinCheck.dist(master.getLastName(), dup.getLastName());

        if (!master.getEmail().equals(dup.getEmail()))
            score += LevenshteinCheck.dist(master.getEmail(), dup.getEmail());

        if (!master.getCompany().equals(dup.getCompany()))
            score += LevenshteinCheck.dist(master.getCompany(), dup.getCompany());

        if (!master.getAddressOne().equals(dup.getAddressOne()))
            score += LevenshteinCheck.dist(master.getAddressOne(), dup.getAddressOne());

        if (!master.getAddressTwo().equals(dup.getAddressTwo()))
            score += LevenshteinCheck.dist(master.getAddressTwo(), dup.getAddressTwo());

        if (!master.getCity().equals(dup.getCity()))
            score += LevenshteinCheck.dist(master.getCity(), dup.getCity());

        if (!master.getStateShort().equals(dup.getStateShort()))
            score += LevenshteinCheck.dist(master.getStateShort(), dup.getStateShort());

        if (!master.getStateLong().equals(dup.getStateLong()))
            score += LevenshteinCheck.dist(master.getStateLong(), dup.getStateLong());

        if (master.getPhone() != dup.getPhone())
            score += 1;

        if (master.getZipCode() != dup.getZipCode())
            score += 1;

        master.setLevScore(score);
        dup.setLevScore(score);
    }

    /**
     * Method indicating if a message can be considered equal,
     * based on Levenshtein distance calculation.
     *
     * @param referenceText the reference text
     * @param targetText    the target text for the comparison
     * @return 0 if the can be considered similar else return integer for number number of shifts
     */
    public static int dist(String referenceText, String targetText) {
        return LevenshteinDistance.getDefaultInstance().apply(referenceText, targetText);
    }
}
