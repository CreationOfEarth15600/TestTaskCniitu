package com.example.testtaskcniitu.controller;

import com.example.testtaskcniitu.model.User;
import com.example.testtaskcniitu.repository.UserRepository;
import com.example.testtaskcniitu.service.XmlProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private XmlProcessingService xmlProcessingService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<String>> uploadFile(@RequestParam("file") MultipartFile file) {
        return xmlProcessingService.processFileAsync(file)
                .thenApply(result -> ResponseEntity.ok("File processed successfully"))
                .exceptionally(ex -> ResponseEntity.status(500).body("Error processing file: " + ex.getMessage()));
    }
}
