package sample;

import javafx.application.Platform;

import java.util.Objects;

public class Withdraw {

    private Controller controller;
    private String user;
    private volatile boolean flag1 = false;
    private volatile boolean flag2 = false;
    private volatile int turn = 1;
    private boolean subtractedUsr1 = false;
    private boolean subtractedUsr2 = false;

    Withdraw(Controller controller, String user){
        this.controller = controller;
        this.user = user;
        Thread t3 = new Thread(r1);
        Thread t4 = new Thread(r2);
        t3.setName("Thread 3");
        t4.setName("Thread 4");
        t3.start();
        t4.start();
    }




    Runnable r1 = () -> {
        flag1 = true;
        while (flag2) {
            if (turn != 1) {
                flag1 = false;
                while (turn != 1);
                flag1 = true;
            }
        }


        if(user.equals("user1")) {
            String withdrawFieldString = controller.withdrawUser1Field.getText();
            if ((!withdrawFieldString.equals("")) && (Integer.parseInt(withdrawFieldString) > 0)) {

                if((controller.accountUser1 >= 0) && (Integer.parseInt(withdrawFieldString) <= controller.accountUser1)) {
                    subtractedUsr1 = true;
                    System.out.println("Account user1=$"+controller.accountUser1);
                    //Sub to account field
                        controller.accountUser1 -= Integer.parseInt(controller.withdrawUser1Field.getText());
                    //Cash In Bank User1
                    controller.cashUser1.setText("$" + String.valueOf(controller.accountUser1));



                    System.out.println(Thread.currentThread().getName()+" subtract cash from user1's account");
                }else{
                    Platform.runLater(
                            () -> AlertBox.display("Fail", "Amount to withdraw \nis greater than amount of user1's \naccount or its $0 account"));
                    System.out.println("Amount to withdraw is greater than amount of user1's account or its $0 account");
                }


            } else if (Objects.equals(withdrawFieldString, "0")) {
                Platform.runLater(
                        () -> AlertBox.display("Fail", "You cannot withdraw $0!"));

            } else {
                Platform.runLater(
                        () -> AlertBox.display("Fail", "You must withdraw positive number!"));
            }
        }else{
            String withdrawFieldString = controller.withdrawUser2Field.getText();
            if ((!withdrawFieldString.equals("")) && (Integer.parseInt(withdrawFieldString) > 0)) {

                if((controller.accountUser2 >= 0) && (Integer.parseInt(withdrawFieldString) <= controller.accountUser2)) {
                    subtractedUsr2=true;
                    //Sub from account field
                        controller.accountUser2 -= Integer.parseInt(controller.withdrawUser2Field.getText());
                    //Cash In Bank User2
                    controller.cashUser2.setText("$" + String.valueOf(controller.accountUser2));

                    System.out.println("Account user2=$"+controller.accountUser2);
                    System.out.println(Thread.currentThread().getName()+" subtract cash from user2's account");
                }else{
                    Platform.runLater(
                            () -> AlertBox.display("Fail", "Amount to withdraw \nis greater than amount of user2's \naccount or its $0 account"));
                    System.out.println("Amount to withdraw is greater than amount of user2's account or its $0 account");
                }


            } else if (Objects.equals(withdrawFieldString, "0")) {
                Platform.runLater(
                        () -> AlertBox.display("Fail", "You cannot withdraw $0!"));

            } else {
                Platform.runLater(
                        () -> AlertBox.display("Fail", "You must withdraw positive number!"));
            }
        }



        turn = 2;
        flag1 = false;

    };

    Runnable r2 = () -> {
        flag2 = true;
        while (flag1) {
            if (turn != 2) {
                flag2 = false;
                while (turn != 2) ;
                flag2 = true;
            }
        }

        if (user.equals("user1")) {
            String withdrawFieldString = controller.withdrawUser1Field.getText();
            String log = controller.logUser1.getText();

                if((!withdrawFieldString.equals("")) && (Integer.parseInt(withdrawFieldString) > 0)) {
                    if(((controller.accountUser1>0) && (Integer.parseInt(withdrawFieldString) <= controller.accountUser1 )) || subtractedUsr1) {

                        //Log User1
                        log += "\n-Withdrawal $" + controller.withdrawUser1Field.getText();
                        controller.logUser1.setText(log);

                        System.out.println("Account user1=$" + controller.accountUser1);
                        System.out.println(Thread.currentThread().getName() + " added info to user1's log");
                        subtractedUsr1=false;
                    }
                }

        }else{
            String withdrawFieldString = controller.withdrawUser2Field.getText();
            String log = controller.logUser2.getText();

            if((!withdrawFieldString.equals("")) && (Integer.parseInt(withdrawFieldString) > 0)) {
                if(((controller.accountUser2 >= 0) && (Integer.parseInt(withdrawFieldString) <= controller.accountUser2)) || subtractedUsr2) {
                    //Log User2
                    log += "\n-Withdrawal $" + controller.withdrawUser2Field.getText();
                    controller.logUser2.setText(log);

                    System.out.println("Account user2=$" + controller.accountUser2);
                    System.out.println(Thread.currentThread().getName() + " added info to user2's log");
                    subtractedUsr2=false;
                }
            }

        }
        turn = 1;
        flag2 = false;

    };



}
