package com.etiya.training.services.concretes;

import com.etiya.training.entities.Category;
import com.etiya.training.entities.Product;
import com.etiya.training.repositories.CategoryRepository;
import com.etiya.training.repositories.ProductRepository;
import com.etiya.training.services.abstracts.ProductService;
import com.etiya.training.services.dtos.product.AddProductRequest;
import com.etiya.training.services.dtos.product.GetListProductResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// interface-interface => extends
// class-class => extends
// class-interface => implements
@Service
public class ProductManager implements ProductService
{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductManager(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product add(AddProductRequest request) {
        //TODO: MANUEL MAPPINGI .stream ile düzelt
        //ekleme işi için iş akışı
        //dTo => Transfer => Mapping
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Verilen id ile bir kategori bulunamadı."));
        Product newProduct = new Product();
        newProduct.setProductName(request.getProductName());
        newProduct.setQuantityPerUnit(request.getQuantityPerName());
        newProduct.setDiscontinued(request.getDiscontinued());

        category.setCategoryId(request.getCategoryId());
        newProduct.setCategory(category);

        return productRepository.save(newProduct);
    }

    @Override
    public void delete(Short id) {
        Product productToDelete = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Bu id ile bir ürün bulunamadı"));
        this.productRepository.delete(productToDelete);
    }

    @Override
    public List<GetListProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        // Manual Mapping
       /* List<Product> products = productRepository.findAllCustom();
        List<GetListProductResponse> response=new ArrayList<>();
        // TODO: Refactor
        for (Product product: products) {
            GetListProductResponse dto = new GetListProductResponse();
            dto.setProductId(product.getProductId());
            dto.setProductName(product.getProductName());
            GetListProductResponse dto = new GetListProductResponse(product.getProductId(), product.getProductName());
            response.add(dto);
        }


        return response;
        */

        // Bir Iterable (koleksiyon) alanın stream türüne çevrilerek üzerinde stream API işlevlerinin gerçekleştirilmesi.
        // Java 8
        products.stream().forEach((product) -> {

        });

        List<GetListProductResponse> dtos = products
                .stream()
                .filter(product -> product.getDiscontinued() == 1 && product.getProductId() > 5)
                .map((product -> {
            return new GetListProductResponse(product.getProductId(), product.getProductName());
        })).sorted( Comparator.comparing(GetListProductResponse::getProductName).reversed()).toList();

        return dtos;
    }

    @Override
    public List<GetListProductResponse> getByName(String name) {
        return productRepository.getByName(name);
    }
}