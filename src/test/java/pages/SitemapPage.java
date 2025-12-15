package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SitemapPage extends BasePage {

    private static final String URL =
            "https://nextjs15.dev.fishingbooker.com/sitemap";

    private final By topDestinationsSection =
            By.xpath("//h2[contains(text(),'Top Fishing Destinations')]");

    private final By destinationLinks =
            By.xpath("//h2[contains(text(),'Top Fishing Destinations')]/following::a");

    public SitemapPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
        waitForVisible(topDestinationsSection);
    }

    public void clickAnyTopDestination() {
        driver.findElements(destinationLinks).get(0).click();
    }
}

