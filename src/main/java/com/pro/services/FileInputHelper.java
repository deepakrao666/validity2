package com.pro.services;

import com.pro.models.FileReport;
import com.pro.models.User;
import com.pro.utils.LevenshteinCheck;
import com.pro.utils.PointUtils;
import com.pro.utils.SelfCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FileInputHelper {
    private static int baseFullness, totalFullness;
    private static List<User> list;
    private static Logger logger = LoggerFactory.getLogger(FileInputHelper.class);


    /**
     * MakeFileReport function, uses util functions to create fileReport
     *
     * @param master, input master Map of string as key and value as User Object
     * @param dup,    input duplicate Map of string as key and value as User Object
     * @return object of fileReport
     */
    public static FileReport makeFileReport(Map<String, User> master, Map<String, User> dup) {
        FileReport fileReport = new FileReport();
        baseFullness = 0;
        totalFullness = 0;

        // master records computations.
        System.out.println("\n\nmaster list ");
        fileReport.setMasterList(recordComputationsAndListing(master));

        // possible duplicate record computations.
        System.out.println("\n\nPossible Duplicates include is ");
        fileReport.setDupList(recordComputationsAndListing(dup));

        // fullness = (  fullOfEachRecordInDup + fullOfEachRecordInMaster) / ( (dup.size + master.size)*11 )
        // redundancyRate is nulls or empty values in the record.
        System.out.println("sizes\nx=" + (baseFullness) + "\ny= " + (totalFullness));
        fileReport.setRedundancyRate(PointUtils.rate((baseFullness), (totalFullness)));

        FileInputHelper.setScores(master, dup);

        return fileReport;
    }

    /**
     * RecordComputationsAndListing function
     * Converts values in map to list of User Object
     * Calculates fullness of each User Object
     *
     * @return List of User Object
     */
    private static List<User> recordComputationsAndListing(Map<String, User> map) {
        list = new ArrayList<>(map.values());
        list.sort(Comparator.comparing(User::getUserId));
        list.forEach(e -> {
            baseFullness += e.getFullness();
            totalFullness += 11;
            logger.info(e.toString());
        });
        return list;
    }

    /**
     * setScores function
     * Calculates two types of scores for file report, selfScore and levScore.
     *
     * @param master, map of string as key and User object as value
     * @param dup,    map of string as key and User object as value
     */
    private static void setScores(Map<String, User> master, Map<String, User> dup) {
        //compute score for duplicate values, <each point of score dictates cost of conversion for the record>
        dup.forEach((k, v) -> {
            if (master.containsKey(k)) {
                SelfCheck.computeScore(v, master.get(k));
                LevenshteinCheck.computeScore(v, master.get(k));
            }
        });
    }
}
