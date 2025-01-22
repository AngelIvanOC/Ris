package com.example.conexionbd.product.model;

import com.example.conexionbd.product.model.Product;
import com.example.conexionbd.product.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Product save(Product product) {
        product.setStatus(true);
        return productRepository.save(product);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Product update(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            Product productNew = productOptional.get();
            productNew.setName(product.getName());
            productNew.setStock(product.getStock());
            return productRepository.save(productNew);
        }
        return null;
    }

    public Product changeStatus(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product productNew = productOptional.get();
            productNew.setStatus(!productNew.isStatus());
            return productRepository.save(productNew);
        }
        return null;
    }
}