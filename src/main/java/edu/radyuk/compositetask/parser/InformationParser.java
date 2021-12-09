package edu.radyuk.compositetask.parser;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.exception.TextException;

public interface InformationParser {
    InformationUnit parse(String text) throws TextException;
}
