package personal.photo.filter.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@RestController
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("image") MultipartFile file) {

        try {
            String uploadDir = "uploads/";

            Path dir = Paths.get(uploadDir);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            String filename = Paths
                    .get(file.getOriginalFilename())
                    .getFileName()
                    .toString();

            Path path = dir.resolve(filename);


            Files.copy(
                    file.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return "saved";
        } catch (Exception e) {
            return "Error" + e.getMessage();
        }
    }
}