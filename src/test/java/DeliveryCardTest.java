import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @BeforeAll
    static void setUpAll() { SelenideLogger.addListener("allure", new AllureSelenide());}
    @AfterAll
    static void tearDownAll() {    SelenideLogger.removeListener("allure");}

    @Test
    void shouldSubmitRequest() {
        GenerateDate generateDate = new GenerateDate();
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Санкт-Петербург");
        String planningDate = generateDate.generateDate(5);
        $("[placeholder='Дата встречи']").click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $(byName("name")).val("Василий Васильев");
        $("[name='phone']").setValue("+79121234223");
        $("[class='checkbox__box']").click();
        $(withText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}