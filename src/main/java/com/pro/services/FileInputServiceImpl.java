package com.pro.services;


import com.pro.exceptions.FileFormatCustomException;
import com.pro.models.FileReport;
import com.pro.models.User;
import com.pro.services.struct.FileInputService;
import com.pro.utils.PointUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.HashMap;

@Service
public class FileInputServiceImpl implements FileInputService {

    /**
     * ConsumeAndReport function, takes a file of CSV format, and parses its values
     * FileReport object is returned which is combination of rate which is amount of redundancy in the file,
     * unique values in the file, and possible duplicates it might find.
     *
     * @param file, consumes file
     * @return FileReport Object
     */
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
                        .withFirstName(csvRecord.get(1))
                        .withLastName(csvRecord.get(2))
                        .withCompany(csvRecord.get(3))
                        .withEmail(csvRecord.get(4))
                        .withAddressOne(csvRecord.get(5))
                        .withAddressTwo(csvRecord.get(6))
                        .withCity(csvRecord.get(8))
                        .withStateShort(csvRecord.get(10))
                        .withStateLong(csvRecord.get(9))
                        .withZipCode(zipProcessor(csvRecord.get(7)))
                        .withPhoneNumber(csvRecord.get(11))
                        .build();
                // uncomment this to see each record that is being read at a given time
                //System.out.println("Read : \n" + user);

                // fullness is the rate of records that are complete or have some value
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
            throw new FileFormatCustomException("Please check file input format.");
        }

        return FileInputHelper.makeFileReport(uniqueMap, possibleDup);
    }

    /**
     * zipProcessor function, String and converts to integer
     *
     * @param zipInString, input string
     * @return integer if string is valid, else 0
     */
    private static int zipProcessor(String zipInString) {
        try {
            if (zipInString.isEmpty() || zipInString.equals("")) {
                return 0;
            }
            return Integer.parseInt(zipInString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
