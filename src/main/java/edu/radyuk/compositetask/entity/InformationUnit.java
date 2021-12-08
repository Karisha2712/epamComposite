package edu.radyuk.compositetask.entity;

public interface InformationUnit {
    boolean add(InformationUnit informationUnit);

    boolean remove(InformationUnit informationUnit);

    InformationUnitType getType(InformationUnit informationUnit);
}
