package com.ecommerce.service;

import com.ecommerce.dto.PagedResponse;
import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProductById(Long id);
    PagedResponse<ProductResponse> getAllProducts(Pageable pageable);
    PagedResponse<ProductResponse> getProductsByCategory(String category, Pageable pageable);
    PagedResponse<ProductResponse> searchProducts(String keyword, Pageable pageable);
    List<ProductResponse> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
    List<String> getAllCategories();
}
