package modelTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTest {

    @ParameterizedTest
    @CsvSource(value = {
            "PURPLE, пурпурный",
            "YELLOW, желтый",
            "GREEN, зеленый",
            "RED, красный",
            "VIOLET, лиловый",
            "SILVER, серебряный"
    })
    void checkColorDescription(String name, String description) {
        assertEquals(description, Color.valueOf(name).getName());
    }
}