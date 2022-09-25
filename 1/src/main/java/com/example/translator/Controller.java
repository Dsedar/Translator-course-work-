package com.example.translator;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;

public class Controller {

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField searchBar;

    @FXML
    private TextField Counter;

    @FXML
    private Button AddButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button DictionaryButton;

    @FXML
    private TextField EnglishField;

    @FXML
    private Button LoadDictionaryButton;

    @FXML
    private Button MoreButton;

    @FXML
    private TextField RussianField;

    @FXML
    private Button SaveDictionaryButton;

    @FXML
    private Button TranslateButton;

    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),dictionary.GetWords()));
    }

    String russiantext, englishtext;
    Dictionary dictionary = new Dictionary();
    Translator translator;

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }


    void CallMore() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("More.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage1 = new Stage();
        stage1.setTitle("More");
        stage1.setScene(new Scene(root));
        stage1.showAndWait();
    }


    @FXML
    void initialize() {
        listView.getItems().addAll(dictionary.GetWords());
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("ListView selection changed from oldValue = "
                        + oldValue + " to newValue = " + newValue);
                RussianField.setText(newValue);
            }
        });

        DictionaryButton.setOnAction(actionEvent -> {
            System.out.println("Dictionary Work");
            if (dictionary.getDictionary().size() == 0)
                System.out
                        .println("Your dictionary is empty! Please add word or load dictionary from file.");
            else System.out.println(dictionary.getDictionary());
        });
        MoreButton.setOnAction(actionEvent -> {
            System.out.println("More Work");
            CallMore();
        });
        TranslateButton.setOnAction(actionEvent -> {
            System.out.println("Translate Work");
            if(RussianField.getText().length()==0){
                englishtext = EnglishField.getText().trim();
                translator = new Translator(dictionary);
                RussianField.setText(translator.TranslateEnglishToRussia(englishtext));
            }
            else{
                russiantext = RussianField.getText().trim();
                translator = new Translator(dictionary);
                EnglishField.setText(translator.TranslateRussiaToEnglish(russiantext));
            }
        });
        AddButton.setOnAction(actionEvent -> {
            System.out.println("Add Work");
            russiantext = RussianField.getText().trim();
            englishtext = EnglishField.getText().trim();
            dictionary.addWord(englishtext, russiantext);
        });
        DeleteButton.setOnAction(actionEvent -> {
            System.out.println("Delete Work");
            russiantext = RussianField.getText().trim();
            englishtext = EnglishField.getText().trim();
            dictionary.deleteWord(englishtext, russiantext);
        });
        LoadDictionaryButton.setOnAction(actionEvent -> {
            System.out.println("Load Dictionary Work");
            String path ="src/dictionary.txt";
            dictionary.load(path);
            Counter.setText(dictionary.GetCounter());
        });
        SaveDictionaryButton.setOnAction(actionEvent -> {
            System.out.println(" Save Dictionary Work");
            String path ="src/dictionary.txt";
            dictionary.save(path);
        });
    }
}

