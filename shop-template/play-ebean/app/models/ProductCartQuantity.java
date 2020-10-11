package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="product_cart_quantity")
public class ProductCartQuantity extends Model implements Comparable<ProductCartQuantity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_cart_quantity_id", unique = true, nullable = false)
    private long productCartQuantityId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(columnDefinition = "double default 0")
    private double weight;

    @Column(columnDefinition = "int default 1")
    private int quantity;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE })
    @JoinColumn(name="products_cart_id")
    @JsonIgnore
    private Cart cart;

    public long getProductCartQuantityId() {
        return productCartQuantityId;
    }

    public void setProductCartQuantityId(long productCartQuantityId) {
        this.productCartQuantityId = productCartQuantityId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void increaseQuantity() {
        this.quantity++;
        this.price += this.product.getPrice();
        if (this.cart != null)
            this.cart.setTotalPrice(this.cart.getTotalPrice() + this.product.getPrice());
    }

    public void decreaseQuantity() {
        this.quantity--;
        this.price -= this.product.getPrice();
        if (this.cart != null)
            this.cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());
    }

    public void increaseWeight(double value) {
        this.weight += value;
        this.price += this.product.getPrice()/value;
    }

    public void decreaseWeight(double value) {
        this.weight -= value;
        this.price -= this.product.getPrice()/value;
    }

    @Override
    public int compareTo(ProductCartQuantity productCartQuantity) {
        return this.product.getName().compareTo(productCartQuantity.product.getName());
    }

}
