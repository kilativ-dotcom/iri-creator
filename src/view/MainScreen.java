package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Iri;
import model.IriTemplate;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class MainScreen extends GridPane {
    public MainScreen() {
        Label iriLabel = new Label("Enter Iri");
        TextField iriTextField = new TextField();
        CheckBox isReversed = new CheckBox("iri is reversed");

        RadioButton isPredicateRadioButton = new RadioButton("predicate");
        RadioButton isCimTypeRadioButton = new RadioButton("cim type");
        RadioButton isLogicCimTypeRadioButton = new RadioButton("logic cim type");
        RadioButton isBinaryRelationRadioButton = new RadioButton("binary relation");
        RadioButton isBinaryRelationIntoLiteralRadioButton = new RadioButton("binary relation into literal");
        RadioButton isQuasyBinaryRelationRadioButton = new RadioButton("quasy binary relation");

        ToggleGroup toggleGroup = new ToggleGroup();
        isPredicateRadioButton.setToggleGroup(toggleGroup);
        isCimTypeRadioButton.setToggleGroup(toggleGroup);
        isLogicCimTypeRadioButton.setToggleGroup(toggleGroup);
        isBinaryRelationRadioButton.setToggleGroup(toggleGroup);
        isBinaryRelationIntoLiteralRadioButton.setToggleGroup(toggleGroup);
        isQuasyBinaryRelationRadioButton.setToggleGroup(toggleGroup);
        isCimTypeRadioButton.setSelected(true);

        CheckBox createReversedIri = new CheckBox("CreateReversedIri");
        TextArea createdIri = new TextArea();
        createdIri.setEditable(false);
        createdIri.setOnMouseClicked(event -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(createdIri.getText()), null);
        });

        add(iriTextField, 5, 5, 5, 1);
        add(iriLabel, 7, 4);
        add(isReversed, 10, 5);

        add(isPredicateRadioButton, 6, 8);
        add(isCimTypeRadioButton, 7, 8);
        add(isLogicCimTypeRadioButton, 8, 8);
        add(isBinaryRelationRadioButton, 6, 9);
        add(isBinaryRelationIntoLiteralRadioButton, 7, 9);
        add(isQuasyBinaryRelationRadioButton, 8, 9);


        add(createReversedIri, 8, 11);

        add(createdIri, 6, 12, 5, 10);

        iriTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateIri(newValue, isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });

        isPredicateRadioButton.setOnAction(event -> {
            updateIri(iriTextField.getText(), isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });
        isCimTypeRadioButton.setOnAction(event -> {
            updateIri(iriTextField.getText(), isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });
        isLogicCimTypeRadioButton.setOnAction(event -> {
            updateIri(iriTextField.getText(), isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });
        isBinaryRelationRadioButton.setOnAction(event -> {
            updateIri(iriTextField.getText(), isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });
        isBinaryRelationIntoLiteralRadioButton.setOnAction(event -> {
            updateIri(iriTextField.getText(), isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });
        isQuasyBinaryRelationRadioButton.setOnAction(event -> {
            updateIri(iriTextField.getText(), isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });
        createReversedIri.setOnAction(event -> {
            updateIri(iriTextField.getText(), isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });
        isReversed.setOnAction(event -> {
            updateIri(iriTextField.getText(), isReversed, isPredicateRadioButton, createReversedIri, isLogicCimTypeRadioButton, isCimTypeRadioButton, isBinaryRelationIntoLiteralRadioButton, isBinaryRelationRadioButton, isQuasyBinaryRelationRadioButton, createdIri);
        });


    }

    private static void updateIri(String newValue, CheckBox isReversed, RadioButton isPredicateRadioButton, CheckBox createReversedIri, RadioButton isLogicCimTypeRadioButton, RadioButton isCimTypeRadioButton, RadioButton isBinaryRelationIntoLiteralRadioButton, RadioButton isBinaryRelationRadioButton, RadioButton isQuasyBinaryRelationRadioButton, TextArea createdIri) {
        try {
            Iri iri = new Iri(newValue, isReversed.isSelected());
            String scsIri = "";
            if (isPredicateRadioButton.isSelected()) {
                scsIri = IriTemplate.PredicateTemplate.createIri(iri, createReversedIri.isSelected());
            } else if (isLogicCimTypeRadioButton.isSelected()) {
                scsIri = IriTemplate.LogicCimTypeTemplate.createIri(iri, createReversedIri.isSelected());
            } else if (isCimTypeRadioButton.isSelected()) {
                scsIri = IriTemplate.CimTypeTemplate.createIri(iri, createReversedIri.isSelected());
            } else if (isBinaryRelationIntoLiteralRadioButton.isSelected()) {
                scsIri = IriTemplate.BinaryRelationIntoLiteralTemplate.createIri(iri, createReversedIri.isSelected());
            } else if (isBinaryRelationRadioButton.isSelected()) {
                scsIri = IriTemplate.BinaryRelationTemplate.createIri(iri, createReversedIri.isSelected());
            } else if (isQuasyBinaryRelationRadioButton.isSelected()) {
                scsIri = IriTemplate.QuasyBinaryRelationTemplate.createIri(iri, createReversedIri.isSelected());
            }
            createdIri.setText(scsIri);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
