package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`products`")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private double price;
  private String image;
  // private String detailDesc;
  // private String shortDesc;
  private long quantity;
  private long sold;
  private String factory;
  private String target;

  // Getters and Setters
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  public long getSold() {
    return sold;
  }

  public void setSold(long sold) {
    this.sold = sold;
  }

  public String getFactory() {
    return factory;
  }

  public void setFactory(String factory) {
    this.factory = factory;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  // toString method
  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", quantity=" + quantity
        + ", sold=" + sold + ", factory=" + factory + ", target=" + target + "]";
  }

}
