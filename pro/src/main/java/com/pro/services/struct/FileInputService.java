package com.validity.services.struct;

import com.validity.models.FileReport;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileInputService {
    FileReport consumeAndReport(MultipartFile file);

}
