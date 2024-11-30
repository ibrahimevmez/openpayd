package UIstepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.awt.*;

public class ShopStepDefs {
    DashboardPage dashboardPage = new DashboardPage();
    Robot robot = new Robot();

    public ShopStepDefs() throws AWTException {
    }

    @Given("User goes to the URL.")
    public void user_goes_to_the_URL() {
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        ReusableMethods.waitFor(2);
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Amazon"));
        ReusableMethods.clickWithJS(dashboardPage.accept);
        ReusableMethods.waitFor(2);
    }

    @When("User types Laptops in the searchbox and search it.")
    public void user_types_Laptops_in_the_searchbox_and_search_it() {

        ReusableMethods.clickWithJS(dashboardPage.searchbox);
        ReusableMethods.waitFor(1);
        dashboardPage.searchbox.sendKeys("Laptops" + Keys.ENTER);
        ReusableMethods.waitFor(1);

    }

    @Then("User adds the non-discounted products in stock on the first page of the search results to the cart.")
    public void user_adds_the_non_discounted_products_in_stock_on_the_first_page_of_the_search_results_to_the_cart() {
        ReusableMethods.waitFor(1);
        robot.mouseWheel(3);
        List<WebElement> laptops = dashboardPage.laptopTotal;
        for (WebElement laptop : laptops) {
            try {
                WebElement discountLabel = laptop.findElement(By.xpath("//span[@class='a-price-symbol'])"));
                if (discountLabel.isDisplayed()) {
                    continue;
                }
            } catch (NoSuchElementException e) {

            }

            try {
                WebElement stockStatus = laptop.findElement(By.xpath("//div[@id='availability']//span[contains(text(), 'In Stock')]"));
                if (stockStatus.getText().contains("In Stock")) {
                    WebElement addToCartButton = laptop.findElement(By.xpath("//span[contains(text(),'Add to Cart')]"));
                    addToCartButton.click();
                    ReusableMethods.waitForVisibility(By.id("nav-cart"), 2);
                    System.out.println("Laptop added to the cart.");
                }
            } catch (NoSuchElementException e) {

            }
        }

    }

    @Then("User goes to cart and check if the products are the right.")
    public void user_goes_to_cart_and_check_if_the_products_are_the_right() {
        ReusableMethods.waitFor(1);
        ReusableMethods.clickWithJS(dashboardPage.cart);

        ReusableMethods.waitForVisibility(By.id("sc-active-cart"), 2);
        List<WebElement> laptopsInCart = Driver.getDriver().findElements((By.cssSelector(".sc-list-item-content")));

        if (laptopsInCart.size() > 0) {
            System.out.println("Laptops successfully added to the cart.");
        } else {
            System.out.println("No laptops found in the cart.");
        }
    }

    @Then("Close the application.")
    public void close_the_application() {
        ReusableMethods.waitFor(1);
        Driver.getDriver().close();
    }

}

