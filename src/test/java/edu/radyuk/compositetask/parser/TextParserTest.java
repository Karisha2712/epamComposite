package edu.radyuk.compositetask.parser;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.parser.impl.TextParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class TextParserTest {
    @Test
    void ifTextParserParsedCorrectly() throws IOException, TextException {
        URL fileUrl = TextParserTest.class.getClassLoader().getResource("files/text.txt");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        Path path = Paths.get(filePath);
        String text = Files.readString(path);
        TextParser textParser = TextParser.getInstance();
        InformationUnit textNode = textParser.parse(text);
        int actual = (int) textNode.getChildNodes().stream()
                .flatMap(p -> p.getChildNodes().stream())
                .flatMap(s -> s.getChildNodes().stream())
                .count();
        int expected = 164;
        Assertions.assertEquals(expected, actual);
    }
}
