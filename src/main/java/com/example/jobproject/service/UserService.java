package com.example.jobproject.service;

import com.example.jobproject.Models.User;
import com.example.jobproject.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class UserService {

    private final Path fileStorageLocation= Paths.get("src/main/resources/files");



    @Autowired
    private UserRepository repository;
    public User saveUser(User user){
        return repository.save(user);
    }

    @SneakyThrows
    public String saveCV(MultipartFile file, String firstName, String lastName) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty");
            }

            if (!file.getContentType().equals("application/pdf")) {
                throw new IllegalArgumentException("Only PDF files are allowed");
            }

            String fileName = "CV_" + firstName + "_" + lastName + ".pdf";
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return targetLocation.toAbsolutePath().toString();
        } catch (IOException ex) {
            throw new IOException("Could not store file", ex);
        }
    }

    public String deleteCvOfUser(int id){
        System.out.println(id);
        User user=getUserById(id);
        String filePath=user.getCvPath();
        File fileToDelete = new File(filePath);

        // Check if the file exists before attempting to delete it
        if (fileToDelete.exists()) {
            // Delete the file
            if (fileToDelete.delete()) {
                return "File deleted successfully.";
            } else {
                return "Failed to delete the file.";
            }
        } else {
            return "File does not exist.";
        }
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserById(int id){
        return repository.findById(id).orElse(null);
    }

    public User getUserByLastName(String lastName){
        return repository.findByLastName(lastName);
    }


    public int deleteUser(int id){
        deleteCvOfUser(id);
        repository.deleteByIdd(id);

        return id;
    }

    public User updateUser(User user){
        User existingUser=repository.findById(user.getUserID()).orElse(null);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setCvPath(user.getCvPath());
        return repository.save(existingUser);
    }
}
