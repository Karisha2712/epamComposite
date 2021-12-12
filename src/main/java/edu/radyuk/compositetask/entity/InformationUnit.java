package edu.radyuk.compositetask.entity;

import java.util.List;

public abstract class InformationUnit {
    public abstract List<InformationUnit> getChildNodes();

    public abstract boolean add(InformationUnit informationUnit);

    public abstract boolean remove(InformationUnit informationUnit);

    public abstract InformationUnitType getType(InformationUnit informationUnit);

    public abstract InformationUnitType getType();
}
