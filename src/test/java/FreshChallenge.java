import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class FreshChallenge {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws InterruptedException {

        //Maximize browser and wait till page loaded
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        driver.get("https://fresh.com.eg/");

        //Wait till Search bar is displayed and editable
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("searchInput"))));

        //Search about "Fresh Ice Box 8 liter"
        driver.findElement(By.id("searchInput")).sendKeys("Fresh Ice Box 8 liter");

        //Click on first item in search results
        driver.findElement(By.xpath("//div[@class='flex flex-col p-xs']")).click();

        Thread.sleep(2000);

        //Assert that price of product after discount as expected
        String ActualAfterDiscount = driver.findElement(By.xpath("//span[@class='text-2xl font-bold text-green-100']")).getText();
        String ExpectedAfterDicsount = "126 EGP";
        assertEquals(ActualAfterDiscount, ExpectedAfterDicsount, "Price after discount is wrong");

        //Assert that price of product before discount as expected
        String ActualBeforeDiscount = driver.findElement(By.xpath("//span[@class='text-lg ml-4 line-through font-semibold']")).getText();
        String ExpectedBeforeDiscount = "140 EGP";
        assertEquals(ActualBeforeDiscount, ExpectedBeforeDiscount, "Price before discount is wrong");

        //Scroll down till find the "Add to cart" elements then click on it
        WebElement add1 = driver.findElement(By.xpath("//button[@class='btn mb-2 bg-green-100 font-bold text-lg xl:text-xl lg:px-10 md:px-8']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", add1);
        add1.click();

        //Force wait for one second and half till item added to cart and success msg appeared
        Thread.sleep(1500);

        //Assert from success msg when first item added to cart
        String ActualFirstMsg = driver.findElement(By.xpath("//h3[contains(@class,'font-bold text-lg')]")).getText();
        String ExpectedFirstMsg = "Item added to cart successfully";
        assertEquals(ActualFirstMsg, ExpectedFirstMsg, "Item failed to added");

        //Click on "Continue Shopping" button
        driver.findElement(By.xpath("//a[@class='btn btn-inverse btn-outline font-bold text-lg nuxt-link-active']")).click();

        //Search about Second item "Fresh TV screen LED 65"
        driver.findElement(By.id("searchInput")).sendKeys("Fresh TV screen LED 65");

        //Click on first item in search results
        driver.findElement(By.xpath("//div[@class='flex flex-col p-xs']")).click();

        Thread.sleep(2000);

        //Assert that user on right page
        String url = driver.getCurrentUrl();
        String Expurl = "https://fresh.com.eg/products/fresh-tv-screen-led-65-inch-ultra-hd-65lu433rq-android-with-receiver-built-in";
        assertEquals(url, Expurl, "User on right page");

        //Scroll down till reach to "Add to cart" button then click on it
        WebElement add2 = driver.findElement(By.xpath("//button[@class='btn mb-2 bg-green-100 font-bold text-lg xl:text-xl lg:px-10 md:px-8']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", add2);
        add2.click();

        Thread.sleep(1500);

        //Assert from success msg when second item added to cart
        String ActualSecondMsg = driver.findElement(By.xpath("//h3[contains(@class,'font-bold text-lg')]")).getText();
        String ExpectedSecondMsg = "Item added to cart successfully";
        assertEquals(ActualSecondMsg, ExpectedSecondMsg, "Second item failed to added");

        //Click on "Continue Shopping" button
        driver.findElement(By.xpath("//a[@class='btn btn-inverse btn-outline font-bold text-lg nuxt-link-active']")).click();


        //Search about Third item "Washing machine"
        driver.findElement(By.id("searchInput")).sendKeys("Fresh Washing Machine SHATOORA");

        //Click on first item in search results
        driver.findElement(By.xpath("//div[@class='flex flex-col p-xs']")).click();

        Thread.sleep(2000);

        //Assert that user on right page
        String url2 = driver.getCurrentUrl();
        String Expurl2 = "https://fresh.com.eg/products/fresh-washing-machine-shatoora-5-kg";
        assertEquals(url2, Expurl2, "User on right page");

        //Scroll down till reach to "Add to cart" button then click on it
        WebElement add3 = driver.findElement(By.xpath("//button[@class='btn mb-2 bg-green-100 font-bold text-lg xl:text-xl lg:px-10 md:px-8']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", add3);
        add3.click();

        Thread.sleep(1500);

        //Assert from success msg when third item added to cart
        String ActualThirdMsg = driver.findElement(By.xpath("//h3[contains(@class,'font-bold text-lg')]")).getText();
        String ExpectedThirdMsg = "Item added to cart successfully";
        assertEquals(ActualThirdMsg, ExpectedThirdMsg, "Second item failed to added");

        //Click on "Continue Shopping" button
        driver.findElement(By.xpath("//a[@class='btn btn-inverse btn-outline font-bold text-lg nuxt-link-active']")).click();

        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //CLick on Cart
        driver.findElement(By.xpath("//button[@class='hidden lg:flex order-4 lg:order-5 ml-auto lg:ml-sm relative link']")).click();

        Thread.sleep(1500);

        //Remove 3 items from cart
        List<WebElement> a = driver.findElements(By.xpath("//button[@class='text-error min-w-0 min-h-0 self-end']"));
        a.get(0).click();
        a.get(1).click();
        a.get(2).click();
    }
}
