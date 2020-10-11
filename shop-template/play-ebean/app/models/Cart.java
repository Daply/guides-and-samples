package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cart")
public class Cart extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id", unique = true, updatable = false, nullable = false)
    private long cartId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCartQuantity> products;

    @Column(name="total_price")
    private double totalPrice;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
    @JoinColumn(name="cart_user_id")
    @JsonIgnore
    private User user;

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public List<ProductCartQuantity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCartQuantity> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addProductCartQuantity(ProductCartQuantity productCartQuantity) {
        if (products == null)
            products = new ArrayList<>();
        products.add(productCartQuantity);
    }

    public void removeProductCartQuantity(ProductCartQuantity productCartQuantity) {
        if (products != null)
            products.remove(productCartQuantity);
    }

}
