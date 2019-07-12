import dao.*;
import models.Category;
import models.Item;
import models.Store;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import models.Store;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import exceptions.ApiException;


import static spark.Spark.*;




public class App {

    public static void main(String[]args){
        staticFileLocation("/public");


        Sql2oStoreDao storeDao;
        Sql2oCategoryDao categoryDao;
        Sql2oItemDao itemDao;
        Connection conn;
        String connectionString = "jdbc:postgresql://localhost:5432/salestracker";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        storeDao = new Sql2oStoreDao(sql2o);
        categoryDao = new Sql2oCategoryDao(sql2o);
        itemDao = new Sql2oItemDao(sql2o);




        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        post("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String storeName = request.queryParams("storeName");
            String location = request.queryParams("description");
            String storeCode = request.queryParams("storeCode");
            int itemId = Integer.parseInt(request.queryParams("itemId"));
            Store newStore = new Store(storeName, location, storeCode, itemId );
            storeDao.add(newStore);
            model.put("stores", storeDao.getAllStore());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        get("/category-form", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "category.hbs");
        }, new HandlebarsTemplateEngine());

        post("/category/new",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            String categoryName=req.queryParams("categoryName");
            Category newCategory= new Category(categoryName);
            categoryDao.add(newCategory);
            model.put("categories",categoryDao.getAll());
            return new ModelAndView(model,"category.hbs");
        },new HandlebarsTemplateEngine());



        get("/item-form", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "items.hbs");
        }, new HandlebarsTemplateEngine());

        post("/item/new",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            String itemName=req.queryParams("itemName");
            int quantity= Integer.parseInt(req.queryParams("quantity"));
            int categoryId= Integer.parseInt(req.queryParams("CategoryId"));
            Item newItem= new Item(itemName, quantity,categoryId);
            itemDao.add(newItem);
            model.put("items",itemDao.getAll());
            return new ModelAndView(model,"items.hbs");
        },new HandlebarsTemplateEngine());

    }
}
