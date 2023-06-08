package controller;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;

import java.net.Socket;
import java.net.URL;

import java.util.ResourceBundle;

public class ClientFormController extends Thread implements Initializable {
    public AnchorPane MainePane;
    public AnchorPane emojiPane;
    public ImageView hahaImg;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    Image image;

    public VBox vbox_messages;
    @FXML
    private Label nameId;

    @FXML
    private TextField txtFeld;

    @FXML
    private ScrollPane scolPane;





    private FileChooser fileChooser;
    private File filePath;



    @FXML
    void ExitOnAction(MouseEvent event) {
        System.exit(0);

    }

    @FXML
    void MiniMiszedOnAction(MouseEvent event) {



    }

    @FXML
    void SendBtnOnAction(MouseEvent event)  {

        String msg = txtFeld.getText();
        writer.println(nameId.getText() + ", " + msg);
        txtFeld.clear();
    }

    @FXML
    void imgOnAction(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);

        writer.println(nameId.getText() + " " + "img" + filePath.getPath());
        System.out.println(filePath);
    }

    @FXML
    void imojiEvent(MouseEvent event) {
        if (emojiPane.isVisible()) {
            emojiPane.setVisible(false);
        }else {
            emojiPane.setVisible(true);
        }
    }

    @FXML
    void sendOnAction(ActionEvent event) {
        String msg = txtFeld.getText();
        writer.println(nameId.getText() + ", " + msg);
        txtFeld.clear();
    }

    @Override
    public void run() {
        try {
            while (true) {


                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];


                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]+" ");
                }


                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }


                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }
                if (firstChars.equalsIgnoreCase("emo")) {
                    st = st.substring(3, st.length() - 1);

                    int emoji=Integer.parseInt(st);
                    switch (emoji){
                        case 1:
                            image=new Image("/view/image/imoji/1.png");
                            System.out.println("1");
                            break;
                        case 2:
                            image=new Image("/view/image/imoji/2.png");
                            System.out.println("2");
                            break;
                        case 3:
                            image=new Image("/view/image/imoji/3.png");
                            System.out.println("3");
                            break;
                        case 4:
                            image=new Image("/view/image/imoji/4.png");
                            System.out.println("4");
                            break;
                        case 5:
                            image=new Image("/view/image/imoji/5.png");
                            System.out.println("5");
                            break;
                        case 6:
                            image=new Image("/view/image/imoji/6.png");
                            System.out.println("6");
                            break;
                        case 7:
                            image=new Image("/view/image/imoji/7.png");
                            System.out.println("7");
                            break;
                        case 8:
                            image=new Image("/view/image/imoji/8.png");
                            System.out.println("8");
                            break;
                        case 9:
                            image=new Image("/view/image/imoji/9.png");
                            System.out.println("9");
                            break;
                    }


                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(60);
                    imageView.setFitWidth(60);

                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(nameId.getText())) {

                        vbox_messages.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                        hBox.setPadding(new Insets(5,5,4,10));

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(" ");
                        hBox.getChildren().add(text1);
                        hBox.setPadding(new Insets(5,5,4,10));


                    }

                    Platform.runLater(() -> vbox_messages.getChildren().addAll(hBox));


                }




               else if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);


                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(100);
                    imageView.setFitWidth(150);



                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(nameId.getText())) {

                        vbox_messages.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                        hBox.setPadding(new Insets(5,5,4,10));

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(" ");
                        hBox.getChildren().add(text1);
                        hBox.setPadding(new Insets(5,5,4,10));


                    }

                    Platform.runLater(() -> vbox_messages.getChildren().addAll(hBox));


                } else  {
                    TextFlow tempFlow = new TextFlow();


                    if (!cmd.equalsIgnoreCase(nameId.getText() + ",")) {
                        Text txtName = new Text(cmd + " \n"+"  ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);

                        tempFlow.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: #FFFFFF;" +
                                " -fx-background-radius: 10px");
                        tempFlow.setPadding(new Insets(3,10,3,10));
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200);

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12);




                    if (!cmd.equalsIgnoreCase(nameId.getText() + ",")) {


                        vbox_messages.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);
                        hBox.setPadding(new Insets(2,5,2,10));

                    } else {

                        Text text2 = new Text(fullMsg + " ");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                        hBox.setPadding(new Insets(2,5,2,10));

                        flow2.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: #DCF8C6;" +
                                "-fx-background-radius: 10px");
                        flow2.setPadding(new Insets(3,10,3,10));

                    }

                    Platform.runLater(() -> vbox_messages.getChildren().addAll(hBox));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String name=LoginFormController.name;
        nameId.setText(name);
        try {
            socket = new Socket("localhost", 50000);
            System.out.println(name+" connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    void angrySelected(MouseEvent event) {

        writer.println(nameId.getText() + " " + "emo" + "7");
        emojiPane.setVisible(false);
    }

    @FXML
    void coolSlected(MouseEvent event) {
        writer.println(nameId.getText() + " " + "emo" + "4");
        emojiPane.setVisible(false);
    }

    @FXML
    void danceSSelected(MouseEvent event) {
        writer.println(nameId.getText() + " " + "emo" + "9");
        emojiPane.setVisible(false);
    }

    @FXML
    void hahaSelect(MouseEvent event) {
        writer.println(nameId.getText() + " " + "emo" + "1");
        emojiPane.setVisible(false);
    }


    @FXML
    void lolslected(MouseEvent event) {
        writer.println(nameId.getText() + " " + "emo" + "8");
        emojiPane.setVisible(false);
    }

    @FXML
    void loveSelect(MouseEvent event) {
        writer.println(nameId.getText() + " " + "emo" + "3");
        emojiPane.setVisible(false);

    }

    @FXML
    void sadselected(MouseEvent event) {
        writer.println(nameId.getText() + " " + "emo" + "5");
        emojiPane.setVisible(false);

    }
    

    @FXML
    void smaileslected(MouseEvent event) {
        writer.println(nameId.getText() + " " + "emo" + "6");
        emojiPane.setVisible(false);
    }

    @FXML
    void wowSlected(MouseEvent event) {
        writer.println(nameId.getText() + " " + "emo" + "2");
        emojiPane.setVisible(false);
    }



}
