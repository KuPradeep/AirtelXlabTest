package com.airtel.xlabs.assignment.controller;

import com.airtel.xlabs.assignment.model.Dictionary;
import com.airtel.xlabs.assignment.model.SpellCheckSuggestion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * @description : Resource for the spell checker
 * @author: Pradeep Kumar
 * @return : return api response as a hashmap
 * @params: Keyword or incorrect word for which we require suggestion
 * @description : Check keyword from the get Request and pass it tod the dictionary to check for suggestions
 */

@RestController
@RequestMapping(value = "api")
public class SpellingCheckerController {

    @GetMapping("spellchecker")
    public HashMap<String, String> SpellChecker(@RequestParam String query) {
        try {
            HashMap<String, String> result = new HashMap<String, String>();
            if(query == null || query.isEmpty()){
                System.out.println("Query Parameter is Null, Please eneter akeyword for check spelling ");
                result.put("Error","Error");
                result.put("statusCode","200");
                result.put("Msgs","Enter a keyword to check spelling");
                return result;
            }
            String wordFreqFileName = "Dictionary.csv";
            Dictionary dictionary = new Dictionary(wordFreqFileName); //Loading the dictionary from csv file where we have store some keyword and their frequecny
            List<SpellCheckSuggestion> spellCheckCorrections = this.checkSpelling(Arrays.asList(query), dictionary);
            // create API response
            result.put("IncorrectWord",query);
            result.put("Sucess","Sucess");
            result.put("statusCode","200");
            result.put("Msgs","Suggestion given successfully");
            int i=0;
            for (SpellCheckSuggestion spellCorrectedWord : spellCheckCorrections) {
                result.put("SuggestedCorrectSpelling_"+i,spellCorrectedWord.getWord());
                i++;
            }
            return result;
        } catch (Exception e){
            // log the exception traceback
            System.out.println("Something went wrong here " + e.getStackTrace());
        }
        return null;
    }

    public List<SpellCheckSuggestion> checkSpelling(List<String> words, Dictionary dictionary) {
        List<String> items = new ArrayList<>();
        List<SpellCheckSuggestion> spellCorrectedWords = new ArrayList<>();

        for (String word : words) {
            if (dictionary.getWordFrequency(word) > 0) {
                //Keyword  found in the dictionary
                continue;
            }
            // This is the case - Spelling mistake in enetered keyword
            spellCorrectedWords.clear();
            this.generateItems(word, items);

            for (String item : items) {
                int candidateFreq = dictionary.getWordFrequency(item);
                if (candidateFreq > 0) {
                    // add suggestions here
                    spellCorrectedWords.add(new SpellCheckSuggestion(item, candidateFreq));
                }
            }
            if (spellCorrectedWords == null || spellCorrectedWords.isEmpty()) {
                System.out.println("No spell corrections found for word = " + word);
                continue;
            }
            // we found spell corrections
            Collections.sort(spellCorrectedWords);
            for (SpellCheckSuggestion spellCorrectedWord : spellCorrectedWords) {
                System.out.println("Spell corrected word = " + spellCorrectedWord.getWord());
            }
            return spellCorrectedWords;
        }
        return null;
    }

    private void generateItems(String word, List<String> items) {
        items.clear();
        items.addAll(this.deleteItems(word));
        items.addAll(this.insertItems(word));
        items.addAll(this.replaceItems(word));
        items.addAll(this.swapItems(word));
    }


    private List<String> deleteItems(String word) {
        List<String> delItems = new ArrayList<>();
        if (word == null || word.length() == 0) {
            return delItems;
        }
        for (int i = 0; i < word.length(); i++) {
            delItems.add(word.substring(0, i) + word.substring(i + 1, word.length()));
        }
        return delItems;
    }

    private List<String> insertItems(String word) {
        List<String> insertItems = new ArrayList<>();
        if (word == null || word.length() == 0) {
            return insertItems;
        }
        for (int i = 0; i <= word.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                insertItems.add(word.substring(0, i) + j + word.substring(i, word.length()));
            }
        }
        return insertItems;
    }

    private List<String> replaceItems(String word) {
        List<String> replaceItems = new ArrayList<>();
        if (word == null || word.length() == 0) {
            return replaceItems;
        }
        for (int i = 0; i < word.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                replaceItems.add(word.substring(0, i) + j + word.substring(i + 1, word.length()));
            }
        }
        return replaceItems;
    }

    private List<String> swapItems(String word) {
        List<String> swapItems = new ArrayList<>();
        if (word == null || word.length() < 2) {
            return swapItems;
        }
        for (int i = 0; i < word.length() - 1; i++) {
            swapItems.add(word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2, word.length()));
        }
        return swapItems;
    }
}