import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    @Test
    void ifFormIsCorrectShouldReturnSuccess() {
        {
            open("http://localhost:9999");
            SelenideElement form = $("form");
            form.$("[data-test-id=city] input").setValue("Москва");
            form.$("[data-test-id=date] input").setValue(getDeliveryDate(new Date()));
            form.$("[data-test-id=name] input").setValue("Иванов Иван");
            form.$("[data-test-id=phone] input").setValue("+79876543210");
            form.$("[data-test-id=agreement]").click();

            $$("button").find(exactText("Забронировать")).click();
            $(withText("Успешно!")).waitUntil(visible, 15000);
        }
    }

    private String getDeliveryDate(Date date){
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, 3);
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(calendar.getTime());
    }
}
