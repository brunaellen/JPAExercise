package com.alura.store.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.alura.store.dao.CategoryDao;
import com.alura.store.dao.ClientDao;
import com.alura.store.dao.OrderDao;
import com.alura.store.dao.ProductDao;
import com.alura.store.dao.RegisterDAO;
import com.alura.store.model.Category;
import com.alura.store.model.Client;
import com.alura.store.model.Order;
import com.alura.store.model.OrderItem;
import com.alura.store.model.Product;
import com.alura.store.model.StoreModel;
import com.alura.store.util.JPAUtil;

public class OrderRegistration {
  
  private EntityManager entityManager;
  private ProductDao productDao;
  private ClientDao clientDao;
  private OrderDao orderDao;
  private CategoryDao categoryDao;

  public OrderRegistration() {
    entityManager = JPAUtil.getEntityManager();
    productDao = new ProductDao(entityManager);
    clientDao = new ClientDao(entityManager);
    orderDao = new OrderDao(entityManager);
    categoryDao = new CategoryDao(entityManager);
    
    populateDatabase(entityManager, productDao, clientDao);
  }
  
  public void createSampleOrder() {
    try {
      Client client = clientDao.searchAClientById(1l);
      Product product = productDao.searchAProductById(1l);
      Order order = new Order(client);
      order.addAItemToOrder(new OrderItem(10, order, product));
      registerInTransaction(orderDao, order);
      displayRegisteredOrder(order.getId());
    } finally {
      closeEntityManager();
    }
  }
  
  private void populateDatabase(EntityManager entityManager, ProductDao productDao, ClientDao clientDao) {
    Category mobilePhoneCategory = new Category("mobile phones");
    registerInTransaction(categoryDao, mobilePhoneCategory);
    
    Product mobilePhone = new Product("Xiaomi Redmi", "Blue", BigDecimal.valueOf(800), mobilePhoneCategory);
    registerInTransaction(productDao, mobilePhone);

    Client client = new Client("Bruna", "bruna@hotmail.com");
    registerInTransaction(clientDao, client);
  }
  
  private void closeEntityManager() {
    entityManager.close();
  }

  private void registerInTransaction(RegisterDAO dao, StoreModel order) {
    entityManager.getTransaction().begin();
    dao.register(order);
    entityManager.getTransaction().commit();
  }
  
  private void displayRegisteredOrder(long id) {
    Order order = orderDao.searchAOrderById(id);
    System.out.println("\nOder successfully registered!!!\n" + order);
  }
}
