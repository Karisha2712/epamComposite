package edu.radyuk.compositetask.entity.impl;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.entity.InformationUnitType;
import edu.radyuk.compositetask.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;

import static edu.radyuk.compositetask.entity.InformationUnitType.LETTER;
import static edu.radyuk.compositetask.entity.InformationUnitType.PUNCTUATION_SYMBOL;

public class SymbolFromText implements InformationUnit {
    private static final Logger logger = LogManager.getLogger();
    private static final String IMPOSSIBLE_OPERATION_MESSAGE = "Impossible operation";
    private InformationUnitType type;
    private char symbol;

    public SymbolFromText() {
    }

    public SymbolFromText(char symbolFromText, InformationUnitType type) throws TextException {
        EnumSet<InformationUnitType> informationUnitTypes = EnumSet.range(LETTER, PUNCTUATION_SYMBOL);
        if (!informationUnitTypes.contains(type)) {
            throw new TextException("Invalid type");
        }
        this.symbol = symbolFromText;
        this.type = type;
    }

    public InformationUnitType getType() {
        return type;
    }

    public void setType(InformationUnitType type) {
        this.type = type;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean add(InformationUnit informationUnit) {
        logger.log(Level.ERROR, IMPOSSIBLE_OPERATION_MESSAGE);
        return false;
    }

    @Override
    public boolean remove(InformationUnit informationUnit) {
        logger.log(Level.ERROR, IMPOSSIBLE_OPERATION_MESSAGE);
        return false;
    }

    @Override
    public InformationUnitType getType(InformationUnit informationUnit) {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymbolFromText symbolFromText = (SymbolFromText) o;
        return this.symbol == symbolFromText.symbol
                && type == symbolFromText.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + type.hashCode();
        result += result * 31 + Character.hashCode(symbol);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SymbolFromText{");
        sb.append("type=").append(type);
        sb.append(", symbol=").append(symbol);
        sb.append('}');
        return sb.toString();
    }
}
