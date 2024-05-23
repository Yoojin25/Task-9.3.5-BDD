package step_definitions;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.asserts.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FiltersSteps {
    public static SelenideElement laptopsCatalogTitle = $x("//h1[@class='catalog-title']");
    public static SelenideElement allFilters = $x("//button[contains(@class, 'dropdown-filter__btn--all')]");
    public static SelenideElement minPrice = $x("//input[@name='startN']");
    public static SelenideElement maxPrice = $x("//input[@name='endN']");
    public static SelenideElement filterResult = $x("//button[@class='filters-desktop__btn-main btn-main']");
    public static SelenideElement resetFilters = $x("//button[@data-link='{on resetAllFiltres}']");
    public static SelenideElement numberOfProductsFilters = $x("//p[@class='filters-desktop__count-goods']");
    public static SelenideElement numberOfProductsCatalog = $x("//span[@class='goods-count']");
    public static SelenideElement radioMax3DaysActive = $x("//span[@class='radio-with-text__text' and text()='до 3 дней']/..");
    public static SelenideElement brandActive = $x("//span[@class='checkbox-with-text__text' and text()='Apple']/..");
    public static SelenideElement screenActive = $x("//span[@class='checkbox-with-text__text' and text()='13.3\"']/..");
    public static SelenideElement listOfSelectedFilters = $x("//ul[@class='your-choice__list']");

    @Then("должна открыться страница {string}")
    public void goToLaptopsPage(String pageTitle) {
        Assert.textPresence(laptopsCatalogTitle, pageTitle);
    }

    @When("пользователь нажимает на кнопку \"Все фильтры\"")
    public void clickOnAllFilters() {
        allFilters.shouldBe(interactable).click();
    }

    @And("пользователь применяет фильтр \"Цена\": {string} и {string}")
    public void fillPriceRange(String min, String max) {
        minPrice.shouldBe(visible, interactable).clear();
        minPrice.shouldBe(visible, empty).sendKeys(min);

        maxPrice.shouldBe(visible, interactable).clear();
        maxPrice.shouldBe(visible, empty).sendKeys(max);
    }

    @And("пользователь применяет фильтр \"Срок доставки\": {string}")
    public void selectDeliveryTime(String deliveryTime) {
        SelenideElement deliveryElement = $(byText(deliveryTime)).shouldBe(interactable);
        deliveryElement.click();
    }

    @And("пользователь применяет фильтр \"Бренд\": {string}")
    public void selectBrand(String brand) {
        SelenideElement brandElement = $(byText(brand)).shouldBe(interactable);
        brandElement.click();
    }

    @And("пользователь применяет фильтр \"Диагональ экрана\": {string}")
    public void selectScreen(String screen) {
        SelenideElement screenElement = $(byText(screen)).shouldBe(interactable);
        screenElement.click();
    }

    @And("пользователь нажимает на кнопку \"Показать\"")
    public void filterProducts() {
        filterResult.shouldBe(visible, interactable).click();
    }

    @Then("фильтры {string}, {string}, {string}, {string}, {string} должны отображаться на странице")
    public void selectedFiltersVisible(String minPrice, String maxPrice, String deliveryTime, String brand, String screen) {
        Assert.checkSelectedFiltersVisible(listOfSelectedFilters, minPrice, maxPrice, deliveryTime, brand, screen);
    }

    @And("на странице должна появиться кнопка \"Сбросить все\"")
    public void checkVisible() {
        Assert.checkVisibleAndClickable(resetFilters);
    }

    @And("количество товара на странице должно быть равно количеству товара на странице фильтров")
    public void quantityOfProductsEquals() {
        allFilters.shouldBe(interactable).click();

        String numberOfProductsFiltersText = numberOfProductsFilters.getText();
        String numberOfProductsCatalogText = numberOfProductsCatalog.getText();

        Assert.textMatch(numberOfProductsFiltersText, numberOfProductsCatalogText);
    }

    @And("фильтры должны активироваться")
    public void filterIsActivated() {
        Assert.checkFilterIsActivated(radioMax3DaysActive, "class", "radio-with-text j-list-item selected");
        Assert.checkFilterIsActivated(brandActive, "class", "checkbox-with-text j-list-item selected");
        Assert.checkFilterIsActivated(screenActive, "class", "checkbox-with-text j-list-item selected");
    }
}
