package com.alura.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.alura.store.model.Order;
import com.alura.store.model.Product;
import com.alura.store.vo.SalesReportVo;

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
  
  public BigDecimal totalValueSold() {
    String jpql = "SELECT SUM(o.totalValue) FROM Order o";
    return entityManager.createQuery(jpql, BigDecimal.class)
        .getSingleResult();
  }
  
  public List<SalesReportVo> salesReport() {
    String jpql = "SELECT new com.alura.store.vo.SalesReportVo( "
        + "product.name, "
        + "SUM(orderItem.quantity) as totalQuantity, "
        + "MAX(o.date)) "
        + "FROM Order o "
        + "JOIN o.items orderItem "
        + "JOIN orderItem.product product "
        + "GROUP BY product.name "
        + "ORDER BY totalQuantity DESC ";
    return entityManager.createQuery(jpql, SalesReportVo.class)
        .getResultList();
  }
  
  public Order seachOrderByClient(Long id) {
    String jpql = "SELECT o FROM Order o JOIN FETCH o.client WHERE o.id = :id";
    return entityManager.createQuery(jpql, Order.class)
        .setParameter("id", id)
        .getSingleResult();
  }
}
