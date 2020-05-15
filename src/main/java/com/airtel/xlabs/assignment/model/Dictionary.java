package com.airtel.xlabs.assignment.model;
import java.util.List;

public class Dictionary {
	private com.airtel.xlabs.assignment.model.TernarySearchTree root = new com.airtel.xlabs.assignment.model.TernarySearchTree();
	public Dictionary(String wordFreqFileName) {
		System.out.println("Loading dictionary");
		this.loadDictionary(wordFreqFileName);
		System.out.println("Loaded dictionary");
	}

	private void loadDictionary(String wordFreqFileName) {
		com.airtel.xlabs.assignment.util.FileScanner fs = new com.airtel.xlabs.assignment.util.FileScanner();
		List<List<String>> wordsWithFreq = fs.readRecords(wordFreqFileName);
		for (List<String> wordWithFreq : wordsWithFreq) {
			String word = wordWithFreq.get(0);
			int wordFreq = Integer.parseInt(wordWithFreq.get(1));
			root.insert(word, wordFreq);
		}
	}

	public int getWordFrequency(String word) {
		com.airtel.xlabs.assignment.model.TernarySearchTreeNode node = this.root.search(word);
		if (node != null) {
			return node.getWordFreq();
		}
		return 0;
	}

}
