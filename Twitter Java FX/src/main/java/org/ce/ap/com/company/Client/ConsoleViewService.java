package org.ce.ap.com.company.Client;

import com.fxcode.twitterjavafx.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ce.ap.com.company.server.service.ClientFileHandler;
import java.io.*;
import java.io.IOException;
public class ConsoleViewService extends Application{

    public static String FXMLAddress;
    public static String NextStage;
    private static OutputStream outputStream;
    private static ClientFileHandler clientFileHandler = new ClientFileHandler();
    private int ClientNumber = 0 ;
    public void setFXMLAddress(String address) {
        FXMLAddress = address;
    }

    public static void setNextStage(String nextStage) {
        NextStage = nextStage;
    }

    public static String getNextStage() {
        return NextStage;
    }

    public static void setOutputStream(OutputStream output) {
        outputStream = output;
    }

    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(FXMLAddress));
            System.out.println(FXMLAddress);
            System.out.println(fxmlLoader.getLocation());
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();

        }catch (Exception error){
            System.out.println();
        }

    }

    @Override
    public void stop() throws Exception {
        try {
            NextStage = clientFileHandler.getStatus(ClientNumber);
            outputStream.write(NextStage.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.stop();
    }

    /**
     * this method will help us to manage server inputs
     * @param input ,
     * @param output ,
     */
    public void consoleInputPrint(InputStream input, OutputStream output) {
        setOutputStream(output);
        try {
            byte[] bufferMenu = new byte[50000];
            ClientNumber = clientFileHandler.getCurrentClient();
            int transfer = input.read(bufferMenu);
            if(transfer!=-1){
                String Address = new String(bufferMenu, 0, transfer);
                setFXMLAddress(Address);
                System.out.println(Address);
                launch();
            }
        }catch (Exception error) {
            System.out.println();
        }
    }

}