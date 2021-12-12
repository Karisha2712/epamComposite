package edu.radyuk.compositetask.parser;

import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.parser.impl.TextParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextParserTest {
    @Test
    public void ifTextParserReturnsCorrectParagraph() throws IOException, TextException {
        URL fileUrl = TextParserTest.class.getClassLoader().getResource("files/text.txt");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        Path path = Paths.get(filePath);
        String text = Files.readString(path);
        TextParser textParser = TextParser.getInstance();
        textParser.parse(text);
    }
}
