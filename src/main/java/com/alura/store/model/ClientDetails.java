package com.alura.store.model;

import javax.persistence.Embeddable;

@Embeddable
public class ClientDetails {
  private String name;
  private String email;

  public ClientDetails() {
  }

  public ClientDetails(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

}
