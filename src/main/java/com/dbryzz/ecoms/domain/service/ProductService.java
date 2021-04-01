package com.dbryzz.ecoms.domain.service;

import com.dbryzz.ecoms.domain.dto.ProductPostDTO;
import com.dbryzz.ecoms.domain.dto.ProductDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    String saveProduct(Long userId, ProductPostDTO product, MultipartFile productImage);

    List<ProductDTO> getAllProducts();

    ProductDTO getProduct(Long id);

    void removeProduct(Long id);

    void editProduct(Long id, String product, MultipartFile productImage);

    Resource loadImage(String filename);
}
