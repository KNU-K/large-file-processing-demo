package com.large_file_processing.demo.service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface UploadService {
    String upload(MultipartFile file, String originalFilename, int chunkIndex, int totalChunks) ;
    String uploadBulk(List<MultipartFile> files);
}