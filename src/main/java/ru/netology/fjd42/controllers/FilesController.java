package ru.netology.fjd42.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.fjd42.schemas.FileNameSchema;
import ru.netology.fjd42.schemas.FileSizeSchema;
import ru.netology.fjd42.services.FilesService;

import java.util.List;

@RestController
@RequestMapping("/cloud")
@AllArgsConstructor
public class FilesController {

    private FilesService filesService;

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestHeader("auth-token") String authToken,
                                        @RequestParam("filename") String fileName,
                                        @RequestParam("file") MultipartFile multipartFile) {
        filesService.uploadFile(authToken, fileName, multipartFile);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String authToken,
                                        @RequestParam("filename") String fileName) {
        filesService.deleteFile(authToken, fileName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> downloadFile(@RequestHeader("auth-token") String authToken,
                                               @RequestParam("filename") String fileName) {
        byte[] fileContent = filesService.downloadFile(authToken, fileName);
        return ResponseEntity.ok(fileContent);
    }

    @PutMapping("/file")
    public ResponseEntity<?> editFilename(@RequestHeader("auth-token") String authToken,
                                          @RequestParam("filename") String fileName,
                                          @RequestBody FileNameSchema fileNameSchema) {
        filesService.editFilename(authToken, fileName, fileNameSchema);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileSizeSchema>> getAllFiles(@RequestHeader("auth-token") String authToken,
                                                            @RequestParam("limit") int limit) {
        List<FileSizeSchema> fileSizeSchemaList = filesService.getAllFiles(authToken, limit);
        return ResponseEntity.ok(fileSizeSchemaList);
    }
}
