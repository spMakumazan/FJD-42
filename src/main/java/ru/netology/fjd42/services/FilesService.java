package ru.netology.fjd42.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.fjd42.exceptions.*;
import ru.netology.fjd42.model.File;
import ru.netology.fjd42.model.Token;
import ru.netology.fjd42.model.User;
import ru.netology.fjd42.repositories.FilesRepository;
import ru.netology.fjd42.repositories.TokensRepository;
import ru.netology.fjd42.schemas.FileNameSchema;
import ru.netology.fjd42.schemas.FileSizeSchema;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilesService {

    private FilesRepository filesRepository;
    private TokensRepository tokensRepository;

    public void uploadFile(String authToken, String filename, MultipartFile multipartFile) {
        User owner = getUserByToken(authToken);
        try {
            byte[] content = multipartFile.getBytes();
            File file = new File();
            file.setFilename(filename);
            file.setContent(content);
            file.setOwner(owner);
            filesRepository.save(file);
        } catch (IOException exception) {
            throw new ErrorInputDataException("Error input data");
        }
    }

    public void deleteFile(String authToken, String fileName) {
        User owner = getUserByToken(authToken);
        if (fileName == null) {
            throw new ErrorInputDataException("Error input data");
        }
        File file = filesRepository.findByFilenameAndOwner(fileName, owner).orElseThrow(
                () -> new ErrorDeleteFileException("Error delete file")
        );
        filesRepository.delete(file);
    }

    public byte[] downloadFile(String authToken, String fileName) {
        User owner = getUserByToken(authToken);
        if (fileName == null) {
            throw new ErrorInputDataException("Error input data");
        }
        File file = filesRepository.findByFilenameAndOwner(fileName, owner).orElseThrow(
                () -> new ErrorDownloadFileException("Error download file")
        );
        return file.getContent();
    }

    public void editFilename(String authToken, String fileName, FileNameSchema fileNameSchema) {
        User owner = getUserByToken(authToken);
        String newFilename = fileNameSchema.getFilename();
        if (fileName == null || newFilename == null) {
            throw new ErrorInputDataException("Error input data");
        }
        File file = filesRepository.findByFilenameAndOwner(fileName, owner).orElseThrow(
                () -> new ErrorUploadFileException("Error upload file")
        );
        file.setFilename(newFilename);
        filesRepository.save(file);
    }

    public List<FileSizeSchema> getAllFiles(String authToken, int limit) {
        User owner = getUserByToken(authToken);
        if (limit <= 0) {
            throw new ErrorInputDataException("Error input data");
        }
        List<File> fileList = filesRepository.findAllByOwner(owner);
        return fileList.stream()
                .map(obj -> new FileSizeSchema(obj.getFilename(), obj.getContent().length))
                .limit(limit)
                .collect(Collectors.toList());
    }

    private User getUserByToken(String authToken) {
        String jwtToken = authToken.substring(7);
        Token token = tokensRepository.findById(jwtToken).orElseThrow(
                () -> new UnauthorizedErrorException("Unauthorized error")
        );
        return token.getUser();
    }
}
