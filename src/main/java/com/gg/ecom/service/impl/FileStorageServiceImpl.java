package com.gg.ecom.service.impl;

import com.gg.ecom.exception.ResourceNotFoundException;
import com.gg.ecom.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path root = Paths.get("uploads");


    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path imageUrl = this.root.resolve(fileName);
        //Path imageUrl = this.root.resolve(file.getOriginalFilename());

        //Path targetLocation = this.root.resolve(fileName);//                .path(fileName)


        try {
            Files.copy(file.getInputStream(), imageUrl, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        String productUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/product/" + imageUrl.toString())  //                .path(fileName)
                .toUriString();

        return productUri;
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("Error: " + e.getMessage());
        }
    }



    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

}
