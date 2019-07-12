package dao;

import models.Item;
import models.Store;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oItemDaoTest {
    private static Sql2oItemDao itemDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectingString = "jdbc:postgresql://localhost:5432/saletracker_test";
        Sql2o sql2o = new Sql2o(connectingString,"calvo-linus","123");
        itemDao = new Sql2oItemDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
       itemDao.clearAll();
    }
    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }
    @Test
    public void  add() throws Exception{
        Item item = setupItem();
        itemDao.add(item);
        int itemId = item.getId();
        assertEquals(itemId,item.getId());
    }

    @Test
    public void findById() throws Exception {
        Item item = setupItem();
        itemDao.add(item);
       Item item1 = itemDao.findById(item.getId());
        assertEquals(item,item);
    }
    @Test
    public void updateStore() throws Exception {
        Item item= setupItem();
        itemDao.add(item);
        itemDao.updateItem(item.getId(),"dettol",250,2);
        Item item1 = itemDao.findById(item.getId());
        assertEquals("dettol",item1.getItemName());
    }

    @Test
    public void getAllStore() {
        Item item = setupItem();
        itemDao.add(item);
        Item item1= setupItem();
        itemDao.add(item1);
        assertTrue(itemDao.getAll().contains(item));
        assertTrue(itemDao.getAll().contains(item1));
    }

    @Test
    public void clearAll() {
        Item item = setupItem();
        itemDao.add(item);
        itemDao.clearAll();
        assertEquals(0,itemDao.getAll().size());
    }


    public Item setupItem(){
        return new Item("soap",250,2);
    }
}