package org.ce.ap.com.company.server.service;

import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import org.ce.ap.com.company.server.model.Account;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * chatroom class for handling chats
 * @author ABTIN ZANDI
 */
public class ChatRoom extends ChatRoomFile {


    ArrayList<String> Title = new ArrayList<>(); // ChatRoom Titles

    //ChatRoom
    @FXML
    private Button ExitChat;
    @FXML
    private Text GroupName;
    @FXML
    private TextField NewMassage;
    @FXML
    private Button Refresh;
    @FXML
    private Button SendMassage;
    @FXML
    private BorderPane massges;
    @FXML
    private ScrollBar scroll;

    //Create New Chat
    @FXML
    private Button CreateButton;
    @FXML
    private Text CreateWarning;
    @FXML
    private TextField NewChatRoomName;

    //JoinChat
    @FXML
    private Button AllChatButton;
    @FXML
    private Button SearchCreateButton;
    @FXML
    private Button SearchButton;
    @FXML
    private TextField SearchChatRoomName;
    @FXML
    private Text SearchChatRoomNameWarning;

    //Massages
    @FXML
    private Text MassageText;
    @FXML
    private BorderPane massgesScale;
    @FXML
    private Rectangle Rec;

    //side Menu Button
    @FXML
    private Button sideMenu;

    private ArrayList<BorderPane> Massages = new ArrayList<>();

    private ClientFileHandler clientFileHandler = new ClientFileHandler();
    //Massages Create
    public void MassagePreproccesor(String ChatName){
        ArrayList<String> chats= new ArrayList<>();
        chats.addAll(chatDetails(ChatName));
        for(String text : chats){
            MassageText.setText(text);
            massgesScale.setCenter(MassageText);
            massgesScale.setRight(Rec);
            BorderPane massge = massgesScale;
            Massages.add(massgesScale);
        }
    }

    /***
     * this method will help us to join in chat rooms
     * @param actionEvent
     */
    public void JoinEvent(ActionEvent actionEvent) {

        if(!SearchChatroom(SearchChatRoomName.getText())){
            SearchChatRoomNameWarning.setText("Chat room("+SearchChatRoomName.getText()+") isnt already exist");
        }
        else{
            int clientNumber = clientFileHandler.getFxmlState("JoinChatRoom");
            clientFileHandler.updateClient(clientNumber,"ChatRoom");
            clientFileHandler.updateServerFXML(clientNumber,"JoinChatRoom",SearchChatRoomName.getText());
        }
    }

    public void AllChatsEvent(ActionEvent actionEvent) {
        //////////////////////////////////////////////////////////////////////////////
    }

    public void RefreshButton(ActionEvent actionEvent) {

    }

    public void ExitChatButton(ActionEvent actionEvent) {
    }

    public void sideMenuButton(ActionEvent actionEvent) {
    }

    /**
     * this method will help us to control (JoinChat --> search )
     * @param actionEvent ,
     */
    public void SearchCreateButton(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("JoinChatRoom");
        clientFileHandler.updateClient(clientNumber,"CreatNewChat");
    }

    //create chat
    public void CreateEvent(ActionEvent actionEvent) {
        if(SearchChatroom(NewChatRoomName.getText())){
            CreateWarning.setText("Chat room("+NewChatRoomName.getText()+") is already exist");
        }
        else{
            chatBuilder(NewChatRoomName.getText());
            CreateWarning.setText("Chat room("+NewChatRoomName.getText()+") has ");
        }
    }

    /**
     * this method will help us to show all titles
     * @param handler
     */
    public void getTitles(ClientHandler handler) {
        String showAllTitles = "";
        for(String title : Title){
            showAllTitles += title+"\n";
        }
    }

    public void setTitle(String title) {
        Title.add(title);
    }


    /**
     * this method will add a new chat room to server
     * @param title,
     */
    public void chatBuilder(String title){
        NewChatRoom(title);
        setTitle(title);
    }

    //Chat Room

    public void setMassges(BorderPane Pane) {
        massges.getChildren().add(Pane);
    }


    /**
     * this method will help us to chat in specific chat room
     * @param user
     * @param handler
     * @param title
     */
    public void chatRoom(Account user , ClientHandler handler,String title){
        boolean Refresh = false;
        //String chatMassages = chatDetails(title);
        while (true){
            handler.outputStream("--> \"Twitter\"\nChat room("+title+") :\n"+chatDetails(title));
            handler.outputStream("\n1)Refresh\n2)new massage\nEny Other Keyboard : Exit");
            String choice = handler.inputStream();
            if (choice.equals("1")) {
                Refresh = true;
            } else if (choice.equals("2")) {
                handler.outputStream("Please Write your new massage:");
                String massage = handler.inputStream();

                UpdateChatRoom(user.getUserName()+": " + massage,title);
            }
            else{
                break;
            }
        }

        if(Refresh){
            chatRoom(user ,handler,title);
        }
    }



}