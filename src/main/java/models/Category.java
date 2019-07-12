package models;

import java.util.Objects;

public class Category {
    private String categoryName;
    private int id;

    public Category(String categoryName) {
       this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (getId() != category.getId()) return false;
        return getCategoryName().equals(category.getCategoryName());
    }

    @Override
    public int hashCode() {
        int result = getCategoryName().hashCode();
        result = 31 * result + getId();
        return result;
    }
}