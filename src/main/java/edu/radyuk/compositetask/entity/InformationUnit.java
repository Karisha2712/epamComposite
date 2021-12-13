package edu.radyuk.compositetask.entity;

import java.util.List;

public abstract class InformationUnit {
    public abstract List<InformationUnit> getChildNodes();

    public abstract void add(InformationUnit informationUnit);

    public abstract void remove(InformationUnit informationUnit);

    public abstract InformationUnitType getType(InformationUnit informationUnit);

    public abstract InformationUnitType getType();
}
