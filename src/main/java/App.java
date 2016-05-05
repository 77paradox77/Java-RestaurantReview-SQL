import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.ArrayList;
import static spark.Spark.*;

public class App {

  public static void main (String[] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("restaurants", request.session().attribute("restaurants"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);

    }, new VelocityTemplateEngine());

    get("/cuisines/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/cuisines.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisines", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      String cuisineType = request.queryParams("cuisineType");
      Cuisine newCuisine = new Cuisine(cuisineType);
      newCuisine.save(); //*** ADDED FOR DB VERSION ***

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      model.put("cuisine", cuisine);
      model.put("template", "templates/cuisine.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/:id/restaurants/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      model.put("cuisine", cuisine);
      model.put("template", "templates/restaurant-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.queryParams("cuisine_id")));
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      // ** THIS SECTION UPDATED FOR DB VERSION ***
      Restaurant newRestaurant = new Restaurant(name, description, cuisine.getId());
      newRestaurant.save();

      model.put("cuisine", cuisine);
      model.put("template", "templates/success-restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    }
  }
