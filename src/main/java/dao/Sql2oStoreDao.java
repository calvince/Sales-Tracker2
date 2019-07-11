package dao;

import models.Store;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oStoreDao implements StoreDao {
    private final Sql2o sql2o;
    public Sql2oStoreDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Store store) {
        String sql ="INSERT INTO stores (storeName, location,storeCode,itemId) VALUES (:storeName, :location,:storeCode,:itemId)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(store)
                    .executeUpdate()
                    .getKey();
            store.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public Store findById(int id) {
        String sql = "SELECT * FROM stores WHERE id=:id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Store.class);
        }
    }

    @Override
    public void updateStore(int id, String storeName, String location, String storeCode, int itemId) {
        String sql = "UPDATE stores SET (storeName, location,storeCode,itemId) = (:storeName,:location,:storeCode,:itemId)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("storeName",storeName)
                    .addParameter("location", location)
                    .addParameter("storeCode",storeCode)
                    .addParameter("itemId",itemId)
                    .addParameter("itemId", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Store> getAllStore() {
        String sql = "SELECT * FROM stores";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(Store.class);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM stores";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM stores WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }

    }
}
