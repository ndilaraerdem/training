package com.etiya.training.repositories;

import com.etiya.training.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository => extends
public interface ProductRepository extends JpaRepository<Product, Short> {
    //jpa rep. bizden ilişki kuracağımız entity alanını ve o entitynin id alanının tipini ister.

}
