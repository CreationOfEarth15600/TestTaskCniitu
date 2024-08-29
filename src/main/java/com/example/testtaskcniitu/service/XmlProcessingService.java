package com.example.testtaskcniitu.service;

import com.example.testtaskcniitu.model.UsersWrapper;
import com.example.testtaskcniitu.repository.UserRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class XmlProcessingService {

    @Autowired
    private UserRepository userRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public CompletableFuture<Void> processFileAsync(MultipartFile file) {
        return CompletableFuture.runAsync(() -> {
            try {
                File tempFile = File.createTempFile("upload", ".xml");
                file.transferTo(tempFile);
                processXml(tempFile);
            } catch (IOException e) {
                throw new RuntimeException("Failed to process file" , e);
            }
        }, executorService);
    }

    private void processXml(File file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        UsersWrapper usersWrapper = xmlMapper.readValue(file, UsersWrapper.class);
        userRepository.saveAll(usersWrapper.getUsers());
    }
}
