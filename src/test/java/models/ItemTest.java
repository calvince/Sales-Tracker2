package models;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void getItemName() {
        Item item = setupItem();
        assertEquals("soap",item.getItemName());
    }

    @Test
    public void getQuantity() {
        Item item = setupItem();
        assertEquals(250,item.getQuantity());
    }
    @Test
    public void  getCategoryId() {
        Item item = setupItem();
        assertEquals(2,item.getCategoryId());
    }

    public Item setupItem(){
        return new Item("soap",250,2);
    }
}