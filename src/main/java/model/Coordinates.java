package model;

import lombok.Getter;

public class Coordinates {
    @Getter
    private int X;
    @Getter
    private int Y;

    public boolean trySetX(int X) {
        if (X <= 0) return false;
        this.X = X;
        return true;
    }

    public boolean trySetY(int Y) {
        if (Y <= 0) return false;
        this.Y = Y;
        return true;
    }
}