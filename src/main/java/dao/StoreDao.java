package dao;

import models.Store;

import java.util.List;

public interface StoreDao {
    //Add new for a department
    void add(Store store);

    //Find news by the id
    Store findById(int id);

    //Update department news
    void updateStore(int id,String storeName , String location, String storeCode,int itemId);

    //Get all department news by department id
    List<Store> getAllStore();

    void clearAll();

    //deleteById
    void deleteById(int id);

}
