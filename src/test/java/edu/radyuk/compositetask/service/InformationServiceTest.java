package edu.radyuk.compositetask.service;

import edu.radyuk.compositetask.entity.InformationUnit;
import edu.radyuk.compositetask.exception.TextException;
import edu.radyuk.compositetask.parser.impl.TextParser;
import edu.radyuk.compositetask.service.impl.InformationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class InformationServiceTest {
    private static InformationUnit textNode;
    private static final InformationServiceImpl informationService = new InformationServiceImpl();

    @BeforeAll
    static void setUp() throws IOException, TextException {
        URL fileUrl = InformationServiceTest.class.getClassLoader().getResource("files/text.txt");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        Path path = Paths.get(filePath);
        String text = Files.readString(path);
        TextParser textParser = TextParser.getInstance();
        textNode = textParser.parse(text);
    }

    @Test
    void ifSortParagraphsBySentenceNumberTestReturnsCorrectObject() throws TextException, IOException {
        URL fileUrl = InformationServiceTest.class.getClassLoader().getResource("files/sortedParagraphs.txt");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        Path path = Paths.get(filePath);
        String text = Files.readString(path);
        TextParser textParser = TextParser.getInstance();
        InformationUnit expectedNode = textParser.parse(text);
        informationService.sortParagraphsBySentenceNumber(textNode);
        Assertions.assertEquals(expectedNode, textNode);
    }

    @Test
    void ifReceiveSentencesWithLongestWordReturnsCorrectSentences() throws TextException {
        List<InformationUnit> actualSentences = informationService.receiveSentencesWithLongestWord(textNode);
        String expected = "They teach us to be kind, clever, polite, hardworking, friendly. ";
        String actual = actualSentences.get(0).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ifRemoveSentencesWithLessWordsNumberReturnsCorrectNumber() throws TextException {
        int expected = (int) textNode.getChildNodes().stream()
                .flatMap(s -> s.getChildNodes().stream())
                .count() - 5;
        informationService.removeSentencesWithLessWordsNumber(textNode, 6);
        int actual = (int) textNode.getChildNodes().stream()
                .flatMap(s -> s.getChildNodes().stream())
                .count();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ifCalculateVowelsNumberReturnsCorrectNumber() throws TextException {
        int actual = informationService.calculateVowelsNumber(textNode);
        int expected = 309;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ifCalculateConsonantsNumberReturnsCorrectNumber() throws TextException {
        int actual = informationService.calculateConsonantsNumber(textNode);
        int expected = 454;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ifFindSameWordsReturnsCorrectNumber() throws TextException {
        int actual = informationService.findSameWords(textNode, "with");
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }
}
