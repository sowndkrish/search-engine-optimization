package sow;

import sow.NaiveBayes;
import sow.NaiveBayesKnowledgeBase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NaiveBayesExample {

    /**
     * Reads the all lines from a file and places it a String array. In each 
     * record in the String array we store a training example text.
     * 
     * @param url
     * @return
     * @throws IOException 
     */
    public static String[] readLines(URL url) throws IOException {

        Reader fileReader = new InputStreamReader(url.openStream(), Charset.forName("UTF-8"));
        List<String> lines;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[lines.size()]);
    }
    
    /**
     * Main method
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //map of dataset files
        Map<String, URL> trainingFiles = new HashMap<>();
        trainingFiles.put("ICSE", NaiveBayesExample.class.getResource("icse_id.txt"));
        trainingFiles.put("VLDB", NaiveBayesExample.class.getResource("vldb_id.txt"));
        
        //loading examples in memory
        Map<String, String[]> trainingExamples = new HashMap<>();
        for(Map.Entry<String, URL> entry : trainingFiles.entrySet()) {
            trainingExamples.put(entry.getKey(), readLines(entry.getValue()));
        }
        
        //train classifier
        NaiveBayes nb = new NaiveBayes();
        nb.setChisquareCriticalValue(6.63); //0.01 pvalue
        nb.train(trainingExamples);
        
        //get trained classifier knowledgeBase
        NaiveBayesKnowledgeBase knowledgeBase = nb.getKnowledgeBase();
        
        nb = null;
        trainingExamples = null;
        
        
        //Use classifier
        nb = new NaiveBayes(knowledgeBase);
        String exampleEn = "information entropy";
        String outputEn = nb.predict(exampleEn);
        String f1score=nb.f1score();
        System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleEn, outputEn);
       System.out.println("F1score"+f1score);
       // String exampleFr = "Je suis Français";
       // String outputFr = nb.predict(exampleFr);
       // System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleFr, outputFr);
        
       // String exampleDe = "Ich bin Deutsch";
        //String outputDe = nb.predict(exampleDe);
        //System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleDe, outputDe);
        

    }
    
}