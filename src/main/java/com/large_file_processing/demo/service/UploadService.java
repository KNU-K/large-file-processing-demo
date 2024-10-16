package com.large_file_processing.demo.service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface UploadService {
    String uploadChunk(MultipartFile file, String originalFilename, int chunkIndex, int totalChunks) ;
    String upload(MultipartFile file) ;
}