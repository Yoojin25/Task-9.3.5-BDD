package step_definitions;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.asserts.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class AddToBasketSteps {
    public static SelenideElement cleanersCatalogNext = $x("//a[contains(@class, 'j-menu-drop-link') and contains(text(), 'Пылесосы и пароочистители')]");
    public static SelenideElement cleanersCatalogTitle = $x("//h1[@class='catalog-title']");
    public static SelenideElement filterPath = $x("//div[@class='breadcrumbs__container']");
    public static SelenideElement addToBasketButton = $x("//article[1]//a[@href='/lk/basket']");
    public static SelenideElement cookiesButton = $x("//button[@class='cookies__btn btn-minor-md']");
    public static SelenideElement counterOfBasket = $x("//span[@class='navbar-pc__notify']");
    public static SelenideElement basketButton = $x("//span[contains(@class, 'navbar-pc__icon--basket')]");
    public static SelenideElement productNameInBasket = $x("//span[@class='good-info__good-name']");
    public static SelenideElement productPriceInBasket = $x("//div[contains(@data-link, 'priceSumWith')]");
    public static SelenideElement productNameOnPage = $x("//article[1]//span[@class='product-card__name']");
    public static SelenideElement productPriceOnPage = $x("//article[1]//ins");
    public static SelenideElement priceAllProducts = $x("//span[@class='b-right']");
    public static SelenideElement totalPrice = $x("//span[contains(@data-link, 'totalPaymentFee')]");
    public static SelenideElement orderButton = $x("//button[@name='ConfirmOrderByRegisteredUser']");
    public static SelenideElement chatButton = $x("//button[contains(@class, 'btn-chat')]");

    private static String productNameOnPageText;
    private static String productPriceOnPageText;
    private static String productNameInBasketText;
    private static String productPriceInBasketText;

    @And("пользователь нажимает на подкатегорию 3 {string}")
    public void clickOnCleanersNext(String subcategory3) {
        cleanersCatalogNext.shouldHave(text(subcategory3)).shouldBe(interactable).click();
    }

    @Then("пользователь должен перейти на страницу с {string}")
    public void goToCleanersPage(String subcategory3) {
        Assert.textPresence(cleanersCatalogTitle, subcategory3);
    }

    @And("путь фильтра должен быть Главная - {string} - {string} - {string}")
    public void checkFilterPath(String category, String subcategory1, String subcategory2) {
        Assert.textPresence(filterPath, "Главная\n" + category + "\n" + subcategory1 + "\n" + subcategory2);
    }

    @And("пользователь нажимает на кнопку \"В корзину\" у первого товара из списка")
    public void addToBasket() {
        cookiesButton.click();

        addToBasketButton.shouldBe(visible).click();

        productNameOnPageText = productNameOnPage.getText();
        productPriceOnPageText = productPriceOnPage.getText();
    }

    @And("над логотипом \"Корзина\" в правом верхнем углу должна появиться красная цифра \"1\"")
    public void checkCounterOfBasket() {
        Assert.textPresence(counterOfBasket, "1");
    }

    @When("пользователь нажимает на \"Корзина\"")
    public void clickOnBasket() {
        basketButton.click();

        chatButton.shouldBe(visible);
    }

    @Then("текст и цена товара в корзине должны соответствовать названию и цене товара на странице")
    public void matchNameAndPrice() {
        productNameInBasketText = productNameInBasket.getText();
        productPriceInBasketText = productPriceInBasket.getText();

        Assert.textMatch(productNameOnPageText, productNameInBasketText);
        Assert.textMatch(productPriceOnPageText, productPriceInBasketText);
    }

    @And("\"Итого\" должно быть равно сумме всех товаров")
    public void checkTotalPrice() {
        String priceAllProductsText = priceAllProducts.getText();
        String totalPriceText = totalPrice.getText();

        Assert.textMatch(priceAllProductsText, totalPriceText);
    }

    @And("кнопка \"Заказать\" должна быть активна для нажатия")
    public void checkClickable() {
        Assert.checkVisibleAndClickable(orderButton);
    }
}
