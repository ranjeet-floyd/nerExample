package com.example.ner;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import java.io.IOException;

/**
 *
 * @author ranjeet
 */
public class StanfordNLPExample {

    private static final String SERIALED_CLASSIFIER = "classifiers/english.all.3class.distsim.crf.ser.gz";

    public static void main(String[] args) throws Exception {

        String sampleText = "I go to school at Stanford University, which is located in California.";

        StanfordNLPExample nLPExample = new StanfordNLPExample();
        System.out.println("Entities : \n" + nLPExample.findEntities(sampleText));
    }

    private String findEntities(String text) throws IOException, ClassCastException, ClassNotFoundException {
        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(SERIALED_CLASSIFIER);
        // The first column gives entities, the second their classes, and the third the remaining text in a document
      return classifier.classifyToString(text, "tabbedEntities", false);

    }

}
