package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DestinationPage;
import pages.SitemapPage;
import utils.PriceUtils;

import java.util.List;

public class DestinationPageTest extends BaseTest {

    @Test
    public void verifyDestinationPageSortingAndUIElements() {

        SitemapPage sitemap = new SitemapPage(driver);
        DestinationPage destination = new DestinationPage(driver);

        sitemap.open();
        sitemap.clickAnyTopDestination();

        destination.waitForChartersToLoad();
        destination.validateFirstCharterCard();

        destination.sortByLowestPrice();
        destination.waitForPricesToRefresh();
        List<Integer> lowestPrices = destination.getAllPrices();
        Assert.assertTrue(
                PriceUtils.isSortedAscending(lowestPrices),
                "Prices are NOT sorted ascending"
        );

        destination.sortByHighestPrice();
        destination.waitForPricesToRefresh();
        List<Integer> highestPrices = destination.getAllPrices();
        Assert.assertTrue(
                PriceUtils.isSortedDescending(highestPrices),
                "Prices are NOT sorted descending"
        );
    }
}

