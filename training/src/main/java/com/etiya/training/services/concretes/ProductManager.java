package com.etiya.training.services.concretes;

import com.etiya.training.entities.Category;
import com.etiya.training.entities.Product;
import com.etiya.training.repositories.ProductRepository;
import com.etiya.training.services.abstracts.ProductService;
import com.etiya.training.services.dtos.product.AddProductRequest;
import com.etiya.training.services.dtos.product.GetListProductResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// interface-interface => extends
// class-class => extends
// class-interface => implements
@Service
public class ProductManager implements ProductService
{

    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(AddProductRequest request) {
        //ekleme işi için iş akışı
        //dTo => Transfer => Mapping
        Product newProduct = new Product();
        newProduct.setProductName(request.getProductName());
        newProduct.setQuantityPerUnit(request.getQuantityPerName());
        newProduct.setDiscontinued(request.getDiscontinued());

        Category category = new Category();
        category.setCategoryId(request.getCategoryId());
        newProduct.setCategory(category);

        return   productRepository.save(newProduct);
    }

    @Override
    public void delete(Short id) {
        Product productToDelete = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Bu id ile bir ürün bulunamadı"));
        this.productRepository.delete(productToDelete);
    }

    @Override
    public List<GetListProductResponse> getAll() {
        List<Product> products =  productRepository.findAll();

        List<GetListProductResponse> response = new ArrayList<>();

        for (Product product: products){
            GetListProductResponse dto = new GetListProductResponse();
            dto.setProductId(product.getProductId());
            dto.setProductName(product.getProductName());
            response.add(dto);
        }

        return response;
    }
}