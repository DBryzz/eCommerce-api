package com.gg.ecom.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.ecom.dto.PostProductDTO;
import com.gg.ecom.dto.ProductDTO;
import com.gg.ecom.exception.ResourceNotFoundException;
import com.gg.ecom.model.Category;
import com.gg.ecom.model.Product;
import com.gg.ecom.model.User;
import com.gg.ecom.repository.CategoryRepository;
import com.gg.ecom.repository.ProductRepository;
import com.gg.ecom.repository.UserRepository;
import com.gg.ecom.service.FileStorageService;
import com.gg.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

//    private final Path root = Paths.get("uploads");

    private ProductRepository productRepository;
    private FileStorageService storageService;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FileStorageService storageService,
                              UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String saveProduct(Long userId,
                              PostProductDTO productDTO, MultipartFile productImage) {

        //PostProductDTO productDTO = getJson(productRequest);

        String imageUrl = "";
        String message = "";

        Long catId = productDTO.getProductCategory().getCategoryId();

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Category> categoryOptional = categoryRepository.findById(catId);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("User Not Found: UserId - " +userId );
        }

        if (!categoryOptional.isPresent()) {
            throw new ResourceNotFoundException("Category Not Found: CategoryId - " +catId );
        }


        try {
            imageUrl = storageService.save(productImage);
            message = "Uploaded the file successfully: " + productImage.getOriginalFilename();
            System.out.println(message);

        } catch (Exception e) {
            message = "Could not upload the file: " + productImage.getOriginalFilename() + "!";
            throw new RuntimeException(message);
        }


        Product product = copyProductDTOtoProduct(productDTO, new Product());
        product.setSeller(userOptional.get());
        product.setProductCategory(categoryOptional.get());
        product.setProductImageUrl(imageUrl);

        productRepository.save(product);

        message = "Product was successfully created \n product url: " + product.getProductImageUrl();
        return message;

    }

    @Override
    public List<ProductDTO> getAllProducts() {

        Iterable<Product> products = productRepository.findAll();
        return loadProductDTOS(products);

       /*



       List<ProductDTO> products = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ProductServiceImpl.class, "getFile", path.getFileName().toString()).build().toString();
            return new ProductDTO(filename, url);
        }).collect(Collectors.toList());
        return products;

        */
    }



   /* @Override
    public List<org.springframework.hateoas.Resource<ProductDTO>> getAllProducts() {

        Iterable<Product> products = productRepository.findAll();
        List<org.springframework.hateoas.Resource<ProductDTO>> productResources = new ArrayList<>();
        for (ProductDTO product : loadProductDTOS(products)) {
            org.springframework.hateoas.Resource<ProductDTO> rProduct = new org.springframework.hateoas.Resource<ProductDTO>(product);
            productResources.add(rProduct);
        }
        return productResources;


       *//* List<ProductDTO> products = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ProductServiceImpl.class, "getFile", path.getFileName().toString()).build().toString();
            return new ProductDTO(filename, url);
        }).collect(Collectors.toList());
        return products;*//*
    }

    @Override
    public org.springframework.hateoas.Resource<ProductDTO> getProduct(Long id) {

        Product product = productRepository.findById(id).get();

        return new Resource<ProductDTO>(copyProducttoProductDTO(product, new ProductDTO())) ;
    }
*/

    @Override
    public ProductDTO getProduct(Long id) {

        Product product = productRepository.findById(id).get();

        return copyProducttoProductDTO(product, new ProductDTO());
    }

    @Override
    public void removeProduct(Long id) {

    }

    @Override
    public void editProduct(Long id, String product, MultipartFile productImage) {

    }

    @Override
    public Resource loadImage(String filename) {
        return storageService.load(filename);
    }

    public List<ProductDTO> loadProductDTOS(Iterable<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(copyProducttoProductDTO(product, new ProductDTO()));
        }

        return productDTOS;

    }

    private ProductDTO copyProducttoProductDTO(Product product, ProductDTO productDTO) {

        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductQuantity(product.getProductQuantity());
        productDTO.setProductImageUrl(product.getProductImageUrl());
        productDTO.setSeller(product.getSeller());
        productDTO.setProductCategory(product.getProductCategory());

        return productDTO;
    }

  /*  private Object CopyToFromProductDTO(Object obj1, Object obj2) {
        Product s1 = new Product();
        PostProductDTO s2 = new PostProductDTO();
        ProductDTO d1 = new ProductDTO();

        if (obj1.getClass().isInstance(Product.class)) {

            Product source = (Product) obj1;
            ProductDTO destination = (ProductDTO) obj2;
            destination.setProductId(source.getProductId());
        } else {
            PostProductDTO source = (PostProductDTO) obj1;
            Product destination = (Product) obj2;
        }

        destination.setProductName(source.getProductName());
        destination.setProductDescription(source.getProductDescription());
        destination.setProductPrice(source.getProductPrice());
        destination.setProductQuantity(source.getProductQuantity());
        destination.setProductImageUrl(source.getProductImageUrl());
        destination.setSeller(source.getSeller());
        destination.setProductCategory(source.getProductCategory());

        return destination;
    }*/

    private Product copyProductDTOtoProduct(PostProductDTO productDTO, Product product) {

        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductQuantity(productDTO.getProductQuantity());
        /*product.setProductCategory(productDTO.getProductCategory());

        product.setProductImageUrl(productDTO.getProductImageUrl());
        product.setSeller(productDTO.getSeller());*/

        return product;
    }

    public PostProductDTO getJson(String product) {
        PostProductDTO productJson = new PostProductDTO();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            productJson = objectMapper.readValue(product, PostProductDTO.class);
        } catch (IOException err) {
            System.out.printf("Error: ", err.toString());
        }

        return productJson;
    }

}
