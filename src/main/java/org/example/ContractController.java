package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
    //the connection with fxml is made with the controller
public class ContractController {
    int indexOfSellElement=0;
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
    @FXML private RadioButton r1;
    @FXML private RadioButton r2;



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
        contract=new Contract();
        firstName.textProperty().bindBidirectional(contract.firstNameProperty());
        lastName.textProperty().bindBidirectional(contract.lastNameProperty());
        address.textProperty().bindBidirectional(contract.addressProperty());
        //speed.textProperty().bindBidirectional(contract.speedProperty());
        //bandwidth.textProperty().bindBidirectional(contract.bandwidthProperty());
        //duration.textProperty().bindBidirectional(contract.durationProperty());
        firstNameColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().lastNameProperty());
        addressColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().addressProperty());
        speedColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().speedProperty());
        bandwidthColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().bandwidthProperty());
        durationColumn.setCellValueFactory(contractStringCellDataFeatures -> contractStringCellDataFeatures.getValue().durationProperty());
        loadDataSpeed();
        loadDataBand();
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
    //radio button


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
        ModelForList contractModel=new ModelForList();
        contractModel.setFirstName(contractViewTable.getFirstName());
        contractModel.setLastName(contractViewTable.getLastName());
        contractModel.setAddress(contractViewTable.getAddress());
        contractModel.setSpeed(contractViewTable.getSpeed());
        contractModel.setBandwidth(contractViewTable.getBandwidth());
        contractModel.setDuration(contractViewTable.getDuration());
        //equals method and hasCode metode not work, we have two different objects but they have the same content
        for (Contract c1:contractList){
                if (c1.getFirstName().equals(contractModel.getFirstName())&&
                        c1.getLastName().equals(contractModel.getLastName())&&
                        c1.getAddress().equals(contractModel.getAddress())&&
                        c1.getSpeed().equals(contractModel.getSpeed())&&
                        c1.getBandwidth().equals(contractModel.getBandwidth())&&
                        c1.getDuration().equals(contractModel.getDuration())){
                    //we can't delete or write a element at the time of iteration;
                    indexOfSellElement=contractList.indexOf(c1);
                }
            }
        contractList.remove(indexOfSellElement);
        contract.setContractList(contractList);
        viewTable();
    }

    //...
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
