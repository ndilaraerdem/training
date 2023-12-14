package com.etiya.training.services.concretes;

import com.etiya.training.core.utils.exceptions.types.BusinessException;
import com.etiya.training.entities.Category;
import com.etiya.training.entities.Product;
import com.etiya.training.repositories.ProductRepository;
import com.etiya.training.services.abstracts.CategoryService;
import com.etiya.training.services.abstracts.ProductService;
import com.etiya.training.services.dtos.product.AddProductRequest;
import com.etiya.training.services.dtos.product.GetListProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

// interface-interface => extends
// class-class => extends
// class-interface => implements
@Service
@AllArgsConstructor
public class ProductManager implements ProductService
{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Product add(AddProductRequest request) {
        //TODO: MANUEL MAPPINGI .stream ile düzelt
        //ekleme işi için iş akışı
        //dTo => Transfer => Mapping
        Category category = this.throwExceptionIfCategoryNotExists(request.getCategoryId());
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
        Product productToDelete = this.productRepository.findById(id).orElseThrow(() -> new BusinessException("Bu id ile bir ürün bulunamadı"));
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
        return productRepository.getByName(name.toLowerCase());
    }

    private Category throwExceptionIfCategoryNotExists(Short id){
        return categoryService.getById(id)
                .orElseThrow(()-> new BusinessException("Verilen id ile bir kategori bulunamadı."));
    }
}