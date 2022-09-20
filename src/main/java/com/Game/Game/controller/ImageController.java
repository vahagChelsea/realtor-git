package com.Game.Game.controller;

import com.Game.Game.service.FileStorageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;


import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/test")
public class ImageController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestBody MultipartFile file) throws IOException {
        if (!file.isEmpty()) {

            fileStorageService.save(file);
            return ResponseEntity.ok().build();
        } else {
           return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getUpload(@PathVariable("fileName") String fileName,HttpServletResponse response) throws IOException {
        Resource file = fileStorageService.load(fileName);
//        response.setContentType("application/octet-stream");
//        String headerKey = "Content-Disposition";
//        String headervalue = "attachment; filename="+file.getFilename()+";";

//        response.setHeader(headerKey, headervalue);

        byte[] fileContent=StreamUtils.copyToByteArray(file.getInputStream());

//        ServletOutputStream outputStream=response.getOutputStream();
//        outputStream.write(fileContent);
//        outputStream.close();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileContent);
    }
}
