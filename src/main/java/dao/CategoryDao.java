package dao;

import models.Category;

import java.util.List;

public interface CategoryDao {
    //create
    void add(Category category);
    //void addCategoryToStore(Category category, Store store);

    //read
    List<Category> getAll();
    // List<Store> getAllCategoryForAStore(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();
}
