package com.example.translator;

import org.junit.Assert;
//import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.Test;

public class DictionaryTest {
    protected Map<String, String> dictionary;

    @Test
    public void testGetWords() {
        try (FileInputStream fis = new FileInputStream("D:/dictionary.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            dictionary = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(dictionary.get("try"), "попробовать");
    }

    @Test
    public void testGetCounter() {
        try (FileInputStream fis = new FileInputStream("D:/dictionary.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            dictionary = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String counter = String.valueOf(dictionary.size());
        assertEquals(counter, "1");
    }

    @Test
    public void testAddWord() {
        dictionary = new HashMap<String, String>();
        String english = "try";
        String russian = "попробовать";
        dictionary.put(english, russian);
        Assert.assertEquals(dictionary.containsKey(english),true);
    }

    @Test
    public void testDeleteWord() {
        dictionary = new HashMap<String, String>();
        String english = "try";
        String russian = "попробовать";
        dictionary.put(english, russian);
        dictionary.remove(english, russian);
        Assert.assertEquals(dictionary.containsKey(english),false);
    }

    @Test
    public void testSave() {
    }

    @Test
    public void testLoad() {
        try (FileInputStream fis = new FileInputStream("D:/dictionary.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            dictionary = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(dictionary.isEmpty(), false);
    }
}