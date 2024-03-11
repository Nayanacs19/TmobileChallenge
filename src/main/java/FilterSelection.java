import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FilterSelection {
    static WebDriver driver;
    private static By Deals = By.cssSelector("#mat-expansion-panel-header-2");
    private static By Brands = By.cssSelector("#mat-expansion-panel-header-3");
    private static By Op = By.cssSelector("#mat-expansion-panel-header-4");

    public static void selectFilter(String...args){
        if (args[0].equalsIgnoreCase("Deals")) {
            click(Deals);
        } else if (args[0].equalsIgnoreCase("Brands")) {
            click(Brands);
        } else {
            click(Op);
        }

        if (args[1].equalsIgnoreCase("all")) {
            clickAllOptions(args[0]);
        } else {
            for (int i = 1; i <args.length ; i++) {
                String xpath = "//span[contains(text(),'"+args[i]+"')]//ancestor::label";
                click(By.xpath(xpath));
            }
        }
    }

    private static void clickAllOptions(String filter){
        String xpth = "//div[@aria-label='"+filter+"']//label";
        List<WebElement> elements = driver.findElements(By.xpath(xpth));
       for (WebElement e: elements){
           e.click();
       }
    }
    private static void click(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    public static void main(String[] args) {
       driver = new FirefoxDriver();
       driver.get("https://www.t-mobile.com/tablets");
       //selectFilter("Deals","New", "Special offer");
      // selectFilter("Brands", "All")
        // selectFilter("Brands", "Alcatel", "Apple");
        selectFilter("Operating System", " iPadOS");
    }
}
