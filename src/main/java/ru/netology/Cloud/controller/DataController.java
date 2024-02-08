package ru.netology.Cloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.dto.FileResponse;
import ru.netology.Cloud.entity.File;
import ru.netology.Cloud.service.FileServiceImpl;

import java.io.IOException;
import java.util.List;

//@RequestMapping("/cloud")
@RestController
@RequiredArgsConstructor
public class DataController {

    private final FileServiceImpl fileService;

    @PostMapping("/file") // Upload file to server
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.saveFile(file);
        return ResponseEntity.ok("Success upload");
    }

    @DeleteMapping("/file") // Delete file
    public ResponseEntity<String> fileDelete(@RequestParam("filename") String fileName) {
        fileService.deleteFile(fileName);
        return ResponseEntity.ok("Success deleted");
    }

    @GetMapping("/file") // Download file from cloud
    public ResponseEntity<ByteArrayResource> fileDownload(@RequestParam("filename") String fileName) {
        File file = fileService.getFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getOriginalName() + "\"")
                .body(new ByteArrayResource(file.getBytes()));
    }

    @GetMapping("/list") // Get all files
    public ResponseEntity<?> getFilesList(@RequestParam("limit") int limit) {
        List<FileResponse> result = fileService.getFileList(limit);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

