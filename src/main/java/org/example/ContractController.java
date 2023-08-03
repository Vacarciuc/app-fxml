package org.example;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.*;

//the connection with fxml is made with the controller
public class ContractController {
    int indexOfSellElement=-1;
    @FXML
    private ChoiceBox<String>stringChoiceBoxSped;
    @FXML
    private ChoiceBox<String>stringChoiceBoxBand;
    @FXML
    private TableView<Contract> contractTable;
    @FXML
    private TableColumn<Contract, String>firstNameColumn;
    @FXML
    private TableColumn<Contract, String>lastNameColumn;
    @FXML
    private TableColumn<Contract, String>addressColumn;
    @FXML
    private TableColumn<Contract, String>speedColumn;
    @FXML
    private TableColumn<Contract, String>bandwidthColumn;
    @FXML
    private TableColumn<Contract, String>durationColumn;
    //radio button
    @FXML
    private String radioButtonDuration = "";
    @FXML
    private RadioButton r1;
    @FXML
    private RadioButton r2;




    //create observable list for choiseBox, when working with tables it is recommended to use observableList/Set/Map
    ObservableList observableListChoiceBoxSpeed=FXCollections.observableArrayList();
    //it's the same for the band
    ObservableList observableListChoiceBoxBand=FXCollections.observableArrayList();

    //because i use choicebox i remove sped textField
    @FXML
    private TextField firstName, lastName, address/*, speed, bandwidth, duration*/;

    Contract contract;

    public ContractController(){

    }

    //The initialize method is called after all @FXML annotated members have been injected.
    @FXML
    private void initialize(){
        //contractTable.setEditable(true);
        contract=new Contract();
        firstName.textProperty().bindBidirectional(contract.firstNameProperty());
        lastName.textProperty().bindBidirectional(contract.lastNameProperty());
        address.textProperty().bindBidirectional(contract.addressProperty());
        //speed.textProperty().bindBidirectional(contract.speedProperty());
        //bandwidth.textProperty().bindBidirectional(contract.bandwidthProperty());
        //duration.textProperty().bindBidirectional(contract.durationProperty());
        firstNameColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().firstNameProperty());
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contract, String> contractStringCellEditEvent) {
                Contract contract= contractStringCellEditEvent.getRowValue();
                contract.setFirstName(contractStringCellEditEvent.getNewValue());
            }
        });
        lastNameColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().lastNameProperty());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contract, String> contractStringCellEditEvent) {
                Contract contract= contractStringCellEditEvent.getRowValue();
                contract.setLastName(contractStringCellEditEvent.getNewValue());
            }
        });
        addressColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().addressProperty());
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contract, String> contractStringCellEditEvent) {
                Contract contract= contractStringCellEditEvent.getRowValue();
                contract.setAddress(contractStringCellEditEvent.getNewValue());
            }
        });
        speedColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().speedProperty());
        speedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        speedColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contract, String> contractStringCellEditEvent) {
                Contract contract= contractStringCellEditEvent.getRowValue();
                contract.setSpeed(contractStringCellEditEvent.getNewValue());
            }
        });
        bandwidthColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().bandwidthProperty());
        bandwidthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bandwidthColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contract, String> contractStringCellEditEvent) {
                Contract contract= contractStringCellEditEvent.getRowValue();
                contract.setBandwidth(contractStringCellEditEvent.getNewValue());
            }
        });
        durationColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().durationProperty());
        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durationColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contract, String> contractStringCellEditEvent) {
                Contract contract= contractStringCellEditEvent.getRowValue();
                contract.setDuration(contractStringCellEditEvent.getNewValue());
            }
        });
        loadDataSpeed();
        loadDataBand();

