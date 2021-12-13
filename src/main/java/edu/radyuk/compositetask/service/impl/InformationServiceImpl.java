package edu.radyuk.compositetask.service.impl;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.service.InformationService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static edu.radyuk.compositetask.entity.InformationUnitType.TEXT;
import static edu.radyuk.compositetask.entity.InformationUnitType.WORD;

public class InformationServiceImpl implements InformationService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEXP_FOR_VOWEL = "[AEIOUYaeiouy]";
    private static final String REGEXP_FOR_CONSONANTS = "[[^AEIOUYaeiouy]&&A-Za-z]";

    @Override
    public void sortParagraphsBySentenceNumber(InformationUnit textNode) throws TextException {
        if (textNode.getType() != TEXT) {
            throw new TextException("Invalid node");
        }
        Comparator<InformationUnit> sentenceNumberComparator = (p1, p2) -> {
            int number1 = p1.getChildNodes().size();
            int number2 = p2.getChildNodes().size();
            return number1 - number2;
        };
        List<InformationUnit> paragraphs = textNode.getChildNodes();
        paragraphs.sort(sentenceNumberComparator);
        logger.log(Level.INFO, "Paragraphs sorted successfully");
    }

    @Override
    public List<InformationUnit> receiveSentencesWithLongestWord(InformationUnit textNode) throws TextException {
        if (textNode.getType() != TEXT) {
            throw new TextException("Invalid node");
        }
        Optional<Integer> maxWordLength = textNode.getChildNodes().stream()
                .flatMap(p -> p.getChildNodes().stream())
                .map(this::receiveMaxLettersNumberInWord)
                .max(Integer::compareTo);
        if (maxWordLength.isEmpty()) {
            throw new TextException("There aren't any words in text");
        }
        return textNode.getChildNodes().stream()
                .flatMap(p -> p.getChildNodes().stream())
                .filter(s -> receiveMaxLettersNumberInWord(s) == maxWordLength.get())
                .toList();
    }

    @Override
    public void removeSentencesWithLessWordsNumber(InformationUnit textNode, int minLength) throws TextException {
        if (textNode.getType() != TEXT) {
            throw new TextException("Invalid node");
        }
        List<InformationUnit> paragraphs = textNode.getChildNodes();
        paragraphs.forEach(p -> p.getChildNodes()
                .removeIf(s -> s.getChildNodes().size() < minLength));
        logger.log(Level.INFO, "Sentences removed successfully");
    }

    @Override
    public int calculateVowelsNumber(InformationUnit textNode) throws TextException {
        if (textNode.getType() != TEXT) {
            throw new TextException("Invalid node");
        }
        return calculateLettersNumber(textNode, REGEXP_FOR_VOWEL);
    }

    @Override
    public int calculateConsonantsNumber(InformationUnit textNode) throws TextException {
        if (textNode.getType() != TEXT) {
            throw new TextException("Invalid node");
        }
        return calculateLettersNumber(textNode, REGEXP_FOR_CONSONANTS);
    }

    @Override
    public int findSameWords(InformationUnit textNode, String word) throws TextException {
        if (textNode.getType() != TEXT) {
            throw new TextException("Invalid node");
        }
        return (int) textNode.getChildNodes().stream()
                .flatMap(p1 -> p1.getChildNodes().stream())
                .flatMap(s1 -> s1.getChildNodes().stream())
                .filter(w -> w.toString().equalsIgnoreCase(word))
                .count();
    }

    private int calculateLettersNumber(InformationUnit textNode, String regexp) {
        List<InformationUnit> consonants = textNode.getChildNodes().stream()
                .flatMap(p -> p.getChildNodes().stream())
                .flatMap(s -> s.getChildNodes().stream())
                .flatMap(w -> w.getChildNodes().stream())
                .filter(l -> l.toString().matches(regexp))
                .toList();
        return consonants.size();
    }

    private int receiveMaxLettersNumberInWord(InformationUnit sentence) {
        return sentence.getChildNodes().stream()
                .filter(node -> node.getType() == WORD)
                .map(node -> node.getChildNodes().size())
                .max(Integer::compareTo)
                .orElse(-1);
    }
}
