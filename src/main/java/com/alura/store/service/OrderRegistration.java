package com.alura.store.service;

import java.math.BigDecimal;
import java.util.List;

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
import com.alura.store.vo.SalesReportVo;

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
      Product secondProduct = productDao.searchAProductById(2l);
      
      Order order = new Order(client);
      order.addAItemToOrder(new OrderItem(10, order, product));
      order.addAItemToOrder(new OrderItem(40, order, secondProduct));
      registerInTransaction(orderDao, order);
      
      displayRegisteredOrder(order.getId());
      displayTotalValueSold();
      
      List<SalesReportVo> salesReport = orderDao.salesReport();
      salesReport.forEach(System.out::println);
    }finally {
      closeEntityManager();
    }
  }
  
  private void populateDatabase(EntityManager entityManager, ProductDao productDao, ClientDao clientDao) {
    Category mobilePhoneCategory = new Category("mobile phones");
    Category games = new Category("games");
    
    registerInTransaction(categoryDao, mobilePhoneCategory);
    registerInTransaction(categoryDao, games);
    
    Product mobilePhone = new Product("Xiaomi Redmi", "Blue", BigDecimal.valueOf(800), mobilePhoneCategory);
    Product playstation = new Product("ps5", "playstation 5", BigDecimal.valueOf(1800), games);
    
    registerInTransaction(productDao, mobilePhone);
    registerInTransaction(productDao, playstation);

    Client client = new Client("Bruna", "bruna@hotmail.com");
    registerInTransaction(clientDao, client);
  }
  
  private void closeEntityManager() {
    entityManager.close();
  }

  private void registerInTransaction(RegisterDAO dao, StoreModel model) {
    entityManager.getTransaction().begin();
    dao.register(model);
    entityManager.getTransaction().commit();
  }
  
  private void displayRegisteredOrder(long id) {
    Order order = orderDao.searchAOrderById(id);
    System.out.println("\nOder successfully registered!!!" + order);
  }
  
  public void displayTotalValueSold() {
    BigDecimal totalValueSold = orderDao.totalValueSold();
    System.out.println("Total value of the order is: " + totalValueSold);
  }
}
