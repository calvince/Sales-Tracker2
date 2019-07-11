package models;

public class Store {
    private String storeName;
    private String location;
    private String storeCode;
    private int itemId;
    private int id;


    public Store(String storeName , String location, String storeCode,int itemId){
        this.storeName = storeName;
        this.location = location;
        this.storeCode = storeCode;
        this.itemId = itemId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getLocation() {
        return location;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public int getItemId() {
        return itemId;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
}
