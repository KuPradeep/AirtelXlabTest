package com.airtel.xlabs.assignment.model;

public class SpellCheckSuggestion implements Comparable<Object> {
	String word;
	Integer score;

	public SpellCheckSuggestion(String word, Integer score) {
		this.word = word;
		this.score = score;
	}

	public String getWord() {
		return this.word;
	}

	public Integer getScore() {
		return this.score;
	}

	@Override
	public int compareTo(Object other) {
		return ((SpellCheckSuggestion) other).getScore().compareTo(this.getScore());
	}
}
