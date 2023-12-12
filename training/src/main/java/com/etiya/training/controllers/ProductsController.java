package com.etiya.training.controllers;

import com.etiya.training.entities.Product;
import com.etiya.training.services.abstracts.ProductService;
import com.etiya.training.services.dtos.product.AddProductRequest;
import com.etiya.training.services.dtos.product.GetListProductResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products") //localhost:8080/api/products
public class ProductsController {

    // spring tarafından auto
    // Dependency Injection her zaman soyut olmalı!

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<GetListProductResponse> getAll() {
        return productService.getAll();
    }
    @GetMapping("{id}") // path variable
    public Product getById(@PathVariable Short id) {
        return null;
    }

    @PostMapping
    public Product add(@RequestBody AddProductRequest product)
    {
        return productService.add(product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Short id) {
        this.productService.delete(id);
    }

    @PutMapping
    public String update(@RequestBody Product product) {
        // Save methodu ilgili entitynin idsi var ise güncelleme, yok ise ekleme yapar.
        return "Put mapping çalışıyor..";
    }

    //PART 2

//    private final ProductRepository productRepository;
//    public  ProductsController(ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }
//    @GetMapping
//    public List<Product> getAll() {
//
//        return this.productRepository.findAll();
//    }
//    @GetMapping("{id}") // path variable
//    public Product getById(@PathVariable Short id) {
//        return this.productRepository.findById(id).orElse(null);
//    }
//
//    @PostMapping
//    public Product add(@RequestBody Product product)
//    {
//        return this.productRepository.save(product);
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable Short id) {
//        this.productRepository.deleteById(id);
//    }
//
//    @PutMapping
//    public String update(@RequestBody Product product) {
//        // Save methodu ilgili entitynin idsi var ise güncelleme, yok ise ekleme yapar.
//        this.productRepository.save(product);
//        return "Put mapping çalışıyor..";
//    }


    // PART 1

//    //localhost:8080/api/products GET
//    @GetMapping("get1")
//    public String hello() {
//        return "merhaba";
//    }
//    //localhost:8080/api/products/get2 GET
//    @GetMapping("get2")
//    public String hello2() {
//        return "merhaba 2";
//    }
//    //  TODO: Ask what is the difference these 2 usages?
//     //localhost:8080/api/products/get3?name=Dilara GET
//    @GetMapping("get3") // Query String (RequestParam)
//    public String hello3(String name) {
//        return "merhaba " + name;
//    }
//     //localhost:8080/api/products/get4?isim=Dilara GET
//    @GetMapping("get4") // Query String (RequestParam)
//    public String hello4(@RequestParam("isim") String name) {
//        return "merhaba " + name;
//    }
//     //localhost:8080/api/products/Dilara GET

//    @GetMapping("{name}") // path variable
//    public String hello5(@PathVariable String name) {
//        return "merhaba " + name;
//    }

//    @PostMapping
//    public String postMethod(){
//        return "POST Request Completed ! ";
//    }

//    @PostMapping("save")
//    public String postMethod2(@RequestBody Product product) {
//        return "Eklenen Ürün Id'si: " + product.getId() + " name'i: " + product.getName();
//    }

//    @PutMapping
//    public String putMethod(){
//        return "PUT Request Completed ! ";
//    }
//
//    @DeleteMapping
//    public String deleteMethod(){
//        return "DELETE Request Completed ! ";
//    }
}