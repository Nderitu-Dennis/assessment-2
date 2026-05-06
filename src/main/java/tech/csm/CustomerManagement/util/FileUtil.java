package tech.csm.CustomerManagement.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileUtil {
    @Value("${upload.dir}")
    private String uploadDir;

    public String getDirPath() { return uploadDir; }

    public String uploadFile(MultipartFile file) {
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();  //to avoid file name collisions
        Path dest = Paths.get(uploadDir + fileName);
        try {
            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);q
        } catch (IOException e) {
            throw new RuntimeException("Upload failed", e);
        }
        return fileName;
    }
}
