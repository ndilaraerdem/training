package com.etiya.training.services.abstracts;

import com.etiya.training.entities.Product;
//servislerin somut hallerine manager denir
// soyutlama burada yapılır. Manger olmak isteyen bir classın sorumlulukları burda tanımlanır.
public interface ProductService
{
    Product add(Product product);
    void delete(Short id);
}