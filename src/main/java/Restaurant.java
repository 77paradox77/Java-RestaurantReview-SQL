import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Restaurant {
  private int id;
  private String name;
  private String description;
  private int cuisine_id;

  public Restaurant(String name, String description, int cuisine_id) {
    this.name = name;
    this.description = description;
    this.cuisine_id = cuisine_id;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public int getCuisineId() {
    return cuisine_id;
  }

  public static List<Restaurant> all() {
    String sql = "SELECT id, name, description FROM restaurant";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if(!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getDescription().equals(newRestaurant.getDescription()) &&
        this.getCuisineId() == newRestaurant.getCuisineId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurant(name, description, cuisine_id) VALUES (:name, :description, :cuisine_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("description", this.description)
      .addParameter("cuisine_id", this.cuisine_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurant where id=:id";
      Restaurant restaurant = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Restaurant.class);
    return restaurant;
    }
  }
  public List<Review> getReviews() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM review where restaurant_id=:id";
      return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Review.class);
    }
  }
}
