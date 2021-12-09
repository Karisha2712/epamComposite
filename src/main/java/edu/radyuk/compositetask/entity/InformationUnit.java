package edu.radyuk.compositetask.entity;

public abstract class InformationUnit {
    public abstract boolean add(InformationUnit informationUnit);

    public abstract boolean remove(InformationUnit informationUnit);

    public abstract InformationUnitType getType(InformationUnit informationUnit);
}
