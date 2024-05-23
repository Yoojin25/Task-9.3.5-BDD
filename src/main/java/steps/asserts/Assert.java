package steps.asserts;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Assert {

    public static void textPresence(SelenideElement element, String expectedText) {
        assertThat(element.getText(), containsString(expectedText));
    }

    public static void emptyElement(SelenideElement element) {
        assertThat(element.getText(), isEmptyString());
    }

    public static void passToPickPointPage(String listTitle, String selectedTitle) {
        assertThat(listTitle, not(equalTo(selectedTitle)));
    }

    public static void textMatch(String text1, String text2) {
        if (text1.startsWith("/")) {
            text1 = text1.substring(2);
        } else if (text1.startsWith("Нашли")) {
            text1 = text1.substring(6);
        }

        assertThat(text1, equalTo(text2));
    }

    public static void passToHomePage(SelenideElement element, String attribute, String wbUrl) {
        assertThat(element.getAttribute(attribute), equalTo(wbUrl));
    }

    public static void checkVisibleAndClickable(SelenideElement element) {
        element.shouldBe(visible, interactable);
    }

    public static void checkSelectedFiltersVisible(SelenideElement element, String... filters) {
        for (String filter : filters) {
            assertThat(element.getText(), containsString(filter));
        }
    }

    public static void checkFilterIsActivated(SelenideElement element, String attributeName, String expectedAttributeValue) {
        element.shouldHave(attribute(attributeName, expectedAttributeValue));
    }
}
