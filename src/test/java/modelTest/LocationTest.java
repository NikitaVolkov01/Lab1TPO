package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.Coordinates;
import model.Location;
import model.Size;
import model.Word;


import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private Word word;

    @BeforeEach
    void init() {
        word = new Word();
    }

    @Test
    void checkAddInnerLocation() {
        Location outer = createLocation(11, 11, 10, 20, "Hell");
        Location inner = createLocation(14, 14, 3, 3, "Heaven");
        assertAll(
                () -> assertTrue(word.tryAddLocation(outer)),
                () -> assertFalse(word.tryAddLocation(inner))
        );
    }

    @Test
    void checkAddOuterLocation() {
        Location outer = createLocation(11, 11, 10, 20, "Hell");
        Location inner = createLocation(1, 1, 30, 30, "Heaven");
        assertAll(
                () -> assertTrue(word.tryAddLocation(outer)),
                () -> assertFalse(word.tryAddLocation(inner))
        );
    }

    @ParameterizedTest(name = "{index} - test {4} border")
    @CsvSource(value = {
            "21, 11, 10, 20, right",
            "11, 31, 10, 10, top",
            "1, 11, 10, 20, left",
            "11, 1, 10, 10, bottom",
    })
    @DisplayName("Add location in border success")
    void checkAddLocationsInBordersSuccess(int x, int y, int w, int h, String borderName) {
        Location center = createLocation(11, 11, 10, 20, "Hell");
        Location border = createLocation(x, y, w, h, "Heaven");
        assertAll(
                () -> assertTrue(word.tryAddLocation(center)),
                () -> assertTrue(word.tryAddLocation(border), "Location on the " + borderName + " border should be added")
        );
    }

    @ParameterizedTest(name = "{index} - test {4} border")
    @CsvSource(value = {
            "20, 11, 10, 20, right",
            "11, 30, 10, 10, top",
            "2, 11, 10, 20, left",
            "11, 2, 10, 10, bottom",
    })
    @DisplayName("Add location in border failed")
    void checkAddLocationsInFailedBorders(int x, int y, int w, int h, String borderName) {
        Location center = createLocation(11, 11, 10, 20, "Hell");
        Location border = createLocation(x, y, w, h, "Heaven");
        assertAll(
                () -> assertTrue(word.tryAddLocation(center)),
                () -> assertFalse(word.tryAddLocation(border), "Location on the " + borderName + " border should not be added")
        );
    }

    @ParameterizedTest(name = "{index} - test {4} border point")
    @CsvSource(value = {
            "15, 15, 10, 10, left-bottom",
            "5, 15, 10, 10, right-bottom",
            "1, 1, 15, 15, right-top",
            "15, 5, 10, 10, left-top",
    })
    @DisplayName("Some border point is in other location")
    void checkOnePointInOtherLocation(int x, int y, int w, int h, String pointName) {
        Location center = createLocation(11, 11, 10, 10, "Hell");
        Location border = createLocation(x, y, w, h, "Heaven");
        assertAll(
                () -> assertTrue(word.tryAddLocation(center)),
                () -> assertFalse(word.tryAddLocation(border), "Location with " + pointName + " border point should not be added")
        );
    }

    @ParameterizedTest(name = "{index} - test {4} border")
    @CsvSource(value = {
            "11, 11, 20, 20, left-bottom",
            "1, 11, 20, 20, right-bottom",
            "1, 1, 25, 25, right-top",
            "11, 1, 20, 20, left-top",
    })
    @DisplayName("Largest Location that includes border")
    void checkAddLocationThatIncludeOther(int x, int y, int w, int h) {
        Location center = createLocation(11, 11, 10, 10, "Hell");
        Location border = createLocation(x, y, w, h, "Heaven");
        assertAll(
                () -> assertTrue(word.tryAddLocation(center)),
                () -> assertFalse(word.tryAddLocation(border))
        );
    }

    private Location createLocation(int x, int y, int w, int h, String name) {
        Location location = new Location(new Coordinates(), new Size());
        location.getCoordinates().trySetX(x);
        location.getCoordinates().trySetY(y);
        location.getSize().trySetWidth(w);
        location.getSize().trySetHeight(h);
        location.setName(name);
        return location;
    }
}
