package com.example.ner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

/**
 *
 * @author ranjeet
 */
public class OpenNLPExample {

    private static final String LOCATION_MODEL = "en-ner-location.bin";
    private static final String TOKEN_MODEL = "en-token.bin";

    public static void main(String[] args) throws IOException {
        OpenNLPExample example = new OpenNLPExample();
        String sampleText = "I go to school at Stanford University, which is located in California.";
        List<String> locations = example.findLocation(sampleText);

        System.out.println("Found : \n" + String.join("\n ", locations));

    }

    public List<String> findLocation(String text) throws IOException {

        try (InputStream modelIn = new FileInputStream(TOKEN_MODEL)) {
            TokenizerModel tokenModel = new TokenizerModel(modelIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);

            NameFinderME nameFinder
                    = new NameFinderME(new TokenNameFinderModel(new FileInputStream(LOCATION_MODEL)));

            String tokens[] = tokenizer.tokenize(text);
            Span nameSpans[] = nameFinder.find(tokens);

            List<String> locations = new ArrayList<>();
            for (Span nameSpan : nameSpans) {
//                System.out.println("Span: " + nameSpan.toString());
//                System.out.println("Covered text is: " + tokens[nameSpan.getStart()] + " " + tokens[nameSpan.getStart() + 1]);
                locations.add(tokens[nameSpan.getStart()] + " " + tokens[nameSpan.getStart() + 1]);
            }
            return locations;
        }
    }
}
