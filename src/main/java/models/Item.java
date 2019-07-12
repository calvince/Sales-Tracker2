package models;

public class Item {
    private int id;
    private String itemName;
    private int quantity;
    private int categoryId;

    public Item(String itemName, int quantity, int categoryId) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (getId() != item.getId()) return false;
        if (getQuantity() != item.getQuantity()) return false;
        if (getCategoryId() != item.getCategoryId()) return false;
        return getItemName().equals(item.getItemName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getItemName().hashCode();
        result = 31 * result + getQuantity();
        result = 31 * result + getCategoryId();
        return result;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }
}