package castle.ArsProject.service;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public List<String> saveFiles(List<MultipartFile> files, String username) throws IOException {
        List<String> filenames = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String filename = username + extension;

            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path filepath = Paths.get(uploadDir, filename);
            Files.copy(file.getInputStream(), filepath);

            filenames.add(filename);
        }

        return filenames;
    }
}
