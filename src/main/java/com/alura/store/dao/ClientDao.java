package com.alura.store.dao;

import javax.persistence.EntityManager;

import com.alura.store.model.Client;

public class ClientDao implements RegisterDAO<Client>{
  private EntityManager entityManager;
  
  public ClientDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  
  @Override
  public void register(Client client) {
    this.entityManager.persist(client);
  }

  public Client searchAClientById(long id) {
    return entityManager.find(Client.class, id);
  }
}
