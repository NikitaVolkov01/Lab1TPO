package model;

import lombok.Getter;

public class Size {
    @Getter
    private int width;
    @Getter
    private int height;

    public boolean trySetWidth(int width) {
        if (width <= 0) return false;
        this.width = width;
        return true;
    }

    public boolean trySetHeight(int height) {
        if (height <= 0) return false;
        this.height = height;
        return true;
    }
}
