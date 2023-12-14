package com.etiya.training.services.abstracts;

import com.etiya.training.entities.Product;
import com.etiya.training.services.dtos.product.AddProductRequest;
import com.etiya.training.services.dtos.product.GetListProductResponse;

import java.util.List;

//servislerin somut hallerine manager denir
// soyutlama burada yapılır. Manger olmak isteyen bir classın sorumlulukları burda tanımlanır.
public interface ProductService
{
    Product add(AddProductRequest product);
    void delete(Short id);

    List<GetListProductResponse> getAll();
    List<GetListProductResponse> getByName(String name);
}