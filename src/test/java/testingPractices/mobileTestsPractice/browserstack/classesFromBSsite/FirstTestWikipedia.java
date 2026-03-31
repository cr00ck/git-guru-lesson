package testingPractices.mobileTestsPractice.browserstack.classesFromBSsite;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

// это все я скачал в BrowserStack https://app-automate.browserstack.com/qig/get-started и просто тут вставил класс


public class FirstTestWikipedia extends TestBase {

    @Test
    void SearchAppiumtest() throws IOException, InterruptedException {


//        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
//                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));
//        searchElement.click();
        step("Type Appium in search", () -> {
                    $(accessibilityId("Search Wikipedia")).click();

//        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
//                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
//        insertTextElement.sendKeys("BrowserStack");
                    $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
                });

//        Thread.sleep(5000);
        step("Assert that content found ", () -> {
//        List<WebElement> allProductsName = driver.findElements(AppiumBy.className("android.widget.TextView"));
//        assertTrue(allProductsName.size() > 0);
        $$(className("android.widget.TextView")).shouldHave(sizeGreaterThan(0));
        });

    }
}
