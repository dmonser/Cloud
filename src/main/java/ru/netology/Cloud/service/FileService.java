package ru.netology.Cloud.service;

import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.entities.File;
import ru.netology.Cloud.entities.User;

import java.io.IOException;


public interface FileService {
    void saveFile(MultipartFile file) throws IOException;

    void deleteFile(String fileName);
    File toFileEntity(MultipartFile uploadFile) throws IOException;
    boolean fileExists(String fileName);
    File getFile(String fileName);
}
