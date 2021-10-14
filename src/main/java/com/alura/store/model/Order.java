package com.alura.store.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements StoreModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private BigDecimal totalValue;
  private LocalDate date = LocalDate.now();
  
  @ManyToOne
  private Client client;
  
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
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
  
  public List<OrderItem> getItems() {
    return items;
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
  
  /* private long id;
  private BigDecimal totalValue;
  private LocalDate date = LocalDate.now();
  private Client client;
  private List<OrderItem> items = new ArrayList<>();*/
  
  @Override
  public String toString() {
    
//    final StringBuilder itemNames = new StringBuilder();
//    List<Product> products = new ArrayList<>();
//    for (OrderItem item : items) {
//       products.add(item.getProduct());
//    }
//    
//    List<String> productNames = new ArrayList<String>();
//    for (Product product : products) {
//      productNames.add(product.getName());
//    }
    
    List<String> allItemNames = items
      .stream()
      .map(orderItem -> orderItem.getProduct())
      .map(product -> product.getName())
      .collect(Collectors.toList());

    
    return String.format("%nOrder specification:%nId: %d%n"
        + "Client: %s\nProducts: %s%n",this.id, this.client.getName(), 
        Arrays.toString(allItemNames.toArray()));
  }
}
