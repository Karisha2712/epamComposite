package edu.radyuk.compositetask.parser.impl;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.entity.impl.TextNode;
import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.radyuk.compositetask.entity.InformationUnitType.PARAGRAPH;

public class ParagraphParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final ParagraphParser INSTANCE = new ParagraphParser();
    private static final String REGEXP_FOR_PARAGRAPH_PARSING = "(?<=((\\.)|(!)|(\\?)))\\s+(?=[A-Z])";
    private final InformationParser sentenceParser = SentenceParser.getInstance();

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InformationUnit parse(String text) throws TextException {
        String[] sentences = text.split(REGEXP_FOR_PARAGRAPH_PARSING);
        InformationUnit textNode = new TextNode(text, PARAGRAPH);
        for (var sentence : sentences) {
            InformationUnit sentenceNode = sentenceParser.parse(sentence);
            textNode.add(sentenceNode);
        }
        logger.log(Level.INFO, "Paragraph parsed successfully");
        return textNode;
    }
}
