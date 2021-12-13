package edu.radyuk.compositetask.service;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.exception.TextException;

import java.util.List;
import java.util.Map;

public interface InformationService {
    void sortParagraphsBySentenceNumber(InformationUnit textNode) throws TextException;

    List<InformationUnit> receiveSentencesWithLongestWord(InformationUnit textNode) throws TextException;

    void removeSentencesWithLessWordsNumber(InformationUnit textNode, int number) throws TextException;

    int calculateVowelsNumber(InformationUnit textNode) throws TextException;

    int calculateConsonantsNumber(InformationUnit textNode) throws TextException;

    int findSameWords(InformationUnit textNode, String word) throws TextException;
}
