package step_definitions;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import steps.asserts.Assert;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ChangeCitySteps {
    public static SelenideElement addressButton = $x("//span[contains(@class, 'j-geocity-link')]");
    public static SelenideElement addressInput = $x("//input[contains(@class, 'ymaps')]");
    public static SelenideElement firstAddress = $x("(//div[@class='address-item__name'])[1]");
    public static SelenideElement listOfPickPointsTitle = $x("//h2[@class='geo-block__info-title hide-mobile']");
    public static SelenideElement selectedPickPointTitle = $x("//h3[@class='details-self__title']");
    public static SelenideElement addressSelectedPickPoint = $x("//span[@class='details-self__name-text']");
    public static SelenideElement selectButton = $x("//button[@class='details-self__btn btn-main']");
    public static SelenideElement homePageUrl = $x("//base");
    public static SelenideElement addressOnHomePage = $x("//span[@data-wba-header-name='DLV_Adress']");

    private static String listOfPickPointsTitleText;
    private static String firstAddressText;
    private static String selectedPickPointTitleText;
    private static String addressSelectedPickPointText;
    private static String addressOnHomePageText;

    @When("пользователь нажимает на кнопку \"Смена города\"")
    public void clickOnChangeCity() {
        addressButton.shouldBe(interactable).click();
    }

    @And("пользователь вводит {string} в поисковую строку")
    public void inputCityInSearch(String city) {
        addressInput.sendKeys(city, Keys.ENTER);
    }

    @And("пользователь выбирает первый адрес из списка адресов")
    public void clickOnFirstAddress() {
        listOfPickPointsTitleText = listOfPickPointsTitle.getText();
        firstAddressText = firstAddress.getText();

        firstAddress.shouldBe(visible, interactable).click();
    }

    @Then("должна открыться информация о пункте выдачи")
    public void goToPickPointPage() {
        selectedPickPointTitleText = selectedPickPointTitle.getText();

        Assert.passToPickPointPage(listOfPickPointsTitleText, selectedPickPointTitleText);
    }

    @And("адрес пункта выдачи должен совпадать с адресом из списка адресов")
    public void addressMatch() {
        addressSelectedPickPointText = addressSelectedPickPoint.getText();

        Assert.textMatch(firstAddressText, addressSelectedPickPointText);
    }

    @When("пользователь нажимает на кнопку \"Выбрать\"")
    public void selectFirstAddress() {
        selectButton.click();
    }

    @Then("пользователь должен перейти на главную страницу {string}")
    public void goToHomePage(String wbUrl) {
        Assert.passToHomePage(homePageUrl, "href", wbUrl);
    }

    @And("адрес пункта выдачи должен совпадать с адресом на странице пункта выдачи")
    public void addressMatch2() {
        addressOnHomePageText = addressOnHomePage.getText();

        Assert.textMatch(addressSelectedPickPointText, addressOnHomePageText);
    }
}
