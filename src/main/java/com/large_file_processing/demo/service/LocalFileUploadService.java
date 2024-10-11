package com.large_file_processing.demo.service;

import com.large_file_processing.demo.exception.FileUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class LocalFileUploadService {
    private static final String UPLOAD_DIR = "C:/path/to/upload/dir"; // 최종 파일이 저장될 경로
    private static final String TEMP_DIR = UPLOAD_DIR + "/temp";  // 임시 디렉토리

    public String upload(MultipartFile file, String originalFilename, int chunkIndex, int totalChunks) {
        try {
            Path tempFilePath = Paths.get(TEMP_DIR, originalFilename + "_part_" + chunkIndex);
            Files.createDirectories(tempFilePath.getParent());
            file.transferTo(tempFilePath.toFile());
            log.info("Chunk saved at: {}", tempFilePath.toString());

            if (chunkIndex == totalChunks - 1) {
                log.info("sds");
                return mergeChunks(originalFilename, totalChunks);
            }

            return "Chunk uploaded successfully.";
        } catch (IOException e) {
            log.error("Failed to save chunk", e);
            throw new FileUploadException(e);
        }
    }

    private String mergeChunks(String originalFilename, int totalChunks) {
        Path finalFilePath = Paths.get(UPLOAD_DIR, originalFilename);

        try (BufferedOutputStream mergingStream = new BufferedOutputStream(
                new FileOutputStream(finalFilePath.toFile(), true))) {

            for (int i = 0; i < totalChunks; i++) {
                Path chunkPath = Paths.get(TEMP_DIR, originalFilename + "_part_" + i);
                Files.copy(chunkPath, mergingStream);
                Files.delete(chunkPath);
            }
            log.info("File merged at: {}", finalFilePath.toString());
            return finalFilePath.toString();
        } catch (IOException e) {
            log.error("Failed to merge chunks", e);
            throw new FileUploadException("Failed to merge chunks", e);
        }
    }

}
