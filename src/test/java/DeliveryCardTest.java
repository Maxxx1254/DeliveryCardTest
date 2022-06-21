import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Санкт-Петербург");
        $(byName("name")).val("Васиоий Васильев");
        $("[name='phone']").setValue("+79121234223");
        $x("//label[@data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).shouldBe(appear, Duration.ofSeconds(15));
    }
}