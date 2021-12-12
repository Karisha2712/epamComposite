package edu.radyuk.compositetask.entity;

import edu.radyuk.compositetask.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import static edu.radyuk.compositetask.entity.InformationUnitType.TEXT;
import static edu.radyuk.compositetask.entity.InformationUnitType.WORD;

public class TextNode extends InformationUnit {
    private static final Logger logger = LogManager.getLogger();
    private static final String TABULATION_FOR_TEXT = "    ";
    private final List<InformationUnit> childNodes = new ArrayList<>();
    private InformationUnitType type;

    public TextNode() {
    }

    public TextNode(InformationUnitType type) throws TextException {
        EnumSet<InformationUnitType> informationUnitTypes = EnumSet.range(TEXT, WORD);
        if (!informationUnitTypes.contains(type)) {
            throw new TextException("Invalid type");
        }
        this.type = type;
    }

    @Override
    public List<InformationUnit> getChildNodes() {
        return childNodes;
    }

    public InformationUnitType getType() {
        return type;
    }

    public void setType(InformationUnitType type) {
        this.type = type;
    }

    @Override
    public boolean add(InformationUnit informationUnit) {
        return childNodes.add(informationUnit);
    }

    @Override
    public boolean remove(InformationUnit informationUnit) {
        return childNodes.remove(informationUnit);
    }

    @Override
    public InformationUnitType getType(InformationUnit informationUnit) {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextNode textPart1 = (TextNode) o;
        return Objects.equals(childNodes, textPart1.childNodes)
                && type == textPart1.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + childNodes.hashCode();
        result += result * 31 + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String delimiter = type.getDelimiter();
        if (this.type == TEXT) {
            builder.append(TABULATION_FOR_TEXT);
        }
        for (InformationUnit textNode : this.getChildNodes()) {
            builder.append(textNode.toString()).append(delimiter);
        }
        return builder.toString();
    }
}

