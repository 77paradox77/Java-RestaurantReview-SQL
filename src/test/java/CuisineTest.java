import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CuisineTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/restaurant_review_test", null, null);
  }
  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteReviewsQuery = "DELETE FROM review *;";
      String deleteRestaurantsQuery = "DELETE FROM restaurant *;";
      String deleteCuisineQuery = "DELETE FROM cuisine *;";
      con.createQuery(deleteReviewsQuery).executeUpdate();
      con.createQuery(deleteRestaurantsQuery).executeUpdate();
      con.createQuery(deleteCuisineQuery).executeUpdate();
    }
  }

  @Test
  public void Cuisine_instantiatesCorrectly_true() {
    Cuisine testCuisine = new Cuisine("Italian");
    assertEquals(true, testCuisine instanceof Cuisine);
  }

  @Test
  public void getCuisineType_cuisineInstantiatesWithCuisineType_String() {
    Cuisine testCuisine = new Cuisine("Italian");
    assertEquals("Italian", testCuisine.getCuisineType());

  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTypesAreTheSame() { //if not the same, will give AssertionError.
    Cuisine firstCuisine = new Cuisine("Indian");
    Cuisine secondCuisine = new Cuisine("Indian");
    assertTrue(firstCuisine.equals(secondCuisine));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAreTheSame() {
    Cuisine myCuisine = new Cuisine("Mexican");
    myCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(myCuisine));
  }

// GET ID TEST
  @Test
  public void save_assignsIdToObject() {
    Cuisine myCuisine = new Cuisine("Indian");
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.all().get(0);
    assertEquals(myCuisine.getId(), savedCuisine.getId());
  }

  // FIND RESTAURANT IN DATABASE
  @Test
  public void find_findsCuisineInDatabase_true() {
    Cuisine myCuisine = new Cuisine("Mexican");
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
    assertTrue(myCuisine.equals(savedCuisine));
  }
}
