package com.dbryzz.ecoms.domain.service.impl;

import com.dbryzz.ecoms.exception.ResourceNotFoundException;
import com.gg.ecoms.domain.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static Logger logger = LoggerFactory.getLogger(FileStorageServiceImpl.class);

    private final Path root = Paths.get("src/main/resources/static/images/product-dir/");


    @Override
    public void init() {
        try {
            if (Files.exists(root)) {
                System.out.println(root.toString() + " already exist");
            } else {
                Files.createDirectory(root);
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile file, String sellerId) {
        String fileName = sellerId +"."+ StringUtils.cleanPath(file.getOriginalFilename());

        Path imageUrl = this.root.resolve(fileName);


        try {
            Resource resource = new UrlResource(imageUrl.toUri());
            if (resource.exists() || resource.isReadable()) {
                return "Error: An image with the same name already exist";
            } else {

                Files.copy(file.getInputStream(), imageUrl, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: Could not store the file. Error: " + e.getMessage());
        }

        String productUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("product/" + imageUrl.toString())
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
    public void deleteFile(String fileUrl, HttpServletRequest request) {

        /*String hostDomainName = request.getHeader("host");
        String imagePath = "http://"+hostDomainName + "/product/src/main/resources/static/images/product-dir/";
        int imagePathSize = imagePath.length();*/


        String filename = StringUtils.getFilename(fileUrl).replace("%20", " ");

        String normalFilename = StringUtils.getFilename(fileUrl);

        System.out.println(fileUrl + " --> " + fileUrl.length());
        System.out.println(filename + " --> " + filename.length());
        System.out.println(normalFilename + " --> " + normalFilename.length());


        try {
            Path file = root.resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());
            File file1 = new File(file.toString());
            if (resource.exists() || resource.isReadable()) {
                file1.delete();
            } else {
                throw new ResourceNotFoundException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("Error: " + e.getMessage());
        }
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