//        contractTable.setEditable(true);
//        contractTable.getSelectionModel().setCellSelectionEnabled(true);
    }

    //choise box, for speed menu;
    private void loadDataSpeed(){
        observableListChoiceBoxSpeed.removeAll(observableListChoiceBoxSpeed);
        String s1="2", s2="5", s3="10", s4="20", s5="50", s6="100";
        observableListChoiceBoxSpeed.addAll(s1, s2, s3, s4, s5, s6);
        stringChoiceBoxSped.getItems().addAll(observableListChoiceBoxSpeed);
    }

    private void loadDataBand(){
        observableListChoiceBoxBand.removeAll(observableListChoiceBoxBand);
        String b1="1", b2="5", b3="10", b4="100", b5="Flat";
        observableListChoiceBoxBand.addAll(b1, b2, b3, b4, b5);
        stringChoiceBoxBand.getItems().addAll(observableListChoiceBoxBand);
    }


    //save contract caled with button
    @FXML
    private void saveContract(){
        if (contract.isValid()){
            String choiceBoxSpeed=stringChoiceBoxSped.getValue();
            contract.setSpeed(choiceBoxSpeed);
            String choiceBoxBand=stringChoiceBoxBand.getValue();
            contract.setBandwidth(choiceBoxBand);
            if (r1.isSelected()){
                contract.setDuration("1");}
            if (r2.isSelected()){
                contract.setDuration("2");
            }
            contract.save();
            viewTable();
            clearField();
        }else {
            //for saving memory, and not creating new objects we used stringBuilder
            StringBuilder errorMsg=new StringBuilder();
            ArrayList<String>errorList=contract.errorsProperty().get();
            for (String errorsList:errorList){
                errorMsg.append(errorsList);
            }
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Contract can be saved!");
            alert.setHeaderText(null);
            alert.setContentText(errorMsg.toString());
            alert.showAndWait();
            errorList.clear();
        }
    }

    //I changed the button with a typical btn, with an image in the background
    @FXML
    private void closeForm(){
        Platform.exit();
    }

    //after saving the object I release textField
    @FXML
    private void clearField(){
        contract.firstNameProperty().set("");
        contract.lastNameProperty().set("");
        contract.addressProperty().set("");
        contract.speedProperty().set("");
        contract.bandwidthProperty().set("");
        contract.durationProperty().set("");
        stringChoiceBoxSped.setValue(" ");
        stringChoiceBoxBand.setValue(" ");
        r1.setSelected(false);
        r2.setSelected(false);
        System.out.println("clear success");
    }

    //get list of all object and iterate it
    private void viewTable (){
        ObservableList<Contract> contractObservableList = FXCollections.observableArrayList();
        List<Contract>viewContract=contract.getContractList();
        for (Contract s1:viewContract) {
                Contract contractTest=new Contract();
                contractTest.setFirstName(s1.getFirstName());
                contractTest.setLastName(s1.getLastName());
                contractTest.setAddress(s1.getAddress());
                contractTest.setSpeed(s1.getSpeed());
                contractTest.setBandwidth(s1.getBandwidth());
                contractTest.setDuration(s1.getDuration());
            contractObservableList.add(contractTest);
        }
        contractTable.setItems(contractObservableList);
    }

    //deleting a entity by selecting them from the table
    @FXML
    private void deleteItem(){
        Contract contractViewTable=contractTable.getSelectionModel().getSelectedItem();
        List<Contract>contractList=contract.getContractList();
        try {
            if (!contractViewTable.equals("")) {
                ModelForList contractModel = new ModelForList();
                contractModel.setFirstName(contractViewTable.getFirstName());
                contractModel.setLastName(contractViewTable.getLastName());
                contractModel.setAddress(contractViewTable.getAddress());
                contractModel.setSpeed(contractViewTable.getSpeed());
                contractModel.setBandwidth(contractViewTable.getBandwidth());
                contractModel.setDuration(contractViewTable.getDuration());
                //equals method and hasCode metode not work, we have two different objects but they have the same content
                for (Contract c1 : contractList) {
                    if (c1.getFirstName().equals(contractModel.getFirstName()) &&
                            c1.getLastName().equals(contractModel.getLastName()) &&
                            c1.getAddress().equals(contractModel.getAddress()) &&
                            c1.getSpeed().equals(contractModel.getSpeed()) &&
                            c1.getBandwidth().equals(contractModel.getBandwidth()) &&
                            c1.getDuration().equals(contractModel.getDuration())) {
                        //we can't delete or write a element at the time of iteration;
                        indexOfSellElement = contractList.indexOf(c1);
                    }
                }
                contractList.remove(indexOfSellElement);
                contract.setContractList(contractList);
                viewTable();
            }
        }catch (Exception exception){
            exception.getMessage();
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert delete contract!");
            alert.setHeaderText(null);
            alert.setContentText("Please select item from table!");
            alert.show();
        }
    }

    //...
    @FXML
    private void updateItem() throws IOException {
        Contract contractViewTable=contractTable.getSelectionModel().getSelectedItem();
        List<Contract>contractList=contract.getContractList();
        try {
            ModelForList contractModel = new ModelForList();
            contractModel.setFirstName(contractViewTable.getFirstName());
            contractModel.setLastName(contractViewTable.getLastName());
            contractModel.setAddress(contractViewTable.getAddress());
            contractModel.setSpeed(contractViewTable.getSpeed());
            contractModel.setBandwidth(contractViewTable.getBandwidth());
            contractModel.setDuration(contractViewTable.getDuration());
            for (Contract c1 : contractList) {
                if (c1.getFirstName().equals(contractModel.getFirstName()) &&
                        c1.getLastName().equals(contractModel.getLastName()) &&
                        c1.getAddress().equals(contractModel.getAddress()) &&
                        c1.getSpeed().equals(contractModel.getSpeed()) &&
                        c1.getBandwidth().equals(contractModel.getBandwidth()) &&
                        c1.getDuration().equals(contractModel.getDuration())) {
                    indexOfSellElement = contractList.indexOf(c1);
                }
            }
            Contract contractModify = new Contract();
            contractModify = contractList.get(indexOfSellElement);
            System.out.println(contractModify);
            //get selected object to main overview
            contract.setFirstName(contractList.get(indexOfSellElement).getFirstName());
            contract.setLastName(contractList.get(indexOfSellElement).getLastName());
            contract.setAddress(contractList.get(indexOfSellElement).getAddress());
            stringChoiceBoxSped.setValue(contractList.get(indexOfSellElement).getSpeed());
            stringChoiceBoxBand.setValue(contractList.get(indexOfSellElement).getBandwidth());
            String b = contractList.get(indexOfSellElement).getDuration();
            if (b.equals("1")) {
                r1.setSelected(true);
            } else if (b.equals("2")) {
                r2.setSelected(true);
            }
            List<Contract> contractObservableListModify = new ArrayList<>();
            for (Contract c1 : contractList) {
                contractObservableListModify.add(c1);
            }
        }catch (Exception e){
            e.getMessage();
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert delete contract!");
            alert.setHeaderText(null);
            alert.setContentText("Please select item from table!");
            alert.show();
        }
        //test
        //todo
//        PopUpModify popUpModify=new PopUpModify();
//        popUpModify.popUp();
    }

}
