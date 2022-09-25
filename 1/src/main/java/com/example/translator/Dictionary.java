package com.example.translator;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.MultipleSelectionModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dictionary implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Map<String, String> dictionary;
	
	public Dictionary() {
		dictionary = new HashMap<String, String>();
	}


	public ArrayList<String> GetWords(){
		ArrayList<String> words = new ArrayList<String>(dictionary.values());
		return words;
	}

	public String GetCounter(){
		String counter = String.valueOf(dictionary.size());
		return counter;
	}


	public void addWord(String english, String russia) {
		if(dictionary.containsKey(english)){
			System.out.println("Значение уже есть в словаре");
		}
		else{
			dictionary.put(english, russia);
		}
	}

	public void deleteWord(String english, String russia) {
		dictionary.remove(english, russia);
	}

	public Map<String, String> getDictionary() {
		return dictionary;
	}

	public void save(String path) {
		try (FileOutputStream fos = new FileOutputStream(path);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(dictionary);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load(String path) {
		try (FileInputStream fis = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			dictionary = (Map<String, String>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
