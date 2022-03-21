package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.Coordinates;
import model.Location;
import model.LocationPlaceholder;
import model.Size;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationPlaceholderTest {
    private Location location;

    @BeforeEach
    void init() {
        location = new Location(new Coordinates(), new Size());
        location.getCoordinates().trySetX(11);
        location.getCoordinates().trySetY(11);
        location.getSize().trySetWidth(10);
        location.getSize().trySetHeight(10);
        location.setName("Hell");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "13, 13, 1, 1",
            "12, 13, 2, 2",
            "18, 18, 1, 1",
            "12, 15, 4, 3",
    })
    @DisplayName("Add placeholder inside location")
    void checkAddPlaceholdersInsideTheirLocation(int x, int y, int w, int h) {
        LocationPlaceholder placeHolder = createPlaceholder(x, y, w, h, location);
        assertTrue(location.tryAddPlaceholder(placeHolder));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 1, 1, 1",
            "23, 43, 2, 2",
            "1, 41, 1, 1",
            "5, 1, 4, 3",
    })
    @DisplayName("Add placeholder outside location")
    void checkAddPlaceholdersOutsideTheirLocation(int x, int y, int w, int h) {
        LocationPlaceholder placeHolder = createPlaceholder(x, y, w, h, location);
        assertFalse(location.tryAddPlaceholder(placeHolder));
    }

    @ParameterizedTest(name = "{index} - add to {4} border point")
    @CsvSource(value = {
            "11, 11, 1, 1, left-bottom",
            "20, 11, 1, 1, right-bottom",
            "20, 20, 1, 1, right-top",
            "11, 20, 1, 1, left-top",
    })
    @DisplayName("Add placeholder in border point")
    void checkPlantingInBorderValues(int x, int y, int w, int h, String border) {
        LocationPlaceholder placeHolder = createPlaceholder(x, y, w, h, location);
        assertTrue(location.tryAddPlaceholder(placeHolder), "Placeholder should plant in this part of border: " + border);
    }

    @ParameterizedTest(name = "{index} - add to {4} border point")
    @CsvSource(value = {
            "10, 10, 2, 2, left-bottom",
            "20, 10, 2, 2, right-bottom",
            "20, 20, 2, 2, right-top",
            "10, 20, 2, 2, left-top",
    })
    @DisplayName("Add placeholder in border point with outside size values")
    void checkPlantingInBorderValuesWithExtraSize(int x, int y, int w, int h, String border) {
        LocationPlaceholder placeHolder = createPlaceholder(x, y, w, h, location);
        assertFalse(location.tryAddPlaceholder(placeHolder), "Can't plant placeholder in this part of border: " + border);
    }

    @Test
    void checkPlaceholderWithSizeGreaterThanLocation() {
        LocationPlaceholder placeHolder = createPlaceholder(1, 1, 100, 100, location);
        assertFalse(location.tryAddPlaceholder(placeHolder));

    }

    private LocationPlaceholder createPlaceholder(int x, int y, int w, int h, Location location) {
        LocationPlaceholder placeHolder = new LocationPlaceholder(new Coordinates(), new Size(), location);
        placeHolder.getCoordinates().trySetX(x);
        placeHolder.getCoordinates().trySetY(y);
        placeHolder.getSize().trySetWidth(w);
        placeHolder.getSize().trySetHeight(h);
        return placeHolder;
    }
}