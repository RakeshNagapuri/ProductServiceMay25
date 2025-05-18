package com.productservice.productservicemay25.dtos;

import lombok.*;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
