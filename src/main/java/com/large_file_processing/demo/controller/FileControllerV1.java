package com.large_file_processing.demo.controller;

import com.large_file_processing.demo.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileControllerV1 {
    private final FileUploadService fileUploadService;
    @PostMapping("/chunk")
    public String uploadChunk( @RequestParam("file") MultipartFile file,
                          @RequestParam("originalFilename") String originalFilename,
                          @RequestParam("chunkIndex") int chunkIndex,
                          @RequestParam("totalChunks") int totalChunks) {
        log.info("Received file: {}", originalFilename);

        return fileUploadService.uploadChunk(file, originalFilename, chunkIndex, totalChunks);
    }
    @PostMapping()
    public String upload( @RequestParam("file") MultipartFile file) {
        log.info("Received file: {}", file.getOriginalFilename());

        return fileUploadService.upload(file);
    }
}
