package edu.radyuk.compositetask.parser.impl;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.entity.impl.TextNode;
import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.radyuk.compositetask.entity.InformationUnitType.SENTENCE;

public class SentenceParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final SentenceParser INSTANCE = new SentenceParser();
    private static final String REGEXP_FOR_SENTENCE_PARSING = "\\s+";
    private final InformationParser wordParser = WordParser.getInstance();

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InformationUnit parse(String text) throws TextException {
        String[] words = text.split(REGEXP_FOR_SENTENCE_PARSING);
        InformationUnit textNode = new TextNode(text, SENTENCE);
        for (var word : words) {
            InformationUnit wordNode = wordParser.parse(word);
            textNode.add(wordNode);
        }
        logger.log(Level.INFO, "Sentence parsed successfully");
        return textNode;
    }
}
