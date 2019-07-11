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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;

        Store store = (Store) o;

        if (getItemId() != store.getItemId()) return false;
        if (getId() != store.getId()) return false;
        if (!getStoreName().equals(store.getStoreName())) return false;
        if (!getLocation().equals(store.getLocation())) return false;
        return getStoreCode().equals(store.getStoreCode());
    }

    @Override
    public int hashCode() {
        int result = getStoreName().hashCode();
        result = 31 * result + getLocation().hashCode();
        result = 31 * result + getStoreCode().hashCode();
        result = 31 * result + getItemId();
        result = 31 * result + getId();
        return result;
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
