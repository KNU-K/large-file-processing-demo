package com.large_file_processing.demo.service;

import com.large_file_processing.demo.exception.FileUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class LocalFileUploadService implements UploadService{
    private static final String UPLOAD_DIR = "C:/path/to/upload/dir";
    @Override
    public String upload(MultipartFile file) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            Files.createDirectories(filePath.getParent());
            file.transferTo(filePath.toFile());
            log.info("File saved at: {}", filePath.toString());
            return filePath.toString();
        } catch (IOException e) {
            log.error("Failed to save file", e);
            throw new FileUploadException(e);
        }
    }

    @Override
    public String uploadBulk(List<MultipartFile> files) {
        return null;
    }
}
