package edu.radyuk.compositetask.entity.impl;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.entity.InformationUnitType;
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
    private final List<InformationUnit> childNodes = new ArrayList<>();
    private InformationUnitType type;
    private String text;

    public TextNode() {
    }

    public TextNode(String nodeText, InformationUnitType type) throws TextException {
        EnumSet<InformationUnitType> informationUnitTypes = EnumSet.range(TEXT, WORD);
        if (!informationUnitTypes.contains(type)) {
            throw new TextException("Invalid type");
        }
        this.type = type;
        this.text = nodeText;
    }

    public List<InformationUnit> getChildNodes() {
        return List.copyOf(childNodes);
    }

    public InformationUnitType getType() {
        return type;
    }

    public void setType(InformationUnitType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                && type == textPart1.type
                && Objects.equals(text, textPart1.text);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + childNodes.hashCode();
        result += result * 31 + type.hashCode();
        result += result * 31 + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextPart{");
        sb.append("informationUnits=").append(childNodes);
        sb.append(", type=").append(type);
        sb.append(", textPart='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

