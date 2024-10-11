package com.large_file_processing.demo.controller;

import com.large_file_processing.demo.service.LocalFileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {
    private final LocalFileUploadService localFileUploadService;
    @PostMapping
    public String upload( @RequestParam("file") MultipartFile file,
                          @RequestParam("filename") String filename,
                          @RequestParam("originalFilename") String originalFilename,
                          @RequestParam("chunkIndex") int chunkIndex,
                          @RequestParam("totalChunks") int totalChunks) {
        log.info("Received file: {}", originalFilename);

        return localFileUploadService.upload(file, originalFilename, chunkIndex, totalChunks);
    }
}
