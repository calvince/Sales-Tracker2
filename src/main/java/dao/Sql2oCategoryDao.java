package dao;

import models.Category;
import models.Store;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oCategoryDao implements CategoryDao {
    private final Sql2o sql2o;

    public Sql2oCategoryDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(Category category) {
        String sql = "INSERT INTO categories (categoryName) VALUES (:categoryName)"; //if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(category)
                    .executeUpdate()
                    .getKey();
            category.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Category> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM categories")
                    .executeAndFetch(Category.class);
        }
    }

//    @Override
//    public List<Category> getAllCategoryByStore(int categoryId) {
//        try (Connection con = sql2o.open()) {
//            return con.createQuery("SELECT * FROM store WHERE categoryId = :categoryId")
//                    .addParameter("", categoryId)
//                    .executeAndFetch(Category.class);
//        }
//    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from categories WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from categories";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}