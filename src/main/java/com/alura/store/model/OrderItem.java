package com.alura.store.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_items")
public class OrderItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private BigDecimal unitPrice;
  private int quantity;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Order order;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Product product;
  
  public OrderItem() {
  }

  public OrderItem(int quantity, Order order, Product product) {
    this.quantity = quantity;
    this.order = order;
    this.product = product;
    this.unitPrice = product.getPrice();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public BigDecimal getValue() {
    return unitPrice.multiply(new BigDecimal(quantity));
  }
}
