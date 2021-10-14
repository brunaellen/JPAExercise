package com.alura.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import com.alura.store.model.Product;

public class ProductDao implements RegisterDAO<Product>{
  private EntityManager entityManager;
  
  public ProductDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  
  @Override
  public void register(Product product) {
    this.entityManager.persist(product);
  }
  
  public Product searchAProductById(Long id) {
    return entityManager.find(Product.class, id);
  }
  
  public List<Product> searchAllProducts() {
    String jpql = "SELECT p FROM Product p";
    return entityManager.createQuery(jpql, Product.class).getResultList();
  }
  
  public Product searchProductByName(String name) {
    String jpql = "SELECT p FROM Product p WHERE p.name = :name";
    return entityManager.createQuery(jpql, Product.class)
        .setParameter("name", name)
        .getSingleResult();
  }
  
  public List<Product> searchProductByCategoryName(String name) {
    String jpql = "SELECT p FROM Product p WHERE p.category.name = :name";
    return entityManager.createQuery(jpql, Product.class)
        .setParameter("name", name)
        .getResultList();
  }
  
  public BigDecimal searchProductPriceByProductName(String name) {
    String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
    return entityManager.createQuery(jpql, BigDecimal.class)
        .setParameter("name", name)
        .getSingleResult();
  }
}
