package model;

import lombok.Getter;

public enum Color {
    PURPLE("пурпурный"),
    YELLOW("желтый"),
    GREEN("зеленый"),
    RED("красный"),
    VIOLET("лиловый"),
    SILVER("серебряный");
    @Getter
    private final String name;

    Color(String name) {
        this.name = name;
    }
}