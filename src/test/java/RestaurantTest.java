import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class RestaurantTest {

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
  //     con.createQuery(deleteReviewsQuery).executeUpdate();      con.createQuery(deleteRestaurantsQuery).executeUpdate();
  //     con.createQuery(deleteCuisineQuery).executeUpdate();
  //   }
  // }

  @Test
  public void Restaurant_instantiatesCorrectly_True() {
    Restaurant testRestaurant = new Restaurant("Jojo's", "Text here", 1);
    assertEquals(true, testRestaurant instanceof Restaurant);
  }

  @Test
  public void getName_restaurantInstantiatesWithName_String() {
    Restaurant testRestaurant = new Restaurant("Jojo's", "Text here", 1);
    assertEquals("Jojo's", testRestaurant.getName());
  }

  @Test
  public void getDescription_restaurantInstantiatesWithDescription_String() {
    Restaurant testRestaurant = new Restaurant("Jojo's", "Text here", 1);
    assertEquals("Text here", testRestaurant.getDescription());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueifNameIsTheSame() {
  Restaurant firstRestaurant = new Restaurant("Jojo's",
  "Text here", 1);
  Restaurant secondRestaurant = new Restaurant("Jojo's", "Text here", 1);
  assertTrue(firstRestaurant.equals(secondRestaurant));
  }

// GET ID TEST
  @Test
public void save_assignsIdToObject() {
  Restaurant testRestaurant = new Restaurant("Chili's", "Texthere", 1);
  testRestaurant.save();
  Restaurant savedRestaurant = Restaurant.all().get(0);
  assertEquals(testRestaurant.getId(), savedRestaurant.getId());
  }

@Test
public void find_findsRestaurantInDatabase_true() {
  Restaurant testRestaurant = new Restaurant("AppleBee's", "Good Happy Hour", 1);
  testRestaurant.save();
  Restaurant savedRestaurant = Restaurant.find(testRestaurant.getId());
  assertTrue(testRestaurant.getId() == savedRestaurant.getId());
  }



// NEEDS TO BE WRITTEN FOR REVIEWTEST AND CUISINETEST
  @Test
  public void save_savesCuisineIdIntoDB_true() {
    Cuisine testCuisine = new Cuisine("Franchise");
    testCuisine.save();
    Restaurant testRestaurant = new Restaurant("AppleBee's", "It was okay", testCuisine.getId());
    testRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(testRestaurant.getId());
    assertEquals(savedRestaurant.getCuisineId(), testCuisine.getId());
  }

}
