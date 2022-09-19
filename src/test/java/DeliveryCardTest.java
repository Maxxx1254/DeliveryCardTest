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
        GenerateDate generateDate = new GenerateDate();
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Санкт-Петербург");
//        String planningDate = generateDate.generateDate(0);
//        не получается поставить дату, через дебагер видно что дату высчитывает верную
//        но никак не хочет ее ставить, если поможете буду признателен

//        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[placeholder='Дата встречи']").click();
        $("[data-day='1663880400000']").click();
        $(byName("name")).val("Васиоий Васильев");
        $("[name='phone']").setValue("+79121234223");
        $("[class='checkbox__box']").click();
        $(withText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).shouldBe(appear, Duration.ofSeconds(15));
    }
}