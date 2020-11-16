package com.gg.ecom.service;

import com.gg.ecom.dto.ProductDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    String saveProduct(Long userId, Long catId, String product, MultipartFile productImage);

    List<ProductDTO> getAllProducts();

    ProductDTO getProduct(Long id);

    void removeProduct(Long id);

    void editProduct(Long id, String product, MultipartFile productImage);

    Resource loadImage(String filename);
}
