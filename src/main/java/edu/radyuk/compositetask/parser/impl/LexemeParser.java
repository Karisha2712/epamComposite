package edu.radyuk.compositetask.parser.impl;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.entity.SymbolNode;
import edu.radyuk.compositetask.entity.TextNode;
import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.radyuk.compositetask.entity.InformationUnitType.LEXEME;
import static edu.radyuk.compositetask.entity.InformationUnitType.PUNCTUATION_SYMBOL;

public class LexemeParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final LexemeParser INSTANCE = new LexemeParser();
    private static final String REGEXP_FOR_LEXEME_PARSING = "(?<=[«(])|(?=[—:,).»!?])";
    private static final String REGEXP_FOR_WORD = "(\\w|-)+";
    private final InformationParser wordParser = WordParser.getInstance();

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InformationUnit parse(String text) throws TextException {
        String[] lexemes = text.strip().split(REGEXP_FOR_LEXEME_PARSING);
        InformationUnit textNode = new TextNode(LEXEME);
        for (var lexeme : lexemes) {
            InformationUnit lexemeNode;
            if (lexeme.matches(REGEXP_FOR_WORD)) {
                lexemeNode = wordParser.parse(lexeme);
            } else {
                lexemeNode = new SymbolNode(lexeme.charAt(0), PUNCTUATION_SYMBOL);
            }
            textNode.add(lexemeNode);
        }
        logger.log(Level.INFO, "Word parsed successfully");
        return textNode;
    }
}
