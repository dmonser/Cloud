package ru.netology.Cloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.entities.File;
import ru.netology.Cloud.repositories.FileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final FileRepository fileRepository;

    @Override
    public void saveFile(MultipartFile uploadFile) throws IOException {
        File file;

        if (uploadFile.getSize() != 0) {
            file = toFileEntity(uploadFile);
            fileRepository.save(file);
        }
    }

    @Override
    public File toFileEntity(MultipartFile uploadFile) throws IOException {
        File file = new File();
//        file.setOwner(user.getId());
        file.setName(uploadFile.getName());
        file.setOriginalName(uploadFile.getOriginalFilename());
        file.setContentType(uploadFile.getContentType());
        file.setSize(uploadFile.getSize());
        file.setBytes(uploadFile.getBytes());
        return file;
    }

    @Override
    public void deleteFile(String fileName) {
        Optional<File> optFile = fileRepository.findFileByOriginalName(fileName);
        if(optFile.isEmpty()) {
            throw new RuntimeException(String.format("File '%s' not found", fileName));
        }
        File file = optFile.get();
        fileRepository.delete(file);
    }

    @Override
    public boolean fileExists(String fileName) {
        Optional<File> optFile = fileRepository.findFileByOriginalName(fileName);
        return optFile.isPresent();
    }

    @Override
    public File getFile(String fileName) {
        return fileRepository.findFileByOriginalName(fileName).orElseThrow(() -> new RuntimeException("File not found"));
    }

    public Iterable<File> getFiles(int size) {
        PageRequest diapason = PageRequest.of(0, size);
        try {
            return fileRepository.findAll(diapason);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return List.of();
    }

    public List<File> toFilesList(Iterable<File> files) {
        List<File> result = new ArrayList<>();
        for (File fileObj : files) {
            File file = new File();
            file.setId(fileObj.getId());
            file.setOriginalName(fileObj.getOriginalName());
            file.setSize(fileObj.getSize());
            file.setContentType(fileObj.getContentType());
            result.add(file);
        }
        return result;
    }
}
