package com.alura.store;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.alura.store.dao.OrderDao;
import com.alura.store.dao.ProductDao;
import com.alura.store.model.Order;
import com.alura.store.model.Product;
import com.alura.store.service.OrderRegistration;
import com.alura.store.service.ProductRegistration;
import com.alura.store.util.JPAUtil;

public class StoreApplication {
  public static void main(String[] args) {
    OrderRegistration orderRegistration =  new OrderRegistration();
    orderRegistration.createSampleOrder();
    
    ProductRegistration productRegistration = new ProductRegistration();
    productRegistration.registerAProduct();
    
    //testing the a query using JOIN FETCH and (lazy) loading data
    EntityManager entityManager = JPAUtil.getEntityManager();
    OrderDao orderDao = new OrderDao(entityManager);
    Order order = orderDao.seachOrderByClient(1l);
    entityManager.close();
    System.out.println(order.getClient().getName());
    
    //testing a query using Criteria API
    EntityManager secondEntityManager = JPAUtil.getEntityManager();
    ProductDao productDao = new ProductDao(secondEntityManager);
    List<Product> products = productDao.searchProductByGivenParameter("Apple MacBook Air", new BigDecimal(800.00), null);
    
    products.forEach(product -> System.out.print(product.getName()));
  }
}
