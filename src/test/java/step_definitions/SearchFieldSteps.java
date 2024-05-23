package step_definitions;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import steps.asserts.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SearchFieldSteps {
    public static SelenideElement searchField = $x("//input[@id='searchInput']");
    public static SelenideElement searchingResult = $x("//h1");
    public static SelenideElement filterFirst = $x("//button[contains(@class, 'btn--burger')]");
    public static SelenideElement filterByPopularity = $x("//button[contains(@class, 'btn--sorter')]");
    public static SelenideElement firstProductBrand = $x("//article[1]//span[@class='product-card__brand']");
    public static SelenideElement clearButton = $x("//button[contains(@class, 'btn--clear')]");

    @When("пользователь нажимает на поисковую строку")
    public void clickOnSearchField() {
        searchField.shouldBe(visible).click();
    }

    @And("пользователь вводит {string} в поисковую строку и нажимает Enter")
    public void enterRequest(String product) {
        searchField.sendKeys(product, Keys.ENTER);
    }

    @Then("пользователь должен перейти на страницу с текстом {string}")
    public void goToPageOfFoundProduct(String expectedText) {
        Assert.textPresence(searchingResult, expectedText);
    }

    @And("первый фильтр должен быть {string}")
    public void checkFirstFilter(String expectedText) {
        Assert.textPresence(filterFirst, expectedText);
    }

    @And("должен быть применен фильтр {string}")
    public void checkFilterByPopularity(String expectedText) {
        Assert.textPresence(filterByPopularity, expectedText);
    }

    @And("первое устройство из списка должно быть бренда {string}")
    public void checkBrand(String expectedText) {
        Assert.textPresence(firstProductBrand, expectedText);
    }

    @When("пользователь нажимает на крестик в поисковой строке")
    public void clearSearchField() {
        clearButton.click();
    }

    @Then("поисковая строка должна быть пустой")
    public void checkSearchFieldIsCleared() {
        Assert.emptyElement(searchField);
    }
}
