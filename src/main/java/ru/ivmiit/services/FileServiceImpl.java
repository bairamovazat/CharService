package ru.ivmiit.services;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ivmiit.models.File;
import ru.ivmiit.repositories.FileRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private FileRepository fileRepository;

    @Override
    @SneakyThrows
    public File saveFile(MultipartFile file) {
        UUID uuid = UUID.randomUUID();

        File newFile = File.builder()
                .url(uuid.toString())
                .storageFileName(storagePath + uuid.toString())
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .build();

        OutputStream outputStream = new FileOutputStream(new java.io.File(newFile.getStorageFileName()));

        IOUtils.copy(file.getInputStream(), outputStream);
        fileRepository.save(newFile);
        return newFile;
    }

    @Override
    @SneakyThrows
    public void writeFileToResponse(String url, HttpServletResponse response) {
        File file = fileRepository.findFirstByUrl(url);

        response.setContentType(file.getType());
        InputStream inputStream = new FileInputStream(new java.io.File(file.getStorageFileName()));

        response.setHeader("Content-Disposition", "attachment; filename=\""
                + URLEncoder.encode(file.getName(), "UTF-8").replace("+", "_") + "\"");
        response.setContentType(file.getType());


        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}