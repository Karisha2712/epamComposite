package edu.radyuk.compositetask.parser.impl;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.entity.impl.SymbolNode;
import edu.radyuk.compositetask.entity.impl.TextNode;
import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.radyuk.compositetask.entity.InformationUnitType.*;

public class WordParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final WordParser INSTANCE = new WordParser();
    private static final String REGEXP_FOR_WORD_PARSING = "";
    private static final String REGEXP_FOR_PUNCTUATION = "[?!.,)(-]|(\\.{3})";

    private WordParser() {
    }

    public static WordParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InformationUnit parse(String text) throws TextException {
        String[] symbols = text.split(REGEXP_FOR_WORD_PARSING);
        InformationUnit textNode = new TextNode(text, WORD);
        for (var symbol : symbols) {
            SymbolNode symbolNode = new SymbolNode();
            symbolNode.setSymbol(symbol.charAt(0));
            if (symbol.matches(REGEXP_FOR_PUNCTUATION)) {
                symbolNode.setType(PUNCTUATION_SYMBOL);
            } else {
                symbolNode.setType(LETTER);
            }
            textNode.add(symbolNode);
        }
        logger.log(Level.INFO, "Word parsed successfully");
        return textNode;
    }
}
