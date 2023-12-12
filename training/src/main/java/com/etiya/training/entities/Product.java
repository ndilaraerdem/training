package com.etiya.training.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Entity
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Column(name = "product_id")
    @Id
    private Short productId;  //erişim belirteci + tür + ismi

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @Column(name = "discontinued")
    private int discontinued;

    @ManyToOne // birden fazla ürün bir kategoriye sahip olabilir.
    @JoinColumn(name = "category_id") //foreign key
    private Category category;
}
//encapsulation
//alanlara direkt erişimin kısıtlanması
// encapsulation der ki ; alanlar her zaman private olmalı! dışarıdan erişilmemeli sadece class içerisinden erişilebilmeli!
//erişimi direkt alan üzerinden sağlamak yerine getter setter methodlar kullanırız
//kullanıcı alana istediği gibi bir veri atamasın diye encapsullation kullanırız.
//this => class'ın kendisi