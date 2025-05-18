package com.productservice.productservicemay25.services;

import com.productservice.productservicemay25.models.Category;
import com.productservice.productservicemay25.models.Product;
import com.productservice.productservicemay25.repositories.CategoryRepository;
import com.productservice.productservicemay25.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("selfProductService")
public class SelfProductService implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return this.productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
        if(category == null){
            // throw exception
        }
        Optional<Category> categoryOptional = categoryRepository.findByTitle(category.getTitle());

        if(categoryOptional.isEmpty()){
                categoryRepository.save(category);
        }
        return this.productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Long productId) {
         this.productRepository.deleteById(productId) ;
         return true;
    }
}
