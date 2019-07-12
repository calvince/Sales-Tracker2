package dao;

import models.Item;
import models.Store;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oItemDao implements ItemDao {
    private final Sql2o sql2o;
    public Sql2oItemDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Item item) {
        String sql ="INSERT INTO items (itemName, quantity,categoryId) VALUES (:itemName, :quantity,:categoryId)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(item)
                    .executeUpdate()
                    .getKey();
            item.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Item findById(int id) {
        String sql = "SELECT * FROM items WHERE id=:id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Item.class);
        }
    }

    @Override
    public void updateItem(int id, String itemName, int quantity, int categoryId) {
        String sql = "UPDATE items SET (itemName, quantity,categoryId) = (:itemName, :quantity,:categoryId) WHERE id=:id";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("itemName",itemName)
                    .addParameter("quantity", quantity)
                    .addParameter("categoryId",categoryId)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<Item> getAll() {
        String sql = "SELECT * FROM items";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(Item.class);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM items";
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
            String sql = "DELETE FROM items WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }

    }
}
