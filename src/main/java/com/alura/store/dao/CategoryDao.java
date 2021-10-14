package com.alura.store.dao;

import javax.persistence.EntityManager;

import com.alura.store.model.Category;

public class CategoryDao implements RegisterDAO<Category> {
  private EntityManager entityManager;
  
  public CategoryDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  
  public void register(Category category) {
    this.entityManager.persist(category);
  }
  
  public void update(Category category) {
    this.entityManager.merge(category);
  }
  
  public void remove(Category category) {
    category = entityManager.merge(category);
    this.entityManager.remove(category);
  }
}
