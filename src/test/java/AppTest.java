import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Yum!");
  }

  @Test
  public void cuisineIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a new cuisine!"));
    fill("#cuisineType").with("Korean");
    submit(".btn");
    assertThat(pageSource()).contains("Your cuisine has been saved!");
  }
  @Test
  public void cuisineIsDisplayedTest() {
    Cuisine myCuisine = new Cuisine("Korean");
    myCuisine.save();
    String cuisinePath = String.format("http://localhost:4567/cuisines/%d", myCuisine.getId());
    goTo(cuisinePath);
    assertThat(pageSource()).contains("Korean");
  }

  @Test
  public void cusineShowPageDiplayName() {
    goTo("http://localhost:4567/cuisines/new");
    fill("#cuisineType").with("Household cheese");
    submit(".btn");
    click("a", withText("View cuisines")); 
    click("a", withText("Household cheese"));
    assertThat(pageSource()).contains("Household cheese");
     }

}
