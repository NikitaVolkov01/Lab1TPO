package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Word {
    @Getter
    private final List<Location> locations = new ArrayList<>();

    public boolean tryAddLocation(Location location) {
        if (location == null) return false;
        if (locations.stream().anyMatch(location::equals)) return false;
        for (Location l : locations) {
            if (location.isPartiallyInsideOther(l)) return false;
            if (l.isPartiallyInsideOther(location)) return false;
        }
        locations.add(location);
        return true;
    }
}
