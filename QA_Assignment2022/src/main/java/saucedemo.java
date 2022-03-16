import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class saucedemo {
    @Test
    public void Test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

//--Login
        WebElement Username = driver.findElement(By.id("user-name"));
        Username.sendKeys("standard_user");
        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys("secret_sauce");
        Thread.sleep(3000);
        WebElement BtnLogin = driver.findElement(By.id("login-button"));
        BtnLogin.click();

//--Add to cart a item from product list
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://www.saucedemo.com/inventory.html");
        WebElement Product = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Product.click();

//--Go to cart and check out the item
        WebElement Keranjang = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Keranjang.click();
        Thread.sleep(3000);
        WebElement BtnCheckout = driver.findElement(By.id("checkout"));
        BtnCheckout.click();

//--Fill check-out information
        WebElement FirstName = driver.findElement(By.id("first-name"));
        FirstName.sendKeys("Resa Nirmaya");
        Thread.sleep(3000);
        WebElement Lastname = driver.findElement(By.id("last-name"));
        Lastname.sendKeys("Sari");
        Thread.sleep(3000);
        WebElement PostalCode = driver.findElement(By.id("postal-code"));
        PostalCode.sendKeys("27361");
        Thread.sleep(3000);
        WebElement BtnContinue = driver.findElement(By.id("continue"));
        BtnContinue.click();
        Thread.sleep(3000);

//--Overview and finish the order
        WebDriverWait wait = new WebDriverWait(driver, 50000);
        String exp = "SauceCard #31337";
        WebElement m = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[2]")));
        m.isDisplayed();
        String act = m.getText();
        System.out.println("SauceCard #31337 "+ act);
        //verify text with Assertion
        Assert.assertEquals(exp, act);
        Thread.sleep(3000);
        WebElement BtnFinish = driver.findElement(By.id("finish"));
        BtnFinish.click();
        Thread.sleep(3000);

//--See Thank you page order
        WebElement msg=driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));
        String text=msg.getText();
        String expectedText = "THANK YOU FOR YOUR ORDER";
        Assert.assertEquals(text,expectedText);
    }
}
