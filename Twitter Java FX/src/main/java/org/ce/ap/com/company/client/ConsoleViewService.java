package org.ce.ap.com.company.client;

import com.fxcode.twitterjavafx.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

import java.io.IOException;
import java.util.Objects;

public class ConsoleViewService extends Application{

    public String FXMLAddress;

    public  void setFXMLAddress(String address) {
        FXMLAddress = address;
    }

    public String getFXMLAddress() {
       //System.out.println(FXMLAddress+"ll");
        return FXMLAddress;
    }

    @Override
    public void start(Stage stage) {
        try{
            if(FXMLAddress.equals("FirstMenu.fxml")){
                //System.out.println("success");
                //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FirstMenu.fxml"));
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstMenu.fxml")));
                //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Twitter");
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consolePrint(InputStream input, OutputStream output,String[] args){
        try {
            while (true) {
                byte[] bufferMenu = new byte[50000];
                int transfer = input.read(bufferMenu);

                if(transfer>-1){
                    String Address = new String(bufferMenu, 0, transfer);
                    setFXMLAddress(Address);
                    main(args);
                    break;
                }
            }
        }catch (Exception error) {
            error.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
