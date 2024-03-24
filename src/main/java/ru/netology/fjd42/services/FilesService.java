package ru.netology.fjd42.services;

import lombok.AllArgsConstructor;
import org.hibernate.PropertyValueException;
import org.springframework.stereotype.Service;
import ru.netology.fjd42.exceptions.*;
import ru.netology.fjd42.model.File;
import ru.netology.fjd42.model.Token;
import ru.netology.fjd42.model.User;
import ru.netology.fjd42.repositories.FilesRepository;
import ru.netology.fjd42.repositories.TokensRepository;
import ru.netology.fjd42.schemas.FileNameSchema;
import ru.netology.fjd42.schemas.FileSchema;
import ru.netology.fjd42.schemas.FileSizeSchema;

import java.util.List;

@Service
@AllArgsConstructor
public class FilesService {

    private FilesRepository filesRepository;
    private TokensRepository tokensRepository;

    public void uploadFile(String authToken, String filename, FileSchema fileSchema) {
        User owner = getUserByToken(authToken);
        String content = fileSchema.getFile();
        File file = new File();
        try {
            file.setFilename(filename);
            file.setContent(content);
            file.setOwner(owner);
        } catch (PropertyValueException exception) {
            throw new ErrorInputDataException("Error input data");
        }
        filesRepository.save(file);
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

    public FileSchema downloadFile(String authToken, String fileName) {
        User owner = getUserByToken(authToken);
        if (fileName == null) {
            throw new ErrorInputDataException("Error input data");
        }
        File file = filesRepository.findByFilenameAndOwner(fileName, owner).orElseThrow(
                () -> new ErrorDeleteFileException("Error download file")
        );
        return new FileSchema(file.getFilename(), file.getContent());
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
        if (fileList.isEmpty()) {
            throw new ErrorGettingFileListException("Error getting file list");
        }
        return fileList.stream()
                .map(obj -> new FileSizeSchema(obj.getFilename(), obj.getContent().length()/8))
                .limit(limit)
                .toList();
    }

    private User getUserByToken(String authToken) {
        Token token = tokensRepository.findById(authToken).orElseThrow(
                () -> new UnauthorizedErrorException("Unauthorized error")
        );
        return token.getUser();
    }
}
