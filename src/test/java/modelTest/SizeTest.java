package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.Size;

import static org.junit.jupiter.api.Assertions.*;

public class SizeTest {
    Size size;

    @BeforeEach
    void init() {
        size = new Size();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "5, 10",
            "1, 2",
            "50, 100",
    })
    void checkPositiveValues(int w, int h) {
        size.trySetWidth(w);
        size.trySetHeight(h);
        assertAll(
                () -> assertEquals(w, size.getWidth()),
                () -> assertEquals(h, size.getHeight())
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-5, -10",
            "0, -2",
            "-3, 0",
    })
    void checkNegativeAndZeroValues(int w, int h) {
        assertAll(
                () -> assertFalse(size.trySetWidth(w)),
                () -> assertFalse(size.trySetHeight(h))
        );
    }
}
