package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardPage {
    public DashboardPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//input[@id='sp-cc-accept']")
    public WebElement accept;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    public WebElement searchbox;


    @FindBy(xpath = "//div[@class='s-main-slot s-result-list s-search-results sg-row']")
    public WebElement laptopTotal;

    @FindBy(xpath = "//span[@class='a-price-symbol']")
    public WebElement discountLabel;

    @FindBy(xpath = "//div[@id='availability']//span[contains(text(), 'In Stock')]")
    public WebElement inStock;

    @FindBy(id = "nav-cart")
    public WebElement cart;



}
