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

public class InformationServiceTest {
    private static InformationUnit textNode;
    private static final InformationServiceImpl informationService = new InformationServiceImpl();

    @BeforeAll
    public static void setUp() throws IOException, TextException {
        URL fileUrl = InformationServiceTest.class.getClassLoader().getResource("files/text.txt");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        Path path = Paths.get(filePath);
        String text = Files.readString(path);
        TextParser textParser = TextParser.getInstance();
        textNode = textParser.parse(text);
    }

    @Test
    public void ifSortParagraphsBySentenceNumberTest() throws TextException {
        informationService.sortParagraphsBySentenceNumber(textNode);
        System.out.println(textNode);
    }

    @Test
    public void ifReceiveMaxLettersNumberInWordTest() throws TextException {
        List<InformationUnit> sentences = informationService.receiveSentencesWithLongestWord(textNode);
        System.out.println(sentences);
    }

    @Test
    public void ifRemoveSentencesWithLessWordsNumber() throws TextException {
        informationService.removeSentencesWithLessWordsNumber(textNode, 20);
        System.out.println("[" + textNode + "]");
    }

    @Test
    public void ifCalculateVowelsNumber() throws TextException {
        int res = informationService.calculateVowelsNumber(textNode);
        Assertions.assertEquals(10, res);
    }

    @Test
    public void ifCalculateConsonantsNumber() throws TextException {
        int res = informationService.calculateConsonantsNumber(textNode);
        Assertions.assertEquals(14, res);
    }

    @Test
    public void ifFindSameWords() throws TextException {
        System.out.println(informationService.findSameWords(textNode, "it"));

    }
}
