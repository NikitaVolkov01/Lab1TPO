package model;

import lombok.Getter;
import lombok.Setter;

public class LocationPlaceholder extends CanBeLocated {
    @Getter
    private final Location location;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Color color;

    public LocationPlaceholder(Coordinates coordinates, Size size, Location location) {
        super(coordinates, size);
        this.location = location;
    }
}
