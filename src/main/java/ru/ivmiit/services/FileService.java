package ru.ivmiit.services;

import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.ivmiit.models.File;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    File saveFile(MultipartFile file);

    @SneakyThrows
    void writeFileToResponse(String url, HttpServletResponse response);
}
