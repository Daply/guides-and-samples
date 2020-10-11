package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name="product")
public class Product extends Model implements Comparable<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", unique = true, updatable = false, nullable = false)
    private long productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column
    private String description;

    @Column(name="image")
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProductCartQuantity> productCartQuantity;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ProductCartQuantity> getProductCartQuantity() {
        return productCartQuantity;
    }

    public void setProductCartQuantity(List<ProductCartQuantity> productCartQuantity) {
        this.productCartQuantity = productCartQuantity;
    }

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.getName());
    }

    public String getImage() {
        return this.image;
    }

    public String getImageName() {
        return "";
    }

//    public String getImageName() {
//        String encodeImage = "";
//        try {
//            if (this.image != null)
//                encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(Path.of(image)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return encodeImage;
//    }
}
