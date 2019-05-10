package com.validity.services;

import com.validity.models.FileReport;
import com.validity.models.User;
import com.validity.services.struct.FileInputService;
import com.validity.utils.PointUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.*;

@Service
public class FileInputServiceImpl implements FileInputService {
    private static List<User> list;
    private static int baseFullness, totalFullness;

    public FileReport consumeAndReport(MultipartFile file) {
        HashMap<String, User> uniqueMap = new HashMap<>();
        HashMap<String, User> possibleDup = new HashMap<>();

        try {
            CSVParser records = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(file.getInputStream()));

            for (CSVRecord csvRecord : records) {
                // Accessing values by the names of headers assigned to each column
                System.out.println(csvRecord);
                User user = new User.UserBuilder()
                        .withUserId(csvRecord.getRecordNumber())
                        .withFirstName(csvRecord.get("first_name"))
                        .withLastName(csvRecord.get("last_name"))
                        .withCompany(csvRecord.get("company"))
                        .withEmail(csvRecord.get("email"))
                        .withAddressOne(csvRecord.get("address1"))
                        .withAddressTwo(csvRecord.get("address2"))
                        .withCity(csvRecord.get("city"))
                        .withStateShort(csvRecord.get("state"))
                        .withStateLong(csvRecord.get("state_long"))
                        .withZipCode(zipProcessor(csvRecord.get("zip")))
                        .withPhoneNumber(csvRecord.get("phone"))
                        .build();
                // uncomment this to see each record that is being read at a given time
                //System.out.println("Read : \n" + user);

                PointUtils.fullnessCalc(user);
                if (uniqueMap.containsKey(user.getEmail())) {
                    if (!possibleDup.containsKey(user.getEmail()))
                        possibleDup.put(user.getEmail(), user);
                    else
                        possibleDup.put(user.getEmail() + "-dup", user); // if duplicate records > 2

                } else {
                    uniqueMap.put(user.getEmail(), user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //compute score for duplicate values, <each point of score dictates cost of conversion for the record>
        possibleDup.forEach((k, v) -> {
            PointUtils.computeScore(v, uniqueMap.get(k));
        });

        return makeFileReport(uniqueMap, possibleDup);
    }

    private static int zipProcessor(String zipInString) {
        if (zipInString.isEmpty() || zipInString.equals("")) {
            return 0;
        }
        return Integer.parseInt(zipInString);
    }

    private FileReport makeFileReport(Map master, Map dup) {
        baseFullness = 0;
        totalFullness = 0;
        FileReport fileReport = new FileReport();

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

        return fileReport;
    }

    private static List<User> recordComputationsAndListing(Map map) {
        list = new ArrayList<>(map.values());
        list.sort(Comparator.comparing(User::getUserId));
        list.forEach(e -> {
            baseFullness += e.getFullness();
            totalFullness += 11;
            System.out.println(e.toString());
        });
        return list;
    }

}
