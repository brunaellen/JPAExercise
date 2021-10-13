package com.alura.store.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String descripton;
  private BigDecimal price;
  private LocalDate dateRegistration = LocalDate.now();
  
  @ManyToOne
  private Category category;
  
  public Product() {
  }
  
  public Product(String name, String descripton, BigDecimal price, Category category) {
    this.name = name;
    this.descripton = descripton;
    this.price = price;
    this.category = category;
  }
  
  public LocalDate getRegisterDate() {
    return dateRegistration;
  }
  public void setRegisterDate(LocalDate registerDate) {
    this.dateRegistration = registerDate;
  }
  public Category getCategory() {
    return category;
  }
  public void setCategory(Category category) {
    this.category = category;
  }
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
  public String getDescripton() {
    return descripton;
  }
  public void setDescripton(String descripton) {
    this.descripton = descripton;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
