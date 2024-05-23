package step_definitions;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class GeneralSteps {
    public static SelenideElement catalogButton = $x("//button[contains(@class, 'j-menu-burger-btn')]");
    public static SelenideElement loadFirstProduct = $x("//div[@class='main-page__content']//article[1]");

    @When("пользователь нажимает на \"Фильтры\"")
    public void clickOnCatalog() {
        loadFirstProduct.shouldBe(visible);

        catalogButton.shouldBe(visible, interactable).click();
    }

    @And("пользователь наводит курсор на категорию {string}")
    public void moveToCategory(String category) {
        SelenideElement categoryElement = $(byText(category)).shouldBe(visible, enabled);
        categoryElement.hover();
    }

    @And("пользователь нажимает на подкатегорию 1 {string}")
    public void clickOnSubcategory1(String subcategory1) {
        SelenideElement subcategoryElement = $(byText(subcategory1)).shouldBe(interactable);
        subcategoryElement.click();
    }

    @And("пользователь нажимает на подкатегорию 2 {string}")
    public void clickOnSubcategory2(String subcategory2) {
        SelenideElement subcategoryElement = $(byText(subcategory2)).shouldBe(interactable);
        subcategoryElement.click();
    }
}
