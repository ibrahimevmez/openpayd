package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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

    @Given("Kullanici URL e gider")
    public void kullanici_url_e_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        ReusableMethods.waitFor(2);
    }
    @When("Kullanici arama kutusuna urun yazar ve arama yapar")
    public void kullanici_arama_kutusuna_urun_yazar() {
        ReusableMethods.clickWithJS(dashboardPage.searchbox);
        ReusableMethods.waitFor(1);
        dashboardPage.searchbox.sendKeys("ürün", Keys.ARROW_DOWN, Keys.ENTER);
        ReusableMethods.waitFor(1);
        robot.mouseWheel(4);
        ReusableMethods.waitFor(1);
        ReusableMethods.clickWithJS(dashboardPage.urun);

    }
    @Then("Kullanici bes urun ekler")
    public void kullanici_bes_urun_ekler() {
        ReusableMethods.clickWithJS(dashboardPage.dropdown);
        Select select = new Select(dashboardPage.dropdown);
        select.selectByIndex(4);
        ReusableMethods.waitFor(1);
        ReusableMethods.clickWithJS(dashboardPage.sepeteEkle);
    }
    @Then("Kullanici sepetinize eklenmistir yazisini gorur")
    public void kullanici_sepetinize_eklenmistir_yazisini_gorur() {
        ReusableMethods.waitFor(1);
    Assert.assertTrue(dashboardPage.sepeteEklenmistir.isDisplayed());
    }
    @Then("Kullanici sepette bes urun oldugunu kontrol eder")
    public void kullanici_sepette_bes_urun_oldugunu_kontrol_eder() {
        ReusableMethods.waitFor(1);
        Assert.assertTrue(dashboardPage.urunSayisi.getText().contains("5"));
    }
    @Then("Uygulamayi kapat")
    public void uygulamayi_kapat() {
        ReusableMethods.waitFor(1);
        Driver.getDriver().close();
    }

}
