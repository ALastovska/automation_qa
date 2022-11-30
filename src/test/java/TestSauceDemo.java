import io.github.bonigarcia.wdm.WebDriverManager;
import lv.acodemy.constants.Generic;
import lv.acodemy.page_object.InventoryPage;
import lv.acodemy.page_object.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static lv.acodemy.constants.Generic.SAUCE_URL;

public class TestSauceDemo {

    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod(description = "Preconditions")
    public void initialize() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        driver.get(SAUCE_URL);
    }

    @Test(description = "Happy path: Test authorization with standard user")
    public void authorizeTest() {
        driver.get(SAUCE_URL);
        loginPage.authorize("standard_user", "secret_sauce");
        Assert.assertEquals(inventoryPage.getTitleElement().getText(), "PRODUCTS");
    }

    @Test(description = "Failure: Test authorization error message with incorrect credentials")
    public void openProductTest() {
        driver.get(SAUCE_URL);
        loginPage.authorize("standard_user", "secret_sauce");
        Assert.assertEquals(inventoryPage.getTitleElement().getText(), "PRODUCTS");
        inventoryPage.clicklOnProductByLabel("Sauce Labs Bolt T-Shirt");
        System.out.println();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
