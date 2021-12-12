package edu.radyuk.compositetask.entity;

import edu.radyuk.compositetask.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static edu.radyuk.compositetask.entity.InformationUnitType.LETTER;
import static edu.radyuk.compositetask.entity.InformationUnitType.PUNCTUATION_SYMBOL;

public class SymbolNode extends InformationUnit {
    private static final Logger logger = LogManager.getLogger();
    private InformationUnitType type;
    private char symbol;

    public SymbolNode() {
    }

    public SymbolNode(char symbol, InformationUnitType type) throws TextException {
        EnumSet<InformationUnitType> informationUnitTypes = EnumSet.range(LETTER, PUNCTUATION_SYMBOL);
        if (!informationUnitTypes.contains(type)) {
            throw new TextException("Invalid type");
        }
        this.symbol = symbol;
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
    public List<InformationUnit> getChildNodes() {
        List<InformationUnit> childNodes = new ArrayList<>();
        logger.log(Level.ERROR, "Impossible operation");
        return childNodes;
    }

    @Override
    public boolean add(InformationUnit informationUnit) {
        logger.log(Level.ERROR, "Impossible operation");
        return false;
    }

    @Override
    public boolean remove(InformationUnit informationUnit) {
        logger.log(Level.ERROR, "Impossible operation");
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
        SymbolNode symbolNode = (SymbolNode) o;
        return this.symbol == symbolNode.symbol
                && type == symbolNode.type;
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
        return Character.toString(symbol);
    }
}
