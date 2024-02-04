package ru.netology.Cloud.service;

import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.entity.File;

import java.io.IOException;
import java.util.List;


public interface FileService {
    void saveFile(MultipartFile file) throws IOException;

    void deleteFile(String fileName);

    File toFileEntity(MultipartFile uploadFile) throws IOException;

    boolean fileExists(String fileName);

    File getFile(String fileName);

    Iterable<File> getFiles(int size);

    List<File> toFilesList(Iterable<File> files);
}
