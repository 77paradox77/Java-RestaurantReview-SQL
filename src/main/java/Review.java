import java.util.List;
import org.sql2o.*;

public class Review {
  private int id;
  private String info;
  private int restaurant_id;

  public Review(String info, int restaurant_id) {
    this.info = info;
    this.restaurant_id = restaurant_id;
  }

  public String getInfo() {
    return info;
  }

  public int getId() {
    return id;
  }

  public int getRestaurantId() {
    return restaurant_id;
  }

  public static List<Review> all() {
    String sql = "SELECT id, info FROM review";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  @Override
  public boolean equals(Object otherReview) {
    if(!(otherReview instanceof Review)) {
      return false;
    } else {
      Review newReview = (Review) otherReview;
      return this.getInfo().equals(newReview.getInfo()) &&
        this.getRestaurantId() == newReview.getRestaurantId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO review (info, restaurant_id) VALUES (:info, :restaurant_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("info", this.info)
      .addParameter("restaurant_id", this.restaurant_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Review find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM review WHERE id=:id";
      Review review = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Review.class);
     return review;
   }
  }
  
}
