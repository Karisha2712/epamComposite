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

public class TextPart implements InformationUnit {
    private static final Logger logger = LogManager.getLogger();
    private final List<InformationUnit> informationUnits = new ArrayList<>();
    private InformationUnitType type;
    private String textPart;

    public TextPart() {
    }

    public TextPart(String textPart, InformationUnitType type) throws TextException {
        EnumSet<InformationUnitType> informationUnitTypes = EnumSet.range(TEXT, WORD);
        if (informationUnitTypes.contains(type)) {
            throw new TextException("Invalid type");
        }
        this.type = type;
        this.textPart = textPart;
    }

    public List<InformationUnit> getInformationUnits() {
        return List.copyOf(informationUnits);
    }

    public InformationUnitType getType() {
        return type;
    }

    public void setType(InformationUnitType type) {
        this.type = type;
    }

    public String getTextPart() {
        return textPart;
    }

    public void setTextPart(String textPart) {
        this.textPart = textPart;
    }

    @Override
    public boolean add(InformationUnit informationUnit) {
        return informationUnits.add(informationUnit);
    }

    @Override
    public boolean remove(InformationUnit informationUnit) {
        return informationUnits.remove(informationUnit);
    }

    @Override
    public InformationUnitType getType(InformationUnit informationUnit) {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextPart textPart1 = (TextPart) o;
        return Objects.equals(informationUnits, textPart1.informationUnits)
                && type == textPart1.type
                && Objects.equals(textPart, textPart1.textPart);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + informationUnits.hashCode();
        result += result * 31 + type.hashCode();
        result += result * 31 + textPart.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextPart{");
        sb.append("informationUnits=").append(informationUnits);
        sb.append(", type=").append(type);
        sb.append(", textPart='").append(textPart).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

