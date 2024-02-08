package ru.netology.Cloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.Cloud.dto.FileResponse;
import ru.netology.Cloud.entity.File;
import ru.netology.Cloud.repository.FileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    public void saveFile(MultipartFile uploadFile) throws IOException {
        File file;

        if (uploadFile.getSize() == 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error input data");
        }
        file = toFileEntity(uploadFile);
        try {
            fileRepository.save(file);
        } catch (HttpServerErrorException exception) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error upload data");
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
        if (optFile.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error input data");
        }
        File file = optFile.get();
        try {
            fileRepository.delete(file);
        } catch (HttpServerErrorException e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error delete file");
        }
    }

    @Override
    public File getFile(String fileName) {
        try {
            return fileRepository.findFileByOriginalName(fileName).orElseThrow(() ->
                    new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error input data"));
        } catch (HttpServerErrorException e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error download file");
        }
    }

    @Override
    public List<FileResponse> getFileList(int size) {
        Iterable<File> files = getFiles(size);
        List<FileResponse> result = new ArrayList<>();
        for (File fileObj : files) {
            FileResponse fileResponse = new FileResponse(fileObj.getOriginalName(), fileObj.getSize());
            result.add(fileResponse);
        }
        return result;
    }

    private Iterable<File> getFiles(int size) {
        if (size <= 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error input data");
        }
        PageRequest diapason = PageRequest.of(0, size);
        try {
            return fileRepository.findAll(diapason);
        } catch (HttpServerErrorException exception) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting file list");
        }
    }
}
