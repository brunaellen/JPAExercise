package com.alura.store.dao;

import javax.persistence.EntityManager;

import com.alura.store.model.Order;
import com.alura.store.model.Product;

public class OrderDao implements RegisterDAO<Order> {
  private EntityManager entityManager;
  
  public OrderDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  
  public void register(Order order) {
    this.entityManager.persist(order);
  }
  
  public Order searchAOrderById(Long id) {
    return entityManager.find(Order.class, id);
  }
}
