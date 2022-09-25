package com.example.translator;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import static org.testng.Assert.*;

public class TranslatorTest {
    protected Map<String, String> dictionary;


    @Test
    public void testTranslateEnglishToRussia() {
        try (FileInputStream fis = new FileInputStream("D:/dictionary.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            dictionary = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(dictionary.get("try"), "попробовать");
    }

    @Test
    public void testTranslateRussiaToEnglish() {
        try (FileInputStream fis = new FileInputStream("D:/dictionary.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            dictionary = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        String english = null;
        
        for (Object o : dictionary.keySet()) {
            if (dictionary.get(o).equals("попробовать")) {
                english = (String) o;
            }
        }
        assertEquals(english, "try");
    }
}