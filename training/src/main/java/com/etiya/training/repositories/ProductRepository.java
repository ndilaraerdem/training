package com.etiya.training.repositories;

import com.etiya.training.entities.Product;
import com.etiya.training.services.dtos.product.GetListProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository => extends
public interface ProductRepository extends JpaRepository<Product, Short> {
    //jpa rep. bizden ilişki kuracağımız entity alanını ve o entitynin id alanının tipini ister.
    //JPQL de tablo ismi yerine entity ismi ile kullanılır * yerine alias kullanırlır
    // JPQL'de tablo ismi değil Entity ismi ile kullanılır. * yerine Alias kullanılır.
    //@Query(value = "Select * from products p", nativeQuery = true)
    @Query(value = "Select p from Product p Where p.discontinued=0", nativeQuery = false)
    List<Product> findAllCustom();


    // String => Kod bloğu
    // classın tam yolu
    @Query("Select new com.etiya.training.services.dtos.product.GetListProductResponse(p.productId, p.productName) from Product p")
    List<GetListProductResponse> getAll();

    @Query("Select new com.etiya.training.services.dtos.product.GetListProductResponse(p.productId, p.productName) " +
            "from Product p Where LOWER( p.productName ) LIKE %:name%")
    List<GetListProductResponse> getByName(String name);

}
