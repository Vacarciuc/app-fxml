package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContractController {
    int indexOfSellElement=0;
    @FXML
    private ChoiceBox<String>stringChoiceBox;
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


//    @FXML
//    private TableView firstNameColumn, lastNameColumn, addressColumn, speedColumn, bandwidthColumn, durationColumn;

    //create observable list for choiseBox
    ObservableList observableListChoiseBox=FXCollections.observableArrayList();

    @FXML
    private TextField firstName, lastName, address, speed, bandwidth, duration;

    Contract contract;

    public ContractController(){

    }

    @FXML
    private void initialize(){
        contract=new Contract();

        firstName.textProperty().bindBidirectional(contract.firstNameProperty());
        lastName.textProperty().bindBidirectional(contract.lastNameProperty());
        address.textProperty().bindBidirectional(contract.addressProperty());
        speed.textProperty().bindBidirectional(contract.speedProperty());
        bandwidth.textProperty().bindBidirectional(contract.bandwidthProperty());
        duration.textProperty().bindBidirectional(contract.durationProperty());
        firstNameColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().lastNameProperty());
        addressColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().addressProperty());
        speedColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().speedProperty());
        bandwidthColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().bandwidthProperty());
        durationColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().durationProperty());
        loadDataSpeed();
    }
    //choise box, for speed menu;
    private void loadDataSpeed(){
        observableListChoiseBox.removeAll(observableListChoiseBox);
        String s1="2", s2="5", s3="10", s4="20", s5="50", s6="100";
        observableListChoiseBox.addAll(s1, s2, s3, s4, s5, s6);
        stringChoiceBox.getItems().addAll(observableListChoiseBox);
    }

    //
    @FXML
    private void saveContract(){
        if (contract.isValid()){
            System.out.println("mes123!");
            String choiseBoxSpeed=stringChoiceBox.getValue();
            contract.setSpeed(choiseBoxSpeed);
            System.out.println("speed"+choiseBoxSpeed);
            contract.save();
            viewTable();
            clearField();
        }else {
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

    @FXML
    private void closeForm(){
        Platform.exit();
    }
    @FXML
    private void clearField(){
        contract.firstNameProperty().set("");
        contract.lastNameProperty().set("");
        contract.addressProperty().set("");
        contract.speedProperty().set("");
        contract.bandwidthProperty().set("");
        contract.durationProperty().set("");
        System.out.println("clear success");
    }
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
    @FXML
    private void deleteItem(){
        Contract contractViewTable=contractTable.getSelectionModel().getSelectedItem();
        List<Contract>contractList=contract.getContractList();
        ModelForList contractModel=new ModelForList();
        contractModel.setFirstName(contractViewTable.getFirstName());
        contractModel.setLastName(contractViewTable.getLastName());
        contractModel.setAddress(contractViewTable.getAddress());
        contractModel.setSpeed(contractViewTable.getSpeed());
        contractModel.setBandwidth(contractViewTable.getBandwidth());
        contractModel.setDuration(contractViewTable.getDuration());
        //equals method and hasCode metode not work, we have two different objects but they have the same content
        for (Contract c1:contractList){
            System.out.println(c1);
            System.out.println(contractModel);
            System.out.println("Iterate list");
                if (c1.getFirstName().equals(contractModel.getFirstName())&&
                        c1.getLastName().equals(contractModel.getLastName())&&
                        c1.getAddress().equals(contractModel.getAddress())&&
                        c1.getSpeed().equals(contractModel.getSpeed())&&
                        c1.getBandwidth().equals(contractModel.getBandwidth())&&
                        c1.getDuration().equals(contractModel.getDuration())){
                    System.out.println("they are equals");
                    //we can't delete or write a element at the time of iteration;
                    indexOfSellElement=contractList.indexOf(c1);
                    System.out.println("find object");
                }
            }
        System.out.println("after if");
        contractList.remove(indexOfSellElement);
        contract.setContractList(contractList);
        System.out.println("delete succes");
        viewTable();
    }

    @FXML
    private void updateItem() throws IOException {
        Contract contractViewTable=contractTable.getSelectionModel().getSelectedItem();
        List<Contract>contractList=contract.getContractList();
        ModelForList contractModel=new ModelForList();
        contractModel.setFirstName(contractViewTable.getFirstName());
        contractModel.setLastName(contractViewTable.getLastName());
        contractModel.setAddress(contractViewTable.getAddress());
        contractModel.setSpeed(contractViewTable.getSpeed());
        contractModel.setBandwidth(contractViewTable.getBandwidth());
        contractModel.setDuration(contractViewTable.getDuration());
        for (Contract c1:contractList){
            if (c1.getFirstName().equals(contractModel.getFirstName())&&
                    c1.getLastName().equals(contractModel.getLastName())&&
                    c1.getAddress().equals(contractModel.getAddress())&&
                    c1.getSpeed().equals(contractModel.getSpeed())&&
                    c1.getBandwidth().equals(contractModel.getBandwidth())&&
                    c1.getDuration().equals(contractModel.getDuration())){
                indexOfSellElement=contractList.indexOf(c1);
            }
        }
        Contract contractModify=new Contract();
        contractModify=contractList.get(indexOfSellElement);
        System.out.println(contractModify);
        ///
//        Popup popup = new Popup();
//        PopUpModify controller = new PopUpModify();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("popUpModify.fxml"));
//        loader.setController(controller);
//        popup.getContent().add((Parent)loader.load());
    }
}
