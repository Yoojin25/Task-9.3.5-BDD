package base;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    public void setUp() {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.timeout = 8000;
    }

    @BeforeEach
    @Given("пользователь открывает сайт {string}")
    public void openBrowser(String url) {
        setUp();

        open(url);
    }
}
