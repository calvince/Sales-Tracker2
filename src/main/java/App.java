import models.Store;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[]args){
        ProcessBuilder process =new ProcessBuilder();
        Integer port;

        if(process.environment().get("PORT")!= null){
            port = Integer.parseInt(process.environment().get("PORT"));

        }
        else{
            port =4567;
        }
        port(port);

        staticFileLocation("/public");

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
            model.put("stores", storeDao.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
