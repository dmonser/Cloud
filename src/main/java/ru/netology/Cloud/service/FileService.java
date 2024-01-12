package ru.netology.Cloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.entities.File;
import ru.netology.Cloud.entities.User;
import ru.netology.Cloud.repositories.FileRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void saveFile(User user, MultipartFile uploadFile) throws IOException {
        File file;

        if (uploadFile.getSize() != 0) {
            file = toFileEntity(uploadFile, user);
            user.addFileToUser(file);
        }
    }

    private File toFileEntity(MultipartFile uploadFile, User user) throws IOException {
        File file = new File();
        file.setOwner(user.getId());
        file.setName(uploadFile.getName());
        file.setOriginalName(file.getOriginalName());
        file.setContentType(uploadFile.getContentType());
        file.setSize(uploadFile.getSize());
        file.setBytes(uploadFile.getBytes());
        return file;
    }
}
