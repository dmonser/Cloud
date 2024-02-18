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
import ru.netology.Cloud.dto.PutRequest;
import ru.netology.Cloud.entity.File;
import ru.netology.Cloud.service.FileServiceImpl;

import java.io.IOException;
import java.util.List;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class DataController {

    private final FileServiceImpl fileService;

    @PostMapping("/file")
    public ResponseEntity<Void> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.saveFile(file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/file")
    public ResponseEntity<Void> fileDelete(@RequestParam("filename") String fileName) {
        fileService.deleteFile(fileName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/file")
    public ResponseEntity<ByteArrayResource> fileDownload(@RequestParam("filename") String fileName) {
        File file = fileService.getFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getOriginalName() + "\"")
                .body(new ByteArrayResource(file.getBytes()));
    }

    @PutMapping("/file")
    public ResponseEntity<Void> editFileName(@RequestParam("filename") String filename, @RequestBody PutRequest putRequest) {
        fileService.editFileName(filename, putRequest.filename());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileResponse>> getFilesList(@RequestParam("limit") int limit) {
        List<FileResponse> result = fileService.getFileResponseList(limit);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

