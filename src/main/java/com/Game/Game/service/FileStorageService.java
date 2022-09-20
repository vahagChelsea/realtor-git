package com.Game.Game.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    void save(MultipartFile multipartFile) throws IOException;

    Resource load(String filename);

    Stream<Path> load();

    void clear();

}
