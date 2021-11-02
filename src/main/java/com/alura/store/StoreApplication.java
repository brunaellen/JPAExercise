package com.alura.store;

import javax.persistence.EntityManager;

import com.alura.store.dao.OrderDao;
import com.alura.store.model.Order;
import com.alura.store.service.OrderRegistration;
import com.alura.store.service.ProductRegistration;
import com.alura.store.util.JPAUtil;

public class StoreApplication {
  public static void main(String[] args) {
    OrderRegistration orderRegistration =  new OrderRegistration();
    orderRegistration.createSampleOrder();
    
    ProductRegistration productRegistration = new ProductRegistration();
    productRegistration.registerAProduct();
    
    EntityManager entityManager = JPAUtil.getEntityManager();
    OrderDao orderDao = new OrderDao(entityManager);
    Order order = orderDao.seachOrderByClient(1l);
    entityManager.close();
    System.out.println(order.getClient().getName());
    
  }
}
