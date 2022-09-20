package com.Game.Game.service;

import com.Game.Game.models.Image;
import com.Game.Game.repositories.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path path;

    @Autowired
    private ImageRepository imageRepository;

    public FileStorageServiceImpl() throws IOException {
        path = Paths.get("fileStorage");
        try {
            Files.createDirectory(path);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void save(MultipartFile multipartFile) throws IOException {
        Files.copy(multipartFile.getInputStream(), this.path.resolve(multipartFile.getOriginalFilename()));
        Image image = Image.builder()
                .name(multipartFile.getName())
                .originalFileName(multipartFile.getOriginalFilename())
                .build();
        imageRepository.save(image);
    }

    @Override
    public Resource load(String filename) {
        Path file = path.resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                log.error("Could not read the file.");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Stream<Path> load() {
        return null;
    }

    @Override
    public void clear() {

    }
}
