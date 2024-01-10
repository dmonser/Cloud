package ru.netology.Cloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class DataController {

    @PostMapping("/file_upload")
    public ResponseEntity<String> fileUpload(@RequestParam("comment") String comment,
                                             @RequestParam("fileName") MultipartFile file) {
        try (InputStream is = file.getInputStream()){
            return ResponseEntity.ok("Comment: " + comment + ", Name: " + file.getName()
                    + ", File Name:" + file.getOriginalFilename() + ", Size: " + is.available());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
