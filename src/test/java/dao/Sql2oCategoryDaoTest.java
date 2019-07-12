package dao;

import models.Category;
import models.Store;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCategoryDaoTest {
    private static Connection conn;
    private static Sql2oCategoryDao categoryDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/saletracker_test";
        Sql2o sql2o = new Sql2o(connectionString, "calvo-linus","123");
        categoryDao = new Sql2oCategoryDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        categoryDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
    }

    @Test
    public void addingCategorySetsId() throws Exception {
        Category testCategory = setupCategory();
        categoryDao.add(testCategory);
        int theId = testCategory.getId();
        assertEquals(theId, testCategory.getId());
    }

//    @Test
//    public void getAll() throws Exception {
//        Category category1 = setupCategory();
//        Category category2 = setupCategory();
//        assertEquals(2, CategoryDao.getAll().size());
//    }

//    @Test
//    public void getAllCategoryByStore() throws Exception {
//        Store testStore = setupStore();
//        Store otherStore = setupStore(); //add in some extra data to see if it interferes
//        Category category1 = setupCategoryForStore(testStore);
//        Category category2 = setupCategoryForStore(testStore);
//        Category CategoryForOtherStore = setupCategoryForStore(otherStore);
//        assertEquals(2, categoryDao.getAllCategoryByStore(testStore.getId()).size());
//    }

    @Test
    public void deleteById() throws Exception {
        Category testCategory = setupCategory();
        Category otherCategory = setupCategory();
        categoryDao.add(testCategory);
        categoryDao.add(otherCategory);
        assertEquals(2, categoryDao.getAll().size());
        categoryDao.deleteById(testCategory.getId());
        assertEquals(1, categoryDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Category testCategory = setupCategory();
        Category otherCategory = setupCategory();
        categoryDao.clearAll();
        assertEquals(0, categoryDao.getAll().size());
    }

    //helpers

    public Category setupCategory() {
        return new Category("great");
    }

//    public Category setupCategoryForStore(Store store) {
//        Category category = new Category("great", "Kim", 4, Store.getId());
//        categoryDao.add(category);
//        return category;
//    }
//
//    public Store setupStore() {
//        Store store = new Store("Fish Witch", "214 NE Broadway", "97232", 503-402-9874);
//        storeDao.add(store);
//        return store;
//    }
}