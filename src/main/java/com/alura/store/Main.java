package com.alura.store;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.alura.store.dao.CategoryDao;
import com.alura.store.dao.ProductDao;
import com.alura.store.model.Category;
import com.alura.store.model.Product;
import com.alura.store.util.JPAUtil;

public class Main {
  public static void main(String[] args) {
    registerAProduct();
    EntityManager entityManager = JPAUtil.getEntityManager();
    ProductDao productDao = new ProductDao(entityManager);
    
    Product product = productDao.searchAProductById(1l);
    System.out.print(product.getPrice());
    
    List<Product> productsList = productDao.searchProductByCategoryName("mobile phones");
    productsList.forEach(products -> System.out.print(products.getName()));
    
    BigDecimal productPrice = productDao.searchProductPriceByProductName("Xiaomi Redmi");
    System.out.print("the product price is: " + productPrice);
  }

  public static void registerAProduct() {
    Category mobilePhoneCategory = new Category("mobile phones");
    Product mobilePhone = new Product("Xiaomi Redmi", "Blue", new BigDecimal(800), mobilePhoneCategory);

    EntityManager entityManager = JPAUtil.getEntityManager();
    ProductDao productDao = new ProductDao(entityManager);
    CategoryDao categoryDao = new CategoryDao(entityManager);
    
    entityManager.getTransaction().begin();
    productDao.register(mobilePhone);
    categoryDao.register(mobilePhoneCategory);
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
