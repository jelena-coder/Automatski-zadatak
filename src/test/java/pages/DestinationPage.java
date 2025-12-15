package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DestinationPage extends BasePage {

    private final By charterCards =
            By.cssSelector("[data-testid='charter-card']");

    private final By charterName =
            By.cssSelector("h3 a");

    private final By boatLength =
            By.xpath(".//*[contains(text(),'ft')]");

    private final By maxPeople =
            By.xpath(".//*[contains(text(),'people')]");

    private final By price =
            By.xpath(".//*[contains(text(),'Trips from')]");

    private final By wishlistIcon =
            By.cssSelector("[data-testid='wishlist-tooltip']");

    private final By tooltipText =
            By.xpath("//*[contains(text(),'Add listing to wishlist')]");

    private final By seeAvailabilityButton =
            By.xpath(".//button[contains(text(),'See availability')]");

    private final By sortDropdown =
            By.cssSelector("[data-testid='sort-dropdown']");

    private final By priceLowest =
            By.xpath("//*[text()='Price (Lowest)']");

    private final By priceHighest =
            By.xpath("//*[text()='Price (Highest)']");

    private final By applyButton =
            By.xpath("//button[contains(text(),'Apply')]");

    public DestinationPage(WebDriver driver) {
        super(driver);
    }

    public void waitForChartersToLoad() {
        wait.until(d -> driver.findElements(charterCards).size() >= 10);
    }

    public void validateFirstCharterCard() {
        WebElement first = driver.findElements(charterCards).get(0);

        assert first.findElement(charterName).isDisplayed();
        assert first.findElement(boatLength).isDisplayed();
        assert first.findElement(maxPeople).isDisplayed();
        assert first.findElement(price).isDisplayed();
        assert first.findElement(seeAvailabilityButton).isDisplayed();

        hover(wishlistIcon);
        waitForVisible(tooltipText);
    }

    public List<Integer> getAllPrices() {
        List<Integer> prices = new ArrayList<>();

        for (WebElement card : driver.findElements(charterCards)) {
            String priceText = card.findElement(price).getText();
            prices.add(Integer.parseInt(
                    priceText.replaceAll("[^0-9]", "")
            ));
        }
        return prices;
    }

    public void sortByLowestPrice() {
        click(sortDropdown);
        click(priceLowest);
    }

    public void sortByHighestPrice() {
        click(sortDropdown);
        click(priceHighest);
        click(applyButton);
    }

    public void waitForPricesToRefresh() {
    wait.until(driver -> {
        List<Integer> prices = getAllPrices();
        return prices.size() > 0;
    });
}
}

