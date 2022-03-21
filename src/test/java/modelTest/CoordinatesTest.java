package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.Coordinates;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {
    Coordinates coordinates;

    @BeforeEach
    void init() {
        coordinates = new Coordinates();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "5, 10",
            "1, 2",
            "50, 100",
    })
    void checkPositiveValues(int x, int y) {
        coordinates.trySetX(x);
        coordinates.trySetY(y);
        assertAll(
                () -> assertEquals(x, coordinates.getX()),
                () -> assertEquals(y, coordinates.getY())
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-5, -10",
            "0, -2",
            "-3, 0",
    })
    void checkNegativeAndZeroValues(int x, int y) {
        assertAll(
                () -> assertFalse(coordinates.trySetX(x)),
                () -> assertFalse(coordinates.trySetY(y))
        );
    }
}