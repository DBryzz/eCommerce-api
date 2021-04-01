package com.dbryzz.ecoms.domain.controller;

import com.dbryzz.ecoms.domain.dto.ProductPostDTO;
import com.dbryzz.ecoms.domain.dto.ProductDTO;
import com.dbryzz.ecoms.domain.model.Product;
import com.dbryzz.ecoms.domain.service.CategoryService;
import com.dbryzz.ecoms.domain.service.ProductService;
import com.dbryzz.ecoms.domain.dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/product")
@SessionAttributes("sessionInfo")
public class ProductController {

    /*@Autowired
    private FileStorageService storageService;*/

    private CategoryService categoryService;
    private ProductService productService;


    @Autowired
    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/products")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('BUYER')" )
    public String showProducts(@ModelAttribute Product product, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "cssandjs/products";
        }

        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getCategories());

        return "cssandjs/products";
    }




    @PostMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public ResponseEntity<MessageResponseDTO> addProduct(@PathVariable("userId") Long userId,
                                                         @ModelAttribute @Valid ProductPostDTO product,
                                                         @RequestPart("image") MultipartFile productImage) {

        String message = "";

        // Normalize file name
        String fileName = StringUtils.cleanPath(productImage.getOriginalFilename());

        if(fileName.contains("..")) {
            message = "Error: Filename contains invalid path sequence " + fileName;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new MessageResponseDTO(message));
        }

        if (!(fileName.toLowerCase().endsWith(".jpeg") ||
                fileName.toLowerCase().endsWith(".jpg") ||
                fileName.toLowerCase().endsWith(".png"))) {
            message = "Error: UnSupported file Format. \nfile must be png, jpg or jpeg format  " + fileName;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new MessageResponseDTO(message));
        }

        message = productService.saveProduct(userId, product, productImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO(message));

    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getListOfAllProducts() {


        return ResponseEntity.ok(productService.getAllProducts());

    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductDTO> getAProduct(@PathVariable("id") Long productId) {

        return ResponseEntity.ok(productService.getProduct(productId));

        /*Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);*/
    }

    @GetMapping("/uploads/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = productService.loadImage(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
