package com.alura.store.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client implements StoreModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Embedded
  private ClientDetails clientDetails;
  
  public Client() {
  }
  
  public Client (String name, String email) {
    this.clientDetails = new ClientDetails(name, email);
  }

  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public ClientDetails getClientDetails() {
    return clientDetails;
  }
  
  public String getName() {
    return this.clientDetails.getName();
  }
  
  public String getEmail() {
    return this.clientDetails.getEmail();
  }
}
