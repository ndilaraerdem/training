package com.etiya.training.services.dtos.product;
//javax
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {
    @NotBlank(message = "Ürün adı boş geçilemez.")
    @Size(min = 3, message = "Ürün adı min 3 karakterli olmalıdır.")
    private String productName;
    private String quantityPerName;
    private int discontinued;
    private Short categoryId;
}
