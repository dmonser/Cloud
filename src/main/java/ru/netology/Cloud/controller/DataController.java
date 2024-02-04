package ru.netology.Cloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.entity.File;
import ru.netology.Cloud.service.FileServiceImpl;

import java.io.IOException;
import java.util.List;

@RequestMapping("/cloud")
@RestController
@RequiredArgsConstructor
public class DataController {

    private final FileServiceImpl fileService;

    @PostMapping("/file") // Upload file to server
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getSize() != 0) {
            try {
                fileService.saveFile(file);
                return ResponseEntity.ok("Success upload");
            } catch (IOException e) {
                return new ResponseEntity<>("Error upload data", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error input data", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete") // Delete file
    public ResponseEntity<?> fileDelete(@RequestParam("filename") String fileName) {
        if (fileService.fileExists(fileName)) {
            try {
                fileService.deleteFile(fileName);
                return ResponseEntity.ok("Success deleted");
            } catch (RuntimeException e) {
                return new ResponseEntity<>("Error delete file", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error input data", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get") // Download file from cloud
    public ResponseEntity<?> fileDownload(@RequestParam("filename") String fileName) {
        if (fileService.fileExists(fileName)) {
            try {
                File file = fileService.getFile(fileName);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(file.getContentType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getOriginalName() + "\"")
                        .body(new ByteArrayResource(file.getBytes()));
            } catch (RuntimeException e) {
                return new ResponseEntity<>("Error download file", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error input data", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list") // Get all files
    public ResponseEntity<?> getFilesList(@RequestParam("limit") int limit) {
        if (limit > 0) {
            try {
                Iterable<File> files = fileService.getFiles(limit);
                List<File> result = fileService.toFilesList(files);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>("Error getting file list", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error input data", HttpStatus.BAD_REQUEST);
        }
    }

}

