package sample;


import javafx.application.Platform;

import java.util.Objects;

public class Deposit{
    private Controller controller;
    private String user;
    private volatile boolean flag1 = false;
    private volatile boolean flag2 = false;
    private volatile int turn = 1;

    Deposit(Controller controller, String user) {
        this.controller = controller;
        this.user = user;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.setName("Thread 1");
        t2.setName("Thread 2");
        t1.start();
        t2.start();
    }

    Runnable r1 = () -> {
            flag1 = true;
            while (flag2) {
                if (turn != 1) {
                    flag1 = false;
                    while (turn != 1) ;
                    flag1 = true;
                }
            }

            if (user.equals("user1")) {
                String depositFieldString = controller.depositUser1Field.getText();
                if ((!depositFieldString.equals("")) && (Integer.parseInt(depositFieldString) > 0)) {
                    System.out.println("Account user1=$"+controller.accountUser1);

                    //Add to account field
                    controller.accountUser1 += Integer.parseInt(controller.depositUser1Field.getText());
                    //Cash In Bank User1
                    controller.cashUser1.setText("$" + String.valueOf(controller.accountUser1));


                    System.out.println(Thread.currentThread().getName()+" added cash to user1's account");


                } else if (Objects.equals(depositFieldString, "0")) {
                    Platform.runLater(
                            () -> AlertBox.display("Fail", "You cannot deposit $0!"));


                } else {
                    Platform.runLater(
                            () -> AlertBox.display("Fail", "You must deposit positive number!"));
                }
            } else {
                String depositFieldString = controller.depositUser2Field.getText();
                if ((!depositFieldString.equals("")) && (Integer.parseInt(depositFieldString) > 0)) {
                    System.out.println("Account user2=$"+controller.accountUser2);

                    //Add to account field
                    controller.accountUser2 += Integer.parseInt(controller.depositUser2Field.getText());
                    //Cash In Bank User1
                    controller.cashUser2.setText("$" + String.valueOf(controller.accountUser2));



                    System.out.println(Thread.currentThread().getName()+" added cash to user2's account");



                } else if (Objects.equals(depositFieldString, "0")) {
                    Platform.runLater(
                            () -> AlertBox.display("Fail", "You cannot deposit $0!"));

                } else {
                    Platform.runLater(
                            () -> AlertBox.display("Fail", "You must deposit positive number!"));
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
                String depositFieldString = controller.depositUser1Field.getText();
                String log = controller.logUser1.getText();

                if ((!depositFieldString.equals("")) && (Integer.parseInt(depositFieldString) > 0)) {
                    //Log User1
                    log += "\n+Deposited $" + controller.depositUser1Field.getText();
                    controller.logUser1.setText(log);


                    System.out.println(Thread.currentThread().getName()+" added info to user1's log");
                    System.out.println("Account user1=$"+controller.accountUser1);

                }

            } else {
                String depositFieldString = controller.depositUser2Field.getText();
                String log = controller.logUser2.getText();
                if ((!depositFieldString.equals("")) && (Integer.parseInt(depositFieldString) > 0)) {
                    //Log User1
                    log += "\n+Deposited $" + controller.depositUser2Field.getText();
                    controller.logUser2.setText(log);


                    System.out.println(Thread.currentThread().getName()+" added info to user2's log");
                    System.out.println("Account user2=$"+controller.accountUser2);

                }
            }
            turn = 1;
            flag2 = false;

        };



}


