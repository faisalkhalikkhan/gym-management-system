/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc.helper;

import java.util.Properties;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author FAISAL
 */
public class Bill {

    public static void sendBill(String rec,String perc, String BhadweKaNaam, String amount, String plan, String dOfExpire) {
        String to = rec;
        String from = "afcgym85@gmail.com";
        String password = "Afc@1234";

        try {
            Properties prop = new Properties();
            prop.setProperty("mail.smtp.host", "smtp.gmail.com");
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.smtp.socketFactory.port", "465");
            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(prop, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("AFC BILL");
            message.setContent("<h1>Congratulations Dear, " + BhadweKaNaam
                    + "</h1>"+
                    "	<img src=\"D:\\Java\\AFC\\src\\afc\\AFC.jpg\">"
                    + "	<h3>You got "+perc+"% off On your " + plan
                    + " plan</h3><br>"
                    + "	<h5>Now Your Billing Amount is of Rupees " + amount
                    + "</h5>"
                    + "	<h6>Your Plan validity will expire On " + dOfExpire
                    + "</h6><br>"
                    + "	<h1>Thanking You</h1>", "text/html");
            Transport.send(message);
            System.out.println("Bill Sended Successfully !");
            showAlertBox("Bill Sended Successfully !");

        } catch (Exception e) {
            System.out.println("Send Again");
        }
    }
     public static void showAlertBox(String message) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        VBox box = new VBox(new Text(message), new Button("OK"));
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(15));

        dialogStage.setScene(new Scene(box));
        dialogStage.show();
    }

}
