package com.gg.ecom.controller;

import com.gg.ecom.dto.ProductDTO;
import com.gg.ecom.payload.response.MessageResponse;
import com.gg.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {

    /*@Autowired
    private FileStorageService storageService;*/

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('BUYER')" )
    public String showProducts(Model model) {

        model.addAttribute("products", productService.getAllProducts());

        return "cssandjs/products";
    }




    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<MessageResponse> addProduct(@PathVariable("userId") Long userId,
                                                      @PathVariable("categoryId") Long catId,
                                                      @RequestPart("product") String product,
                                                      @RequestPart("image") MultipartFile productImage) {

        String message = "";

        // Normalize file name
        String fileName = StringUtils.cleanPath(productImage.getOriginalFilename());

        if(fileName.contains("..")) {
            message = "Error: Filename contains invalid path sequence " + fileName;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new MessageResponse(message));
        }

        if (!(fileName.toLowerCase().endsWith(".jpeg") ||
                fileName.toLowerCase().endsWith(".jpg") ||
                fileName.toLowerCase().endsWith(".png"))) {
            message = "Error: UnSupported file Format. \nfile must be png, jpg or jpeg format  " + fileName;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new MessageResponse(message));
        }

        message = productService.saveProduct(userId, catId, product, productImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(message));

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
