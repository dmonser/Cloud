package ru.netology.Cloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.entities.User;
import ru.netology.Cloud.service.FileService;
import ru.netology.Cloud.service.UserServiceImpl;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DataController {

//    private final FileService fileService;
//    private final UserServiceImpl userService;
//
//    @PostMapping("/file_upload")
//    public ResponseEntity<?> fileUpload(@RequestParam("user_name") String userName,
//                                        @RequestParam("file") MultipartFile file) throws IOException {
//        User user;
//        Optional<User> optionalUser = userService.findByUsername(userName);
//        if (optionalUser.isPresent()) {
//            user = optionalUser.get();
//        } else {
//            return new ResponseEntity<>("User: " + userName + " not found", HttpStatus.NOT_FOUND);
//        }
//
//        fileService.saveFile(user, file);
//        return ResponseEntity.ok("File: " + file.getOriginalFilename() + " is upload");
//    }

//    @PostMapping("/file_upload")
//    public ResponseEntity<?> fileUpload(@RequestParam("comment") String comment,
//                                        @RequestParam("file") MultipartFile file) {
//        try (InputStream is = file.getInputStream()){
//            return ResponseEntity.ok("Comment: " + comment + ", Name: " + file.getName()
//                    + ", File Name:" + file.getOriginalFilename() + ", Size: " + is.available());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
