import models.Store;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import dao.Sql2oStoreDao;
import models.Store;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import exceptions.ApiException;


import static spark.Spark.*;




public class App {
//
//    static int getHerokuAssignedPort() {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        if (processBuilder.environment().get("PORT") != null) {
//            return Integer.parseInt(processBuilder.environment().get("PORT"));
//        }
//        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
//    }
    public static void main(String[]args){
        staticFileLocation("/public");
//        port(getHerokuAssignedPort());

        Sql2oStoreDao storeDao;
        Connection conn;
        String connectionString = "jdbc:postgresql://localhost:5432/salestracker";
        Sql2o sql2o = new Sql2o(connectionString, "xkddftxsfraqws", "5f5d3259bc8ebb3621205bc08f55d9ecf0df2c958d5f2aef0253555a9c22f619");
        storeDao = new Sql2oStoreDao(sql2o);


        get("/stores", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "store-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/stores", (request, response) -> {
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

    }
}
