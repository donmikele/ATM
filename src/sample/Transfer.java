package sample;

import javafx.application.Platform;

import java.util.Objects;

public class Transfer {

    private Controller controller;
    private String user;
    private volatile boolean flag1 = false;
    private volatile boolean flag2 = false;
    private volatile int turn = 1;
    private boolean transferedUsr1 = false;
    private boolean transferedUsr2 = false;

    Transfer(Controller controller, String user){
        this.controller = controller;
        this.user = user;
        Thread t5 = new Thread(r1);
        Thread t6 = new Thread(r2);
        t5.setName("Thread 5");
        t6.setName("Thread 6");
        t5.start();
        t6.start();
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
            String transferFieldString = controller.transferUser1Field.getText();
            if ((!transferFieldString.equals("")) && (Integer.parseInt(transferFieldString) > 0)) {

                if((controller.accountUser1 >= 0) && (Integer.parseInt(transferFieldString) <= controller.accountUser1)) {
                    transferedUsr1 = true;
                    System.out.println("Account user1=$"+controller.accountUser1);
                    System.out.println("Account user2=$"+controller.accountUser2);

                    //Transfer from account1 to account2 field
                    controller.accountUser1 -= Integer.parseInt(controller.transferUser1Field.getText());
                    controller.accountUser2 += Integer.parseInt(controller.transferUser1Field.getText());

                    //Cash In Bank User1
                    controller.cashUser1.setText("$" + String.valueOf(controller.accountUser1));
                    controller.cashUser2.setText("$" + String.valueOf(controller.accountUser2));
                    System.out.println(Thread.currentThread().getName()+" transferred cash from user1's account to user2's account");
                }else{
                    Platform.runLater(
                            () -> AlertBox.display("Fail", "Amount to transfer \nis greater than amount of user1's \naccount or its $0 account"));
                    System.out.println("Amount to transfer is greater than amount of user1's account or its $0 account");
                }


            } else if (Objects.equals(transferFieldString, "0")) {
                Platform.runLater(
                        () -> AlertBox.display("Fail", "You cannot transfer $0!"));

            } else {
                Platform.runLater(
                        () -> AlertBox.display("Fail", "You must transfer positive number!"));
            }
        }else{
            String transferFieldString = controller.transferUser2Field.getText();
            if ((!transferFieldString.equals("")) && (Integer.parseInt(transferFieldString) > 0)) {

                if((controller.accountUser2 != 0) && (Integer.parseInt(transferFieldString) <= controller.accountUser2)) {
                    transferedUsr2 = true;
                    System.out.println("Account user2=$"+controller.accountUser2);
                    System.out.println("Account user1=$"+controller.accountUser1);

                    //Transfer from account2 to account1 field
                    controller.accountUser2 -= Integer.parseInt(controller.transferUser2Field.getText());
                    controller.accountUser1 += Integer.parseInt(controller.transferUser2Field.getText());

                    //Cash In Bank User2
                    controller.cashUser2.setText("$" + String.valueOf(controller.accountUser2));
                    controller.cashUser1.setText("$" + String.valueOf(controller.accountUser1));
                    System.out.println(Thread.currentThread().getName()+" transferred cash from user2's account to user1's account");
                }else{
                    Platform.runLater(
                            () -> AlertBox.display("Fail", "Amount to transfer \nis greater than amount of user2's \naccount or its $0 account"));
                    System.out.println("Amount to transfer is greater than amount of user2's account or its $0 account");
                }


            } else if (Objects.equals(transferFieldString, "0")) {
                Platform.runLater(
                        () -> AlertBox.display("Fail", "You cannot transfer $0!"));

            } else {
                Platform.runLater(
                        () -> AlertBox.display("Fail", "You must transfer positive number!"));
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
            String transferFieldString = controller.transferUser1Field.getText();
            String log = controller.logUser1.getText();
            String log2 = controller.logUser2.getText();

            if((controller.accountUser1 >= 0) && (Integer.parseInt(transferFieldString) <= controller.accountUser1)) {
                //Log User1
                log += "\n-Transfered $" + controller.transferUser1Field.getText();
                controller.logUser1.setText(log);
                //Log User2
                log2 += "\n+Transfered $" + controller.transferUser1Field.getText();
                controller.logUser2.setText(log2);


                System.out.println(Thread.currentThread().getName() + " added info to user1's log & user2's log");
                System.out.println("Account user1=$"+controller.accountUser1);
                System.out.println("Account user2=$"+controller.accountUser2);
                transferedUsr1 = false;
            }
            else if(transferedUsr1){
                //Log User1
                log += "\n-Transfered $" + controller.transferUser1Field.getText();
                controller.logUser1.setText(log);
                //Log User2
                log2 += "\n+Transfered $" + controller.transferUser1Field.getText();
                controller.logUser2.setText(log2);


                System.out.println(Thread.currentThread().getName() + " added info to user1's log & user2's log");
                System.out.println("Account user1=$"+controller.accountUser1);
                System.out.println("Account user2=$"+controller.accountUser2);
                transferedUsr1 = false;
            }

        }else{
            String transferFieldString = controller.transferUser2Field.getText();
            String log = controller.logUser2.getText();
            String log2 = controller.logUser1.getText();

            if((controller.accountUser2 >= 0) && (Integer.parseInt(transferFieldString) <= controller.accountUser2)) {
                //Log User2
                log += "\n-Transfered $" + controller.transferUser2Field.getText();
                controller.logUser2.setText(log);
                //Log User1
                log2 += "\n+Transfered $" + controller.transferUser2Field.getText();
                controller.logUser1.setText(log2);


                System.out.println(Thread.currentThread().getName() + " added info to user2's log & user1's log");
                System.out.println("Account user2=$"+controller.accountUser2);
                System.out.println("Account user1=$"+controller.accountUser1);
                transferedUsr2 = false;
            }
            else if(transferedUsr2){
                //Log User2
                log += "\n-Transfered $" + controller.transferUser2Field.getText();
                controller.logUser2.setText(log);
                //Log User1
                log2 += "\n+Transfered $" + controller.transferUser2Field.getText();
                controller.logUser1.setText(log2);


                System.out.println(Thread.currentThread().getName() + " added info to user2's log & user1's log");
                System.out.println("Account user2=$"+controller.accountUser2);
                System.out.println("Account user1=$"+controller.accountUser1);
                transferedUsr2 = false;
            }
        }
        turn = 1;
        flag2 = false;

    };

}
