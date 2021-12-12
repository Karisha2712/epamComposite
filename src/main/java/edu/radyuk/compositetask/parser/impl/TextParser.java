package edu.radyuk.compositetask.parser.impl;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.entity.TextNode;
import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.radyuk.compositetask.entity.InformationUnitType.TEXT;

public class TextParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final TextParser INSTANCE = new TextParser();
    private static final int SPACE_NUMBER_BEFORE_PARAGRAPH = 6;
    private static final String REGEXP_FOR_TEXT_PARSING = "\\s{" + SPACE_NUMBER_BEFORE_PARAGRAPH + "}";
    private final InformationParser paragraphParser = ParagraphParser.getInstance();

    private TextParser() {
    }

    public static TextParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InformationUnit parse(String text) throws TextException {
        String[] paragraphs = text.strip().split(REGEXP_FOR_TEXT_PARSING);
        InformationUnit textNode = new TextNode(TEXT);
        for (var paragraph : paragraphs) {
            paragraph = paragraph.strip();
            InformationUnit paragraphNode = paragraphParser.parse(paragraph);
            textNode.add(paragraphNode);
        }
        logger.log(Level.INFO, "Text parsed successfully");
        return textNode;
    }
}
