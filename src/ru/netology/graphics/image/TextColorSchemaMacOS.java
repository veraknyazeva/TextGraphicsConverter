package ru.netology.graphics.image;

public class TextColorSchemaMacOS implements TextColorSchema {
    private final static char[] SPECIAL_SYMBOLS = new char[]{'▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-'};

    @Override
    public char convert(int color) {
        if (0 <= color && color < 28) {
            return SPECIAL_SYMBOLS[0];
        } else if (28 <= color && color < 57) {
            return SPECIAL_SYMBOLS[1];
        } else if (57 <= color && color < 86) {
            return SPECIAL_SYMBOLS[2];
        } else if (86 <= color && color < 115) {
            return SPECIAL_SYMBOLS[3];
        } else if (115 <= color && color < 144) {
            return SPECIAL_SYMBOLS[4];
        } else if (144 <= color && color < 173) {
            return SPECIAL_SYMBOLS[5];
        } else if (173 <= color && color < 202) {
            return SPECIAL_SYMBOLS[6];
        } else if (202 <= color && color < 231) {
            return SPECIAL_SYMBOLS[7];
        } else {
            return SPECIAL_SYMBOLS[8];
        }
    }
}
