import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/restaurant_review_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteReviewsQuery = "DELETE FROM review *;";
      String deleteRestaurantsQuery = "DELETE FROM restaurant *;";
      String deleteCuisineQuery = "DELETE FROM cuisine *";
      con.createQuery(deleteReviewsQuery).executeUpdate();
      con.createQuery(deleteRestaurantsQuery).executeUpdate();
      con.createQuery(deleteCuisineQuery).executeUpdate();
    }
  }
}
