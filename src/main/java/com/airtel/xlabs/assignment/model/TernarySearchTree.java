package com.airtel.xlabs.assignment.model;

/**
 * Node of a Ternary Search Tree
 */
class TernarySearchTreeNode {
	char val;
	TernarySearchTreeNode left, mid, right;
	int wordFreq;
	boolean endOfWord;

	// Initialize your data structure here.
	public TernarySearchTreeNode() {
		// do nothing
	}

	public TernarySearchTreeNode(char val) {
		this.val = val;
	}
	
	public int getWordFreq() {
		return this.wordFreq;
	}

}

/**
 * Ternary Tree based Trie
 */
public class TernarySearchTree {
	private TernarySearchTreeNode root;

	public TernarySearchTree() {
		root = new TernarySearchTreeNode();
	}

	// Inserts a word into the trie.
	public void insert(String word, int wordFreq) {
		if (word != null && !word.isEmpty())
			insert(word, wordFreq, 0, root);
	}

	private TernarySearchTreeNode insert(String word, int wordFreq, int index, TernarySearchTreeNode node) {
		char ch = word.charAt(index);
		if (node == null)
			node = new TernarySearchTreeNode(ch);
		if (ch < node.val)
			node.left = insert(word, wordFreq, index, node.left);
		else if (ch > node.val)
			node.right = insert(word, wordFreq, index, node.right);
		else if (index < word.length() - 1)
			node.mid = insert(word, wordFreq, index + 1, node.mid);
		else {
			node.endOfWord = true;
			node.wordFreq = wordFreq;
		}
		return node;
	}

	// Returns the leaf node in case word is found else null
	public TernarySearchTreeNode search(String word) {
		if (word == null || word.isEmpty())
			return null;
		return search(word, 0, root);
	}

	private TernarySearchTreeNode search(String word, int index, TernarySearchTreeNode node) {
		if (node == null)
			return null;
		char ch = word.charAt(index);
		if (ch < node.val)
			return search(word, index, node.left);
		if (ch > node.val)
			return search(word, index, node.right);
		if (index < word.length() - 1)
			return search(word, index + 1, node.mid);
		return node.endOfWord ? node : null;
	}
}
