package ru.netology.Cloud.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.dto.FileResponse;
import ru.netology.Cloud.entity.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilesMapper {

    public File mapMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File();
        file.setName(multipartFile.getName());
        file.setOriginalName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setSize(multipartFile.getSize());
        file.setBytes(multipartFile.getBytes());
        return file;
    }

    public FileResponse mapToFileResponse(File file) {
        return new FileResponse(file.getOriginalName(), file.getSize());
    }
}
