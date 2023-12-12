package com.etiya.training.controllers;

import com.etiya.training.entities.Product;
import com.etiya.training.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products") //localhost:8080/api/products
public class ProductsController {

    private final ProductRepository productRepository;
    public  ProductsController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @GetMapping
    public List<Product> getAll() {

        return this.productRepository.findAll();
    }
    @GetMapping("{id}") // path variable
    public Product getById(@PathVariable Short id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Product add(@RequestBody Product product)
    {
        Product savedProduct = this.productRepository.save(product);
        return savedProduct;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Short id) {
        this.productRepository.deleteById(id);
    }

    @PutMapping
    public String update(@RequestBody Product product) {
        // Save methodu ilgili entitynin idsi var ise güncelleme, yok ise ekleme yapar.
        this.productRepository.save(product);
        return "Put mapping çalışıyor..";
    }

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