package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void getCategoryName() {
        Category category = setupCategory();
        assertEquals("Fashion",category.getCategoryName());
    }


    public Category setupCategory(){
        return new Category("Fashion");
    }


    @Test
    public void getCategoryname() {
    }

    @Test
    public void getCategory_Id() {
    }

    @Test
    public void setCategoryname() {
    }

    @Test
    public void setCategory_Id() {
    }

    @Test
    public void equals1() {
    }

    @Test
    public void hashCode1() {
    }
}