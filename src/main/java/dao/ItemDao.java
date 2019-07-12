package dao;

import models.Item;
import models.Store;

import java.util.List;

public interface ItemDao {
    //Add new for a department
    void add(Item item);

    //Find news by the id
    Item findById(int id);

    //Update department news
    void updateItem(int id,String itemName,int quantity,int categoryId);

    //Get all department news by department id
    List<Item> getAll();

    void clearAll();

    //deleteById
    void deleteById(int id);
}
