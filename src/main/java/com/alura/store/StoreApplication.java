package com.alura.store;

import com.alura.store.service.OrderRegistration;
import com.alura.store.service.ProductRegistration;

public class StoreApplication {
  public static void main(String[] args) {
    OrderRegistration orderRegistration =  new OrderRegistration();
    orderRegistration.createSampleOrder();
    
    ProductRegistration productRegistration = new ProductRegistration();
    productRegistration.registerAProduct();
  }
}
