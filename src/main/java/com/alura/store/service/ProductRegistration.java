package com.alura.store.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.alura.store.dao.CategoryDao;
import com.alura.store.dao.ProductDao;
import com.alura.store.dao.RegisterDAO;
import com.alura.store.model.Category;
import com.alura.store.model.Product;
import com.alura.store.model.StoreModel;
import com.alura.store.util.JPAUtil;

public class ProductRegistration {

  private EntityManager entityManager;
  private ProductDao productDao;
  private CategoryDao categoryDao;
  
  public ProductRegistration() {
    entityManager = JPAUtil.getEntityManager();
    productDao = new ProductDao(entityManager);
    categoryDao = new CategoryDao(entityManager);
  }
  
  public void registerAProduct() {
    Category electronicsCategory = new Category("electronics");
    registerInTransaction(categoryDao, electronicsCategory);
    
    String productName = "Apple MacBook Air";
    Product laptop = new Product(productName, "13-inch, 8GB RAM, 256GB SSD", 
        BigDecimal.valueOf(800), electronicsCategory);
    
    registerInTransaction(productDao, laptop);
    displayRegisteredProduct(productName);
    closeEntityManager();
  }
  
  private void closeEntityManager() {
    entityManager.close();
  }

  private void registerInTransaction(RegisterDAO dao, StoreModel order) {
    entityManager.getTransaction().begin();
    dao.register(order);
    entityManager.getTransaction().commit();
  }
  
  private void displayRegisteredProduct(String productName) {
    Product product = productDao.searchProductByName(productName);
    System.out.println("\nProduct successfully registered!!!\n" + product);
  }
}
