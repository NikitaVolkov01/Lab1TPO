package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class Location extends CanBeLocated {
    @Getter
    private final List<LocationPlaceholder> locationPlaceholders = new ArrayList<>();
    @Getter
    @Setter
    private String name;

    public Location(Coordinates coordinates, Size size) {
        super(coordinates, size);
    }

    public boolean tryAddPlaceholder(LocationPlaceholder locationPlaceholder) {
        if (locationPlaceholder == null) return false;
        if (!locationPlaceholder.isPartiallyInsideOther(this)) return false;
        if (locationPlaceholder.isPartiallyOutsideOther(this)) return false;
        locationPlaceholders.add(locationPlaceholder);
        return true;
    }
}