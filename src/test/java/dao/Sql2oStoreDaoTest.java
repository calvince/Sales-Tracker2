package dao;

import models.Store;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oStoreDaoTest {
    private static Sql2oStoreDao storeDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectingString = "jdbc:postgresql://localhost:5432/saletracker_test";
        Sql2o sql2o = new Sql2o(connectingString,"calvo-linus","123");
        storeDao = new Sql2oStoreDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        storeDao.clearAll();
    }
    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }
    @Test
    public void  add() throws Exception{
        Store store = setupStore();
        storeDao.add(store);
        int storeId = store.getId();
        assertEquals(storeId,store.getId());
    }

    @Test
    public void findById() throws Exception {
        Store store = setupStore();
        storeDao.add(store);
        Store store1 = storeDao.findById(store.getId());
        assertEquals(store,store1);
    }
    @Test
    public void updateStore() throws Exception {
        Store store = setupStore();
        storeDao.add(store);
        storeDao.updateStore(store.getId(),"Wezi","24 NE Nairobi","NKV123EWC",240);
        Store store1 = storeDao.findById(store.getId());
        assertEquals("Wezi",store1.getStoreName());
    }

    @Test
    public void getAllStore() {
        Store store = setupStore();
        storeDao.add(store);
        Store store1 = setupStore();
        storeDao.add(store1);
        assertTrue(storeDao.getAllStore().contains(store));
        assertTrue(storeDao.getAllStore().contains(store1));
    }

    @Test
    public void clearAll() {
        Store store = setupStore();
        storeDao.add(store);
        storeDao.clearAll();
        assertEquals(0,storeDao.getAllStore().size());
    }


    public Store setupStore(){
        return new Store("Wochi","24 NE Nairobi","NKV123EWC",240);
    }
}