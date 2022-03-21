package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CanBeLocated {
    @Getter
    private Coordinates coordinates;
    @Getter
    private Size size;

    public boolean isPartiallyInsideOther(CanBeLocated other) {
        return isLeftBottomPointInsideLocation(other) || isRightTopPointInsideLocation(other) || isRightBottomPointInsideLocation(other) || isLeftTopPointInsideLocation(other);
    }

    public boolean isPartiallyOutsideOther(CanBeLocated other) {
        return !isLeftBottomPointInsideLocation(other) || !isRightTopPointInsideLocation(other) || !isRightBottomPointInsideLocation(other) || !isLeftTopPointInsideLocation(other);
    }

    private boolean isLeftTopPointInsideLocation(CanBeLocated other) {
        return other.getCoordinates().getX() <= this.getCoordinates().getX()
                && other.getCoordinates().getY() <= this.getCoordinates().getY() + this.getSize().getHeight() - 1
                && other.getCoordinates().getX() + other.getSize().getWidth() - 1 >= this.getCoordinates().getX()
                && other.getCoordinates().getY() + other.getSize().getHeight() - 1 >= this.getCoordinates().getY() + this.getSize().getHeight() - 1;
    }

    private boolean isRightBottomPointInsideLocation(CanBeLocated other) {
        return other.getCoordinates().getX() <= this.getCoordinates().getX() + this.getSize().getWidth() - 1
                && other.getCoordinates().getY() <= this.getCoordinates().getY()
                && other.getCoordinates().getX() + other.getSize().getWidth() - 1 >= this.getCoordinates().getX() + this.getSize().getWidth() - 1
                && other.getCoordinates().getY() + other.getSize().getHeight() - 1 >= this.getCoordinates().getY();
    }

    private boolean isRightTopPointInsideLocation(CanBeLocated other) {
        return other.getCoordinates().getX() <= this.getCoordinates().getX() + this.getSize().getWidth() - 1
                && other.getCoordinates().getY() <= this.getCoordinates().getY() + this.getSize().getHeight() - 1
                && other.getCoordinates().getX() + other.getSize().getWidth() - 1 >= this.getCoordinates().getX() + this.getSize().getWidth() - 1
                && other.getCoordinates().getY() + other.getSize().getHeight() - 1 >= this.getCoordinates().getY() + this.getSize().getHeight() - 1;
    }

    private boolean isLeftBottomPointInsideLocation(CanBeLocated other) {
        return other.getCoordinates().getX() <= this.getCoordinates().getX()
                && other.getCoordinates().getY() <= this.getCoordinates().getY()
                && other.getCoordinates().getX() + other.getSize().getWidth() - 1 >= this.getCoordinates().getX()
                && other.getCoordinates().getY() + other.getSize().getHeight() - 1 >= this.getCoordinates().getY();
    }
}