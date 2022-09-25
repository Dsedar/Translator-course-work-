package com.example.translator;

import java.util.Map;
import java.util.Set;

public class Translator {
	Set<Map.Entry<String, String>> vocabularySet;

	public Translator(Dictionary dictionary) {
		vocabularySet = dictionary.getDictionary().entrySet();
	}

	public String TranslateEnglishToRussia(String phrase) {
		String[] str = phrase.split(" ");
		for (int i = 0; i < str.length; i++) {
			int j = 0;
			if (i == 0) {
				System.out.println();
			}
			for (Map.Entry<String, String> entry : vocabularySet) {
				if (entry.getKey().equalsIgnoreCase(str[i])) {
					System.out.print(entry.getValue() + " ");
					phrase = entry.getValue();
					break;
				}

				else if (j == vocabularySet.size() - 1) {
					System.out.print(str[i] + " ");
				}
				j++;
			}
		}
		return phrase;
	}

	public String TranslateRussiaToEnglish(String phrase) {
		String[] str = phrase.split(" ");
		for (int i = 0; i < str.length; i++) {
			int j = 0;
			if (i == 0) {
				System.out.println();
			}
			for (Map.Entry<String, String> entry : vocabularySet) {
				if (entry.getValue().equalsIgnoreCase(str[i])) {
					System.out.print(entry.getKey() + " ");
					phrase = entry.getKey();
					break;
				}

				else if (j == vocabularySet.size() - 1) {
					System.out.print(str[i] + " ");
				}
				j++;
			}
		}
		return phrase;
	}
}
