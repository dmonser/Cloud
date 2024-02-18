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
import ru.netology.Cloud.mapper.FilesMapper;
import ru.netology.Cloud.repository.FileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final FilesMapper filesMapper;

    @Override
    public void saveFile(MultipartFile uploadFile) throws IOException {
        File file;

        if (uploadFile.getSize() == 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error input data");
        }
        file = filesMapper.mapMultipartFileToFile(uploadFile);
        try {
            fileRepository.save(file);
        } catch (HttpServerErrorException exception) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error upload data");
        }
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
    public List<FileResponse> getFileResponseList(int size) {
        return getFileList(size).stream()
                .map(filesMapper::mapToFileResponse)
                .toList();
    }

    @Override
    public void editFileName(String fileName, String actualName) {
        File file = getFile(fileName);
        file.setOriginalName(actualName);
        fileRepository.save(file);
    }

    private List<File> getFileList(int size) {
        if (size <= 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error input data");
        }
        try {
            return fileRepository.findFilesWithLimit(size);
        } catch (HttpServerErrorException exception) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting file list");
        }
    }
}
