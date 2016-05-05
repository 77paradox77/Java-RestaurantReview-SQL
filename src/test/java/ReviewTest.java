import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class ReviewTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();
  // @Before
  // public void setUp() {
  //   DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/restaurant_review_test", null, null);
  // }
  //
  // @After
  // public void tearDown() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String deleteReviewsQuery = "DELETE FROM review *;";
  //     String deleteRestaurantsQuery = "DELETE FROM restaurant *;";
  //     String deleteCuisineQuery = "DELETE FROM cuisine *;";
  //     con.createQuery(deleteReviewsQuery).executeUpdate();
  //     con.createQuery(deleteRestaurantsQuery).executeUpdate();
  //     con.createQuery(deleteCuisineQuery).executeUpdate();
  //   }
  // }
  @Test
  public void Review_instantiatesCorrectly_True() {
    Review testReview = new Review("It was good.", 3);
    assertEquals(true, testReview instanceof Review);
  }

  @Test
  public void getInfo_reviewInstantiatesWithInfo_String() {
    Review testReview = new Review("It was good.", 3);
    assertEquals("It was good.", testReview.getInfo());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Review.all().size(), 0);
  }


// GET ID TEST
  @Test
  public void save_assignsIdToObject() {
    Review myReview = new Review("It was meh.", 1);
    myReview.save();
    Review savedReview = Review.all().get(0);
    assertEquals(myReview.getId(), savedReview.getId());
}

// GET rEVIEW FROM DATABASE
  @Test
  public void find_findsReviewInDatabase_true() {
    Review myReview = new Review("It was so so", 1);
    myReview.save();
    Review savedReview = Review.find(myReview.getId());
    assertTrue(myReview.equals(savedReview));
  }

  @Test
  public void save_savesRestaurantIdIntoDB_true() {
    Cuisine myCuisine = new Cuisine("Mongolian");
    myCuisine.save();
    Restaurant myRestaurant = new Restaurant("Mongolian Grill", "Noodley", myCuisine.getId());
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertEquals(savedRestaurant.getCuisineId(), myCuisine.getId());
    Review myReview = new Review("It was sweet!", myRestaurant.getId());
    myReview.save();
    Review savedReview = Review.find(myReview.getId());
    assertEquals(savedReview.getRestaurantId(), myRestaurant.getId());
  }

  @Test
  public void getReviews_retrievesAllReviewsFromDatabase_ReviewsList() {
    Cuisine myCuisine = new Cuisine("Korean");
    myCuisine.save();
    Restaurant myRestaurant = new Restaurant("Bo Sung", "A Korean Delight", myCuisine.getId());
    myRestaurant.save();
    Restaurant[] restaurants = new Restaurant[] { myRestaurant };
    Review firstReview = new Review("It was super spicy!", myRestaurant.getId());
    firstReview.save();
    Review secondReview = new Review("Never again.", myRestaurant.getId());
    secondReview.save();
    Review[] reviews = new Review[] { firstReview, secondReview };
    assertTrue(myRestaurant.getReviews().containsAll(Arrays.asList(reviews)));
  }

}
