package com.productservice.productservicemay25.services;

import com.productservice.productservicemay25.dtos.FakeStoreProductDto;
import com.productservice.productservicemay25.models.Category;
import com.productservice.productservicemay25.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
@Qualifier("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        return convertFakeProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProducts = fakeStoreProductDtoResponseEntity.getBody();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProducts){
            products.add(convertFakeProductDtoToProduct(fakeStoreProductDto));
        }
        // List implementation
//        ResponseEntity<List<FakeStoreProductDto>> fakeStoreProductDtoResponseEntity = restTemplate.exchange(
//                "https://fakestoreapi.com/products/",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {});
//
//        List<FakeStoreProductDto> fakeStoreProducts = fakeStoreProductDtoResponseEntity.getBody();
//        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProducts){
//            products.add(convertFakeProductDtoToProduct(fakeStoreProductDto));
//        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }


    private static Product convertFakeProductDtoToProduct(FakeStoreProductDto fakeProductDto){
        if(fakeProductDto == null){
            return null;
        }
        Product product = new Product();
        product.setId(fakeProductDto.getId());
        product.setTitle(fakeProductDto.getTitle());
        product.setPrice(fakeProductDto.getPrice());
        product.setDescription(fakeProductDto.getDescription());
        product.setImageUrl(fakeProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
