package edu.radyuk.compositetask.entity;

public enum InformationUnitType {
    TEXT("\n    "),
    PARAGRAPH(" "),
    SENTENCE(" "),
    WORD(""),
    LETTER(""),
    PUNCTUATION_SYMBOL("");

    private final String delimiter;

    InformationUnitType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
