package sample;

import com.mongodb.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javax.swing.*;

import static sample.Calculation.*;

public class Main extends Application {

    //connect to mongo localhost
    MongoClient mongoC = new MongoClient("localhost",27017);

    //Creating collection called eCal
    DB localhost = mongoC.getDB("@localhost");

    //creating Mongodb collections for store data
    DBCollection finance = localhost.getCollection("finance1_record");
    DBCollection finance2Users = localhost.getCollection("finance2_records");
    DBCollection loanUsers = localhost.getCollection("loan_records");
    DBCollection morgageUsers = localhost.getCollection("mortgage_records");

    //basic Object creating




    //set null variable for store last record
    DBObject lastRecord = null;
    DBObject lastRecord2 = null;
    DBObject lastRecord3 = null;
    DBObject lastRecord4 = null;

    //Text field array for key-board
    TextField[] textFieldsArray = new TextField[1];


    Stage window;
    Scene scene1, scene2, scene3, scene4, scene5;

    //navigation bar for all scenes
    public HBox navigationBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,15,15,15));
        hbox.setSpacing(15);
        hbox.setStyle("-fx-background-color: cadetblue");


        Label lb1 = new Label("Calculator");
        lb1.setPadding(new Insets(0,10,0,0));
        lb1.setFont(Font.font("",FontWeight.BOLD,40));
        lb1.setLayoutX(300);
        lb1.setLayoutY(100);


        Button bt1 = new Button("Finance");;
        bt1.setPadding(new Insets(10));

        Button bt5 = new Button("Finance 2");
        bt5.setPadding(new Insets(10));

        Button bt2 = new Button("Loan");
        bt2.setPadding(new Insets(10));

        Button bt3 = new Button("Mortgage");
        bt3.setPadding(new Insets(10));

        Button bt4 = new Button("Help");
        bt4.setPadding(new Insets(10));
        bt4.setAlignment(Pos.CENTER_RIGHT);


        
        //switvhing between scenes when button clicked
        bt1.setOnAction(e -> window.setScene(scene1));
        bt5.setOnAction(e -> window.setScene(scene5));
        bt2.setOnAction(e -> window.setScene(scene2));
        bt3.setOnAction(e -> window.setScene(scene3));
        bt4.setOnAction(e -> window.setScene(scene4));

        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setMargin(bt1, new Insets(0,0,0,50));
        hbox.setMargin(bt4,new Insets(0,0,0,50));
        hbox.getChildren().addAll(lb1,bt1,bt5,bt2,bt3,bt4);
        return hbox;

    }



    //Created grid pane at left to select finance
    public GridPane finance() {
        GridPane finance1 = new GridPane();
        finance1.setHgap(10);
        finance1.setVgap(10);
        finance1.setPadding(new Insets(0,10,0,10));

        Text topic_1 = new Text("Financial Calculator");
        topic_1.setStyle("-fx-font-size: 30");
        finance1.add(topic_1,0,4,2,1);

        Label lb1 = new Label("Future Value");
        lb1.setPadding(new Insets(0,0,0,10));
        finance1.add(lb1,0,8);
        TextField tf1 = new TextField();
        tf1.setOnMouseClicked(e ->{textFieldsArray[0]=tf1;});
        finance1.add(tf1,1,8);

        Label lb2 = new Label("Start Principle");
        lb2.setPadding(new Insets(0,0,0,10));
        finance1.add(lb2,0,9);
        TextField tf2 = new TextField();
        tf2.setOnMouseClicked(e ->{textFieldsArray[0]=tf2;});
        finance1.add(tf2,1,9);

        Label lb3 = new Label("Interest Rate ");
        lb3.setPadding(new Insets(0,0,0,10));
        finance1.add(lb3,0,10);
        TextField tf3 = new TextField();
        tf3.setOnMouseClicked(e ->{textFieldsArray[0]=tf3;});
        finance1.add(tf3,1,10);

        Label lb4 = new Label("Number of Periods");
        lb4.setPadding(new Insets(0,0,0,10));
        finance1.add(lb4,0,11);
        TextField tf4 = new TextField();
        tf4.setOnMouseClicked(e ->{textFieldsArray[0]=tf4;});
        finance1.add(tf4,1,11);

        Button cal = new Button("Calculate ");
        finance1.add(cal,1,14,2,1);

        Button clear = new Button("Clear");
        finance1.add(clear,2,14);
        finance1.setMargin(clear,new Insets(0,60,0,0));

        clear.setOnAction((ActionEvent )-> {
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
        });

        DBCursor cursor2 = finance.find();
        while (cursor2.hasNext()) {
            lastRecord = cursor2.next();
        }
        

        //seton action to calculate
        cal.setOnAction((ActionEvent e)->{

            Text txtHeading = new Text("Result :");
            finance1.add(txtHeading,13,8);

            Label fV = new Label("Future Value: ");
            finance1.add(fV,13,9);
            Text result = new Text("");
            result.setFont(Font.font("", FontWeight.BOLD,20));
            result.setFill(Color.GREEN);
            finance1.add(result,14,9);

            Label sT = new Label("Starting Principle: ");
            finance1.add(sT,13,10);
            Text sTValue = new Text("");
            sTValue.setFont(Font.font("", FontWeight.BOLD,20));
            sTValue.setFill(Color.GREEN);
            finance1.add(sTValue,14,10);

            Label iR = new Label("Interest Rate: ");
            finance1.add(iR,13,11);
            Text irValue = new Text("");
            irValue.setFont(Font.font("", FontWeight.BOLD,20));
            irValue.setFill(Color.GREEN);
            finance1.add(irValue,14,11);

            Label nP = new Label("Number of Periods: ");
            finance1.add(nP,13,12);
            Text npValue = new Text("");
            npValue.setFont(Font.font("", FontWeight.BOLD,20));
            npValue.setFill(Color.GREEN);
            finance1.add(npValue,14,12);

            clear.setOnAction((ActionEvent )-> {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                result.setText("");
                txtHeading.setText("");
                fV.setText("");
                sTValue.setText("");
                sT.setText("");
                npValue.setText("");
                nP.setText("");
                iR.setText("");
                irValue.setText("");
            });


            //get future value
            if (tf1.getText().isEmpty()) {
                BasicDBObjectBuilder financeRecords = new BasicDBObjectBuilder().start();
                double num2 = Double.parseDouble(tf2.getText());
                double num3 = Double.parseDouble(tf3.getText())/100;
                double num4 = Double.parseDouble(tf4.getText());

                result.setText(finance_fv_calculation(num2,num3,num4)+"");
                tf1.setText(finance_fv_calculation(num2,num3,num4)+"");
                sTValue.setText(tf2.getText());
                irValue.setText(tf3.getText());
                npValue.setText(tf4.getText());

                //passing value to database
                financeRecords.append("startP",tf2.getText());

                financeRecords.append("interest",tf3.getText());

                financeRecords.append("period",tf4.getText());

                WriteResult res = finance.insert(financeRecords.get());
            }
            else if (tf2.getText().isEmpty()) {
                BasicDBObjectBuilder financeRecords = new BasicDBObjectBuilder().start();
                double num1 = Double.parseDouble(tf1.getText());
                double num3 = Double.parseDouble(tf3.getText())/100;
                double num4 = Double.parseDouble(tf4.getText());

                sTValue.setText(finance_sp_calculation(num1,num3,num4)+"");
                tf2.setText(finance_sp_calculation(num1,num3,num4)+"");
                result.setText(tf1.getText());
                irValue.setText(tf3.getText());
                npValue.setText(tf4.getText());

                financeRecords.append("futureValue",tf1.getText());

                financeRecords.append("interest",tf3.getText());

                financeRecords.append("period",tf4.getText());

                WriteResult res = finance.insert(financeRecords.get());
            }
            else if (tf3.getText().isEmpty()) {
                BasicDBObjectBuilder financeRecords = new BasicDBObjectBuilder().start();
                double num1 = Double.parseDouble(tf1.getText());
                double num2 = Double.parseDouble(tf2.getText());
                double num4 = Double.parseDouble(tf4.getText());

                irValue.setText(interest_rate_cal(num1,num2,num4)+"");
                tf3.setText(interest_rate_cal(num1,num2,num4)+"");
                result.setText(tf1.getText());
                sTValue.setText(tf2.getText());
                npValue.setText(tf4.getText());

                financeRecords.append("futureValue",tf1.getText());

                financeRecords.append("startP",tf2.getText());

                financeRecords.append("period",tf4.getText());

                WriteResult res = finance.insert(financeRecords.get());

            }
            else if (tf4.getText().isEmpty()) {
                BasicDBObjectBuilder financeRecords = new BasicDBObjectBuilder().start();
                double num1 = Double.parseDouble(tf1.getText());
                double num2 = Double.parseDouble(tf2.getText());
                double num3 = Double.parseDouble(tf3.getText())/100;

                npValue.setText(number_Of_Payments(num1,num2,num3)+"");
                tf4.setText(number_Of_Payments(num1,num2,num3)+"");
                result.setText(tf1.getText());
                sTValue.setText(tf2.getText());
                irValue.setText(tf3.getText());

                financeRecords.append("futureValue",tf1.getText());

                financeRecords.append("startP",tf2.getText());

                financeRecords.append("interest",tf3.getText());

                WriteResult res = finance.insert(financeRecords.get());
            }



        });

        try{
            if (tf1.getText().isEmpty()) {
                tf2.setText(lastRecord.get("startP").toString());
                tf3.setText(lastRecord.get("interest").toString());
                tf4.setText(lastRecord.get("period").toString());
            }
        } catch (NullPointerException e) {

        }
        try {
            if (tf2.getText().isEmpty()) {
                tf1.setText(lastRecord.get("futureValue").toString());
                tf3.setText(lastRecord.get("interest").toString());
                tf4.setText(lastRecord.get("period").toString());
            }
        }catch (NullPointerException e2) {

        }
        try {
            if (tf3.getText().isEmpty()) {
                tf1.setText(lastRecord.get("futureValue").toString());
                tf2.setText(lastRecord.get("startP").toString());
                tf4.setText(lastRecord.get("period").toString());
            }
        }catch (NullPointerException e3) {

        }
        try {
            if(tf4.getText().isEmpty()) {
                tf1.setText(lastRecord.get("futureValue").toString());
                tf2.setText(lastRecord.get("startP").toString());
                tf3.setText(lastRecord.get("interest").toString());
            }
        }catch (NullPointerException e4){

        }



        return finance1;
    }

    public GridPane finance2() {
        GridPane finance2 = new GridPane();
        finance2.setHgap(10);
        finance2.setVgap(10);
        finance2.setPadding(new Insets(0,10,0,10));

        Text topic_2 = new Text("Financial Calculator 2");
        topic_2.setStyle("-fx-font-size: 30");
        finance2.add(topic_2,0,4,2,1);

        Label lb1 = new Label("Future Value");
        lb1.setPadding(new Insets(0,0,0,10));
        finance2.add(lb1,0,8);
        TextField tf1 = new TextField();
        tf1.setOnMouseClicked(e ->{textFieldsArray[0]=tf1;});
        finance2.add(tf1,1,8);

        Label lb2 = new Label("Start Principle");
        lb2.setPadding(new Insets(0,0,0,10));
        finance2.add(lb2,0,9);
        TextField tf2 = new TextField();
        tf2.setOnMouseClicked(e ->{textFieldsArray[0]=tf2;});
        finance2.add(tf2,1,9);

        Label lb3 = new Label("Interest Rate ");
        lb3.setPadding(new Insets(0,0,0,10));
        finance2.add(lb3,0,10);
        TextField tf3 = new TextField();
        tf3.setOnMouseClicked(e ->{textFieldsArray[0]=tf3;});
        finance2.add(tf3,1,10);

        Label lb4 = new Label("Number of Periods");
        lb4.setPadding(new Insets(0,0,0,10));
        finance2.add(lb4,0,11);
        TextField tf4 = new TextField();
        tf4.setOnMouseClicked(e ->{textFieldsArray[0]=tf4;});
        finance2.add(tf4,1,11);

        Label lb5 = new Label("PMT (Annuity Payment)");
        lb5.setPadding(new Insets(0,0,0,10));
        finance2.add(lb5,0,12);
        TextField tf5 = new TextField();
        tf5.setOnMouseClicked(e ->{textFieldsArray[0]=tf5;});
        finance2.add(tf5,1,12);

        Label lb6 = new Label("PMT made at the");
        lb6.setPadding(new Insets(0,0,0,10));
        finance2.add(lb6,0,13);
        final ToggleGroup rb = new ToggleGroup();
        RadioButton beginning = new RadioButton("Beginning");
        beginning.setToggleGroup(rb);
        finance2.add(beginning,1,13,2,1);
        RadioButton end = new RadioButton("End");
        end.setToggleGroup(rb);
        end.setSelected(true);
        finance2.add(end,2,13);

        Button cal = new Button("Calculate ");
        finance2.add(cal,1,15,2,2);

        Button clear = new Button("Clear");
        finance2.add(clear,2,15);

        clear.setOnAction((ActionEvent )-> {
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
            tf5.setText("");
                });

        DBCursor cursor = finance2Users.find();
        while (cursor.hasNext()) {
            lastRecord2 = cursor.next();
        }


        //calculate the finance2
        cal.setOnAction((ActionEvent e)->{

            Text txtHeading = new Text("Result :");
            finance2.add(txtHeading,13,8);

            Label fV = new Label("Future Value: ");
            finance2.add(fV,13,9);
            Text fVValue = new Text("");
            fVValue.setFont(Font.font("", FontWeight.BOLD,20));
            fVValue.setFill(Color.GREEN);
            finance2.add(fVValue,14,9);

            Label pV = new Label("Present Value: ");
            finance2.add(pV,13,10);
            Text pvValue = new Text("");
            pvValue.setFont(Font.font("", FontWeight.BOLD,20));
            pvValue.setFill(Color.GREEN);
            finance2.add(pvValue,14,10);

            Label totP = new Label("Total Principle: ");
            finance2.add(totP,13,11);
            Text totPValue = new Text("");
            totPValue.setFont(Font.font("", FontWeight.BOLD,20));
            totPValue.setFill(Color.GREEN);
            finance2.add(totPValue,14,11);

            Label totIr = new Label("Total Interest: ");
            finance2.add(totIr,13,12);
            Text totIrValue = new Text("");
            totIrValue.setFont(Font.font("", FontWeight.BOLD,20));
            totIrValue.setFill(Color.GREEN);
            finance2.add(totIrValue,14,12);

            clear.setOnAction((ActionEvent )-> {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                txtHeading.setText("");
                fV.setText("");
                fVValue.setText("");
                pV.setText("");
                pvValue.setText("");
                totP.setText("");
                totPValue.setText("");
                totIr.setText("");
                totIrValue.setText("");
            });


            //to calculate future value
            if (tf1.getText().isEmpty()) {
                double num2 = Double.parseDouble(tf2.getText());
                double num3 = Double.parseDouble(tf3.getText())/100;
                double num4 = Double.parseDouble(tf4.getText());
                double num5 = Double.parseDouble(tf5.getText());

                if (end.isSelected()) {
                    BasicDBObjectBuilder finance2Records = new BasicDBObjectBuilder().start();
                    fVValue.setText(future_val_end(num2,num3,num4,num5)+"");
                    pvValue.setText(present_val_cal_end(num2,num3,num4,num4)+"");

                    double totAp = num5*num4;


                    totPValue.setText(num2+totAp+"");
                    double totI = future_val_end(num2,num3,num4,num5)-(num2+totAp);
                    totIrValue.setText(Math.round((totI)*100.0) / 100.0+"") ;

                    finance2Records.append("startPrin",tf2.getText());

                    finance2Records.append("interestRate",tf3.getText());

                    finance2Records.append("numberOfPeriods",tf4.getText());

                    finance2Records.append("annuityPayment",tf5.getText());

                    WriteResult res = finance2Users.insert(finance2Records.get());

                }
                else if (beginning.isSelected()) {
                    BasicDBObjectBuilder finance2Records = new BasicDBObjectBuilder().start();
                    fVValue.setText(future_value_cal_beg(num2,num3,num4,num5)+"");
                    pvValue.setText(tf2.getText());
                    totPValue.setText(tf3.getText());
                    totIrValue.setText(tf4.getText());

                    finance2Records.append("startPrin",tf2.getText());

                    finance2Records.append("interestRate",tf3.getText());

                    finance2Records.append("numberOfPeriods",tf4.getText());

                    finance2Records.append("annuityPayment",tf5.getText());

                    WriteResult res = finance2Users.insert(finance2Records.get());
                }
            }
            else if (tf2.getText().isEmpty()) {
                double num1 = Double.parseDouble(tf1.getText());
                double num3 = Double.parseDouble(tf3.getText())/100;
                double num4 = Double.parseDouble(tf4.getText());
                double num5 = Double.parseDouble(tf5.getText());

                if (end.isSelected()) {
                    BasicDBObjectBuilder finance2Records = new BasicDBObjectBuilder().start();

                    pvValue.setText(present_val_cal_end(num1,num3,num4,num5)+"");
                    fVValue.setText(tf1.getText());
                    totIrValue.setText(tf4.getText());
                    totPValue.setText(tf3.getText());

                    finance2Records.append("futureVal",tf1.getText());

                    finance2Records.append("interestRate",tf3.getText());

                    finance2Records.append("numberOfPeriods",tf4.getText());

                    finance2Records.append("annuityPayment",tf5.getText());

                    WriteResult res = finance2Users.insert(finance2Records.get());
                }
                else if (beginning.isSelected()) {
                    BasicDBObjectBuilder finance2Records = new BasicDBObjectBuilder().start();
                    pvValue.setText(present_value_cal_beg(num1,num3,num4,num5)+"");

                    finance2Records.append("futureVal",tf1.getText());

                    finance2Records.append("interestRate",tf3.getText());

                    finance2Records.append("numberOfPeriods",tf4.getText());

                    finance2Records.append("annuityPayment",tf5.getText());

                    WriteResult res = finance2Users.insert(finance2Records.get());
                }
            }
            else if (tf3.getText().isEmpty()) {
                double num1 = Double.parseDouble(tf1.getText());
                double num2 = Double.parseDouble(tf2.getText());
                double num4 = Double.parseDouble(tf4.getText());
                double num5 = Double.parseDouble(tf5.getText());

                if (end.isSelected()) {
                }
            }
        });
        try{
            if (tf1.getText().isEmpty()) {
                tf2.setText(lastRecord2.get("startPrin").toString());
                tf3.setText(lastRecord2.get("interestRate").toString());
                tf4.setText(lastRecord2.get("numberOfPeriods").toString());
                tf5.setText(lastRecord2.get("annuityPayment").toString());
            }
        } catch (NullPointerException e) {
        }


        return finance2;
    }


    public GridPane loan() {
        GridPane loan = new GridPane();
        loan.setHgap(10);
        loan.setVgap(10);
        loan.setPadding(new Insets(0,10,0,10));

        Text topic_3 = new Text("Auto Loan Calculator");
        topic_3.setStyle("-fx-font-size: 30");
        loan.add(topic_3,0,4,2,1);

        Label autoPriceDes = new Label("Auto Price");
        autoPriceDes.setPadding(new Insets(0,0,0,10));
        loan.add(autoPriceDes,0,8);
        TextField autoPriceValue = new TextField();
        autoPriceValue.setOnMouseClicked(e ->{textFieldsArray[0]=autoPriceValue;});
        loan.add(autoPriceValue,1,8);

        Label monthlyPayment = new Label("Monthly Payment: ");
        monthlyPayment.setPadding(new Insets(0,0,0,10));
        loan.add(monthlyPayment,0,9);
        TextField monthlyPaymentValue = new TextField();
        monthlyPaymentValue.setOnMouseClicked(e ->{textFieldsArray[0]=autoPriceValue;});
        loan.add(monthlyPaymentValue,1,9);

        Label loanTermDes = new Label("Loan Term");
        loanTermDes.setPadding(new Insets(0,0,0,10));
        loan.add(loanTermDes,0,10);
        TextField loanTermValue = new TextField();
        loanTermValue.setOnMouseClicked(e ->{textFieldsArray[0]=loanTermValue;});
        loan.add(loanTermValue,1,10);

        Label interestRateDes = new Label("Interest Rate");
        interestRateDes.setPadding(new Insets(0,0,0,10));
        loan.add(interestRateDes,0,11);
        TextField interestRateValue = new TextField();
        interestRateValue.setOnMouseClicked(e ->{textFieldsArray[0]=interestRateValue;});
        loan.add(interestRateValue,1,11);

        Label downPaymentDes = new Label("Down Payment");
        downPaymentDes.setPadding(new Insets(0,0,0,10));
        loan.add(downPaymentDes,0,12);
        TextField downPaymentValue = new TextField();
        downPaymentValue.setOnMouseClicked(e ->{textFieldsArray[0]=downPaymentValue;});
        loan.add(downPaymentValue,1,12);

        Label salesTaxDes = new Label("Sales Tax");
        salesTaxDes.setPadding(new Insets(0,0,0,10));
        loan.add(salesTaxDes,0,13);
        TextField salesTaxValue = new TextField();
        salesTaxValue.setOnMouseClicked(e ->{textFieldsArray[0]=salesTaxValue;});
        loan.add(salesTaxValue,1,13);

        Label otherFeeDes = new Label("Other Fess(Register fee and etc)");
        otherFeeDes.setPadding(new Insets(0,0,0,10));
        loan.add(otherFeeDes,0,14);
        TextField otherFeeValue = new TextField();
        otherFeeValue.setOnMouseClicked(e ->{textFieldsArray[0]=otherFeeValue;});
        loan.add(otherFeeValue,1,14);



        DBCursor cursor3 = loanUsers.find();
        while (cursor3.hasNext()) {
            lastRecord3 = cursor3.next();
        }
        

        Button cal = new Button("Calculate");
        loan.add(cal,1,16);

        Button clear = new Button("Clear");
        loan.add(clear,2,16);

        clear.setOnAction((ActionEvent )-> {
            autoPriceValue.setText("");
            monthlyPaymentValue.setText("");
            loanTermValue.setText("");
            interestRateValue.setText("");
            downPaymentValue.setText("");
            salesTaxValue.setText("");
            otherFeeValue.setText("");
        });
        //seton Action for calculation
        cal.setOnAction((ActionEvent e)-> {

            Text res = new Text("Result :");
            loan.add(res,13,8);

            Label propPrice = new Label("Auto Price: ");
            loan.add(propPrice,13,9);
            Text propPriceValue = new Text();
            loan.add(propPriceValue,14,9);
            propPriceValue.setFont(Font.font("", FontWeight.BOLD,20));
            propPriceValue.setFill(Color.GREEN);

            Label loanMP = new Label("Monthly Pay: ");
            loan.add(loanMP,13,10);
            Text mpValue = new Text();
            loan.add(mpValue,14,10);
            mpValue.setFont(Font.font("", FontWeight.BOLD,20));
            mpValue.setFill(Color.GREEN);

            Label totLoan = new Label("Total Loan: ");
            loan.add(totLoan,13,11);
            Text totLoanValue = new Text();
            loan.add(totLoanValue,14,11);
            totLoanValue.setFont(Font.font("", FontWeight.BOLD,20));
            totLoanValue.setFill(Color.GREEN);

            Label totPayble = new Label("Total Payments: ");
            loan.add(totPayble,13,12);
            Text totPaybleValue = new Text();
            loan.add(totPaybleValue,14,12);
            totPaybleValue.setFont(Font.font("", FontWeight.BOLD,20));
            totPaybleValue.setFill(Color.GREEN);

            Label totIr = new Label("Total Interest: ");
            loan.add(totIr,13,13);
            Text totIrValue = new Text();
            loan.add(totIrValue,14,13);
            totIrValue.setFont(Font.font("", FontWeight.BOLD,20));
            totIrValue.setFill(Color.GREEN);

            Label ipFrontPay = new Label("Upfront Payment: ");
            loan.add(ipFrontPay,13,14);
            Text upFrontPayValue = new Text();
            loan.add(upFrontPayValue,14,14);
            upFrontPayValue.setFont(Font.font("", FontWeight.BOLD,20));
            upFrontPayValue.setFill(Color.GREEN);

            Label salesTaxd = new Label("Sales Tax: ");
            loan.add(salesTaxd,13,15);
            Text salesTaxValuee = new Text();
            loan.add(salesTaxValuee,14,15);
            salesTaxValuee.setFont(Font.font("", FontWeight.BOLD,20));
            salesTaxValuee.setFill(Color.GREEN);


            if (monthlyPaymentValue.getText().isEmpty()) {
                BasicDBObjectBuilder loanRecords = new BasicDBObjectBuilder().start();
                double num1 = Double.parseDouble(autoPriceValue.getText());
                double num2 = Double.parseDouble(loanTermValue.getText());
                double num3 = Double.parseDouble(interestRateValue.getText())/100;
                double num4 = Double.parseDouble(downPaymentValue.getText());
                double num5 = Double.parseDouble(salesTaxValue.getText());
                double num6 = Double.parseDouble(otherFeeValue.getText());

                autoPriceValue.setText(num1+"");
                propPriceValue.setText(num1+"");
                monthlyPaymentValue.setText(monthly_pay_calculation(num1,num2,num3,num4)+"");
                totIrValue.setText(totIrCal(num1,num2,num3,num4)+"");
                totPaybleValue.setText(totPayble(num1,num2,num3,num4)+"");
                mpValue.setText(monthly_pay_calculation(num1,num2,num3,num4)+"");

                double upF = num1*(num5/100)+num4+num6;
                upFrontPayValue.setText(upF+"");

                loanRecords.append("autoPrice",autoPriceValue.getText());

                loanRecords.append("loanTerm",loanTermValue.getText());

                loanRecords.append("interestRate",interestRateValue.getText());

                loanRecords.append("downPayment",downPaymentValue.getText());

                loanRecords.append("salesTax",salesTaxValue.getText());

                loanRecords.append("otherFee",otherFeeValue.getText());

                WriteResult res3 = loanUsers.insert(loanRecords.get());


            }else if (autoPriceValue.getText().isEmpty()) {
                BasicDBObjectBuilder loanRecords = new BasicDBObjectBuilder().start();
                    double num2 = Double.parseDouble(loanTermValue.getText());
                    double num3 = Double.parseDouble(interestRateValue.getText())/100;
                    double num4 = Double.parseDouble(downPaymentValue.getText());
                    double num5 = Double.parseDouble(salesTaxValue.getText());
                    double num6 = Double.parseDouble(otherFeeValue.getText());
                    double num7 = Double.parseDouble(monthlyPaymentValue.getText());

                    autoPriceValue.setText(autoPrice(num7,num2,num3)+"");
                    propPriceValue.setText(autoPrice(num7,num2,num3)+"");
                    mpValue.setText(num7+"");
                    totLoanValue.setText(autoPrice(num7,num2,num3)+"");
                    salesTaxValuee.setText(fullLoan(num7,num2,num3,num4)+"");
                    upFrontPayValue.setText(fullLoan(num7,num2,num3,num4)*(num5/100)+num4+num6+"");

                loanRecords.append("loanTerm",loanTermValue.getText());

                loanRecords.append("interestRate",interestRateValue.getText());

                loanRecords.append("downPayment",downPaymentValue.getText());

                loanRecords.append("salesTax",salesTaxValue.getText());

                loanRecords.append("otherFee",otherFeeValue.getText());

                loanRecords.append("monthlyP",monthlyPaymentValue.getText());

                WriteResult res3 = loanUsers.insert(loanRecords.get());
                }

            clear.setOnAction((ActionEvent )-> {
                autoPriceValue.setText("");
                loanTermValue.setText("");
                interestRateValue.setText("");
                downPaymentValue.setText("");
                salesTaxValue.setText("");
                otherFeeValue.setText("");
                mpValue.setText("");
                totLoanValue.setText("");
                salesTaxValue.setText("");
                salesTaxValuee.setText("");
                monthlyPaymentValue.setText("");
                upFrontPayValue.setText("");
                autoPriceValue.setText("");
                res.setText("");
                totIr.setText("");
                totLoan.setText("");
                totPayble.setText("");
                salesTaxd.setText("");
                upFrontPayValue.setText("");
                ipFrontPay.setText("");
                totIr.setText("");
                totIrValue.setText("");
                totPayble.setText("");
                totPaybleValue.setText("");
                loanMP.setText("");
                propPrice.setText("");
                propPriceValue.setText("");
            });
            });

        try {
            if (monthlyPaymentValue.getText().isEmpty()) {
                autoPriceValue.setText(lastRecord3.get("autoPrice").toString());
                loanTermValue.setText(lastRecord3.get("loanTerm").toString());
                interestRateValue.setText(lastRecord3.get("interestRate").toString());
                downPaymentValue.setText(lastRecord3.get("downPayment").toString());
                salesTaxValue.setText(lastRecord3.get("salesTax").toString());
                otherFeeValue.setText(lastRecord3.get("otherFee").toString());
            }
        }catch (NullPointerException e){

        }
        try {
            if (autoPriceValue.getText().isEmpty()) {
                loanTermValue.setText(lastRecord3.get("loanTerm").toString());
                interestRateValue.setText(lastRecord3.get("interestRate").toString());
                downPaymentValue.setText(lastRecord3.get("downPayment").toString());
                salesTaxValue.setText(lastRecord3.get("salesTax").toString());
                otherFeeValue.setText(lastRecord3.get("otherFee").toString());
                monthlyPaymentValue.setText(lastRecord3.get("monthlyP").toString());

            }
        }catch (NullPointerException e) {

        }



        return loan;
    }

    public GridPane mortgage() {
        GridPane mortgage = new GridPane();
        mortgage.setHgap(10);
        mortgage.setVgap(10);
        mortgage.setPadding(new Insets(0,10,0,10));

        Text topic = new Text("Mortgage Calculator");
        topic.setStyle("-fx-font-size: 30");
        mortgage.add(topic,0,4,2,1);

        Label homePrice = new Label("Home Price");
        homePrice.setPadding(new Insets(0,0,0,10));
        mortgage.add(homePrice,0,8);
        TextField homePriceInput = new TextField();
        homePriceInput.setOnMouseClicked(e ->{textFieldsArray[0]=homePriceInput;});
        mortgage.add(homePriceInput,1,8);

        Label downPayment = new Label("Down Payment");
        downPayment.setPadding(new Insets(0,0,0,10));
        mortgage.add(downPayment,0,9);
        TextField downPaymentInput = new TextField();
        downPaymentInput.setOnMouseClicked(e ->{textFieldsArray[0]=downPaymentInput;});
        mortgage.add(downPaymentInput,1,9);

        Label loanTerm = new Label("Loan Term");
        loanTerm.setPadding(new Insets(0,0,0,10));
        mortgage.add(loanTerm,0,10);
        TextField loanTermInput = new TextField();
        loanTermInput.setOnMouseClicked(e ->{textFieldsArray[0]=loanTermInput;});
        mortgage.add(loanTermInput,1,10);

        Label interestRate = new Label("Interest Rate");
        interestRate.setPadding(new Insets(0,0,0,10));
        mortgage.add(interestRate,0,11);
        TextField intrestRateInput = new TextField();
        interestRate.setOnMouseClicked(e ->{textFieldsArray[0]=intrestRateInput;});
        mortgage.add(intrestRateInput,1,11);

        Button cal = new Button("Calculate");
        mortgage.add(cal,1,14,2,1);

        Button clear = new Button("Clear");
        mortgage.add(clear,2,14);

        clear.setOnAction((ActionEvent )-> {
                    homePriceInput.setText("");
                    downPaymentInput.setText("");
                    loanTermInput.setText("");
                    intrestRateInput.setText("");
                });

        DBCursor cursor4 = morgageUsers.find();
        while (cursor4.hasNext()) {
            lastRecord4 = cursor4.next();
        }


        cal.setOnAction((ActionEvent e)-> {
            BasicDBObjectBuilder mortgageRecords = new BasicDBObjectBuilder().start();
            double num1 = Double.parseDouble(homePriceInput.getText());
            double num2 = Double.parseDouble(downPaymentInput.getText());
            double num3 = Double.parseDouble(loanTermInput.getText());
            double num4 = Double.parseDouble(intrestRateInput.getText())/100;

            Text res= new Text("Result :");
            mortgage.add(res,13,8);

            Label smtDescription = new Label("Starting mortgage value: ");
            mortgage.add(smtDescription,13,9);
            Text startingMortgageValue = new Text();
            mortgage.add(startingMortgageValue,14,9);
            startingMortgageValue.setFont(Font.font("", FontWeight.BOLD,20));
            startingMortgageValue.setFill(Color.GREEN);

            Label mpDescription = new Label("Monthly pay: ");
            mortgage.add(mpDescription,13,10);
            Text monthlyPayValue = new Text();
            mortgage.add(monthlyPayValue,14,10);
            monthlyPayValue.setFont(Font.font("", FontWeight.BOLD,20));
            monthlyPayValue.setFill(Color.GREEN);

            Label totIrDescription = new Label("Total Interest: ");
            mortgage.add(totIrDescription,13,11);
            Text totIrDecValue = new Text();
            mortgage.add(totIrDecValue,14,11);
            totIrDecValue.setFont(Font.font("", FontWeight.BOLD,20));
            totIrDecValue.setFill(Color.GREEN);

            Label mortgage360 = new Label("total Mortgage Payments ");
            mortgage.add(mortgage360,13,12);
            Text mortgage360Value = new Text();
            mortgage.add(mortgage360Value,14,12);
            mortgage360Value.setFont(Font.font("", FontWeight.BOLD,20));
            mortgage360Value.setFill(Color.GREEN);


            startingMortgageValue.setText(starting_mortgage_calculation(num1,num2)+"");
            monthlyPayValue.setText(monthly_pay_calculation(num1,num2,num4,num3)+"");
            totIrDecValue.setText(tot_interest(num1,num2,num4,num3) +"");
            mortgage360Value.setText(tot_mortgage_payments(num1,num2,num4,num3)+"");

            mortgageRecords.append("homeP",homePriceInput.getText());

            mortgageRecords.append("downP",downPaymentInput.getText());

            mortgageRecords.append("loanT",loanTermInput.getText());

            mortgageRecords.append("interestR",intrestRateInput.getText());

            WriteResult res4 = morgageUsers.insert(mortgageRecords.get());

            clear.setOnAction((ActionEvent )-> {
                startingMortgageValue.setText("");
                mpDescription.setText("");
                smtDescription.setText("");
                monthlyPayValue.setText("");
                homePriceInput.setText("");
                downPaymentInput.setText("");
                loanTermInput.setText("");
                intrestRateInput.setText("");
                homePriceInput.setText("");
                downPaymentInput.setText("");
                loanTermInput.setText("");
                intrestRateInput.setText("");
                totIrDescription.setText("");
                totIrDecValue.setText("");
                mortgage360.setText("");
                mortgage360Value.setText("");
                res.setText("");




            });
        });
        try {
            homePriceInput.setText(lastRecord4.get("homeP").toString());
            downPaymentInput.setText(lastRecord4.get("downP").toString());
            loanTermInput.setText(lastRecord4.get("loanT").toString());
            intrestRateInput.setText(lastRecord4.get("interestR").toString());
        }catch (NullPointerException e2) {

        }

        return mortgage;
    }


    public Pane help() {
        AnchorPane help = new AnchorPane();
        Label f1 = new Label("Finance 1");
        f1.setFont(Font.font("",FontWeight.BOLD,25));
        f1.setLayoutX(60);
        f1.setLayoutY(50);
        Text f1T = new Text("In This financial calculator you can calculate FV(Future Value), ST(Start Principle, IR(Interest Rate)\n and  NP(Number of Periods). You need to keep empty what you wish to get.");
        f1T.setFont(Font.font("",FontWeight.SEMI_BOLD,18));
        f1T.setLayoutX(60);
        f1T.setLayoutY(110);

        Label f2 = new Label("Finance 2");
        f2.setFont(Font.font("",FontWeight.BOLD,25));
        f2.setLayoutX(60);
        f2.setLayoutY(170);
        Text f2T = new Text("You can calculate Annuity Payment which is extra other than finance calculator 1 in this finance\n calculator. Also you can select the annuity payment is end or beginning. And you need to keep\n empty textbox wht you need to calculate. ");
        f2T.setFont(Font.font("",FontWeight.SEMI_BOLD,18));
        f2T.setLayoutX(60);
        f2T.setLayoutY(230);

        Label mortgage = new Label("Mortgage");
        mortgage.setFont(Font.font("",FontWeight.BOLD,25));
        mortgage.setLayoutX(60);
        mortgage.setLayoutY(300);
        Text mortgageT = new Text("You can calculate Monthly payment, Starting mortgage value, Total interest and Total mortgage\n payments.");
        mortgageT.setFont(Font.font("",FontWeight.SEMI_BOLD,18));
        mortgageT.setLayoutX(60);
        mortgageT.setLayoutY(360);

        Label loan = new Label("Loan");
        loan.setFont(Font.font("",FontWeight.BOLD,25));
        loan.setLayoutX(60);
        loan.setLayoutY(400);
        Text loanT = new Text("when you got an loan You can calculate payment of every month, Total interest, upfront payment\n and sales tax");
        loanT.setFont(Font.font("",FontWeight.SEMI_BOLD,18));
        loanT.setLayoutX(60);
        loanT.setLayoutY(460);

        help.getChildren().addAll(f1,f2,mortgage,loan,f1T,f2T,mortgageT,loanT);
        return help;
    }

    public GridPane keyBord() {
        GridPane key_bord = new GridPane();
        key_bord.setPrefSize(250,300);
        key_bord.setPadding(new Insets(30,0,30,80));
        key_bord.setHgap(2);
        key_bord.setVgap(2);

        Button one = new Button("1 ");
        one.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"1");});
        key_bord.add(one,1,1);

        Button two = new Button("2 ");
        two.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"2");});
        key_bord.add(two,2,1);

        Button three = new Button("3 ");
        three.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"3");});
        key_bord.add(three,3,1);

        Button four = new Button("4 ");
        four.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"4");});
        key_bord.add(four,1,2);

        Button five = new Button("5 ");
        five.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"5");});
        key_bord.add(five,2,2);

        Button six = new Button("6 ");
        six.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"6");});
        key_bord.add(six,3,2);

        Button seven = new Button("7 ");
        seven.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"7");});
        key_bord.add(seven,1,3);

        Button eight = new Button("8 ");
        eight.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"8");});
        key_bord.add(eight,2,3);

        Button nine = new Button("9 ");
        nine.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"9");});
        key_bord.add(nine,3,3);

        Button zero = new Button("0 ");
        zero.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+"0");});
        key_bord.add(zero,2,4);

        Button decimal = new Button(".");
        decimal.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText()+".");});
        key_bord.add(decimal,1,4);

        Button clear = new Button("CE");
        clear.setOnAction(event -> {textFieldsArray[0].setText(textFieldsArray[0].getText(0,textFieldsArray[0].getLength()-1));});
        key_bord.add(clear,3,4);



        one.setPrefSize(100,40);
        two.setPrefSize(100,40);
        three.setPrefSize(100,40);
        four.setPrefSize(100, 40);
        five.setPrefSize(100,40);
        six.setPrefSize(100,40);
        seven.setPrefSize(100,40);
        seven.setPrefSize(100,40);
        eight.setPrefSize(100,40);
        nine.setPrefSize(100,40);
        zero.setPrefSize(100,40);
        decimal.setPrefSize(100,40);
        clear.setPrefSize(100,40);

        return key_bord;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Calculator");
        BorderPane layout = new BorderPane();
        BorderPane layout2 = new BorderPane();
        BorderPane layout3 = new BorderPane();
        BorderPane layout4 = new BorderPane();
        BorderPane layout5 = new BorderPane();
        layout.setStyle("-fx-background-color: silver");
        layout2.setStyle("-fx-background-color: silver");
        layout3.setStyle("-fx-background-color: silver");
        layout4.setStyle("-fx-background-color: silver");
        layout5.setStyle("-fx-background-color: silver");
        layout5.setTop(navigationBar());
        layout5.setLeft(finance2());
        layout5.setBottom(keyBord());
        layout4.setLeft(help());
        layout4.setTop(navigationBar());
        layout3.setTop(navigationBar());
        layout3.setLeft(mortgage());
        layout3.setBottom(keyBord());
        layout2.setTop(navigationBar());
        layout2.setLeft(loan());
        layout2.setBottom(keyBord());
        layout.setTop(navigationBar());
        layout.setLeft(finance());
        layout.setBottom(keyBord());
        scene1 = new Scene(layout,850,750);
        scene2 = new Scene(layout2,850,750);
        scene3 = new Scene(layout3,850,750);
        scene4 = new Scene(layout4,850,750);
        scene5 = new Scene(layout5,850,750);

        window.setScene(scene1);
        window.show();

}


    public static void main(String[] args) {
        launch(args);
    }
}
