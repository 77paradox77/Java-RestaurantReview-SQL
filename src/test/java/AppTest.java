import org.sql2o.*; // for DB support
import org.junit.*; // for @Before and @After

public class AppTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();
//   @Before
// public void setUp() {
//   DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
// }
//
// @After
// public void tearDown() {
//   try(Connection con = DB.sql2o.open()) {
//     String deleteTasksQuery = "DELETE FROM tasks *;";
//     String deleteCategoriesQuery = "DELETE FROM categories *;";
//     con.createQuery(deleteTasksQuery).executeUpdate();
//     con.createQuery(deleteCategoriesQuery).executeUpdate();
//   }
//  }



}
