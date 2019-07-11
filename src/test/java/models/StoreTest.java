package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {
    @Test
    public void getStoreName() {
        Store store = setupStore();
        assertEquals("Wochi",store.getStoreName());
    }
    @Test
    public void getLocation() {
        Store store = setupStore();
        assertEquals("24 NE Nairobi",store.getLocation());
    }
    @Test
    public void getstoreCode() {
        Store store = setupStore();
        assertEquals("NKV123EWC",store.getStoreCode());
    }
    @Test
    public void getItemId(){
        Store store = setupStore();
        assertEquals(240,store.getItemId());
    }
    @Test
    public void setId(){
        Store store = setupStore();
        store.setId(4);
        assertNotEquals(1,store.getId());
    }

    public Store setupStore(){
        return new Store("Wochi","24 NE Nairobi","NKV123EWC",240);
    }
}