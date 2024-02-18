package ru.netology.Cloud.service;

import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.dto.FileResponse;
import ru.netology.Cloud.entity.File;

import java.io.IOException;
import java.util.List;


public interface FileService {
    void saveFile(MultipartFile file) throws IOException;

    void deleteFile(String fileName);

    File getFile(String fileName);

    List<FileResponse> getFileResponseList(int size);

    void editFileName(String fileName, String actualName);
}
