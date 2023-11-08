package tn.esprit.devops_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProduct;
    String title;
    float price;
    int quantity;
    @Enumerated(EnumType.STRING)
    ProductCategory category;
    @ManyToOne()
    @JsonIgnore
    Stock stock;

    public Product(String title, float price, int quantity, ProductCategory category, Stock stock) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.stock = stock;
    }
}
