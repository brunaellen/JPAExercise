package com.alura.store.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private BigDecimal totalValue;
  private LocalDate date = LocalDate.now();
  
  @ManyToOne
  private Client client;
  
  @OneToMany(mappedBy = "order")
  private List<OrderItem> items = new ArrayList<>();
  
  public Order() {
  }
 
  public Order(Client client) {
    this.client = client;
  }
  
  public void addAItemToOrder(OrderItem orderItem) {
    orderItem.setOrder(this);
    this.items.add(orderItem);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public BigDecimal getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(BigDecimal totalValue) {
    this.totalValue = totalValue;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
