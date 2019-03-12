package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;


public class Controller{

    @FXML
    TextField depositUser1Field;
    @FXML
    TextField transferUser1Field;
    @FXML
    TextField withdrawUser1Field;
    @FXML
    TextField depositUser2Field;
    @FXML
    TextField transferUser2Field;
    @FXML
    TextField withdrawUser2Field;

    @FXML
    Text cashUser1;
    @FXML
    Text cashUser2;

    @FXML
    Text logUser1;
    @FXML
    Text logUser2;

    public int accountUser1=0;
    public int accountUser2=0;



    public void menuClearClicked(){
            accountUser1=0;
            accountUser2=0;
            logUser1.setText(null);
            logUser2.setText(null);
            cashUser1.setText(null);
            cashUser2.setText(null);
            depositUser1Field.setText(null);
            transferUser1Field.setText(null);
            withdrawUser1Field.setText(null);
            depositUser2Field.setText(null);
            transferUser2Field.setText(null);
            withdrawUser2Field.setText(null);
    }

    public void menuAuthorClicked(){
        AlertBox.display("Author", "Author:" +
                "\nMichał Kaczyński" +
                "\ngr. H6X1S1" +
                "\nmichal.kaczynski@student.wat.edu.pl" +
                "\nW A T");
    }

    public void menuExitClicked(){ exit(); }


    //User1
    public void depositUser1(){ Deposit d1 = new Deposit(this,"user1"); }

    public void transferUser1(){ Transfer t1 = new Transfer(this,"user1"); }

    public void withdrawUser1(){ Withdraw w1 = new Withdraw(this,"user1"); }


    //User2
    public void depositUser2(){ Deposit d2 = new Deposit(this,"user2"); }

    public void transferUser2(){ Transfer t2 = new Transfer(this,"user2"); }

    public void withdrawUser2(){ Withdraw w2 = new Withdraw(this,"user2"); }



}
