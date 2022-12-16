package com.calcu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class Calculator extends JFrame{
    double num1 = 0,num2 = 0;
    char operator = 'N';
    JButton button1,button2,button3,button4,button5,button6,button7,button8,button9;
    JButton button0, buttonDecimal,buttonSign,buttonSquared,buttonPercentage;
    JButton btnPlus,btnMinus,btnTimes,btnDivide,btnEqual,btnClear;
    JTextField txtNum;
    JPanel panelNorth,panelCenter,panelEast,panelSouth;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    Font txtFont = new Font("Courier New",Font.BOLD,25);

    public Calculator(){
        setTitle("Calculator");
        setSize(375,530);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        createUIComponents();
    }

    private void createUIComponents(){
        panelNorth = new JPanel();
        panelNorth.setPreferredSize(new Dimension(450,50));
        panelNorth.setBackground(new Color(222, 222, 222));
        panelNorth.setLayout(new FlowLayout());
        this.add(panelNorth,BorderLayout.NORTH);

        txtNum = new JTextField();
        txtNum.setPreferredSize(new Dimension(350,40));
        txtNum.setBorder(new LineBorder(Color.BLACK,2));
        txtNum.setHorizontalAlignment(SwingConstants.RIGHT);
        txtNum.setFont(new Font("Courier New",Font.BOLD,25));
        txtNum.setText("0");
        txtNum.setEditable(false);
        panelNorth.add(txtNum);

        panelCenter = new JPanel();
        panelCenter.setPreferredSize(new Dimension(350,400));
        panelCenter.setLayout(new FlowLayout());
        panelCenter.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(panelCenter,BorderLayout.CENTER);

        buttonPercentage = new JButton();
        buttonPercentage.setPreferredSize(new Dimension(80,80));
        buttonPercentage.setText("%");
        buttonPercentage.setFont(txtFont);
        buttonPercentage.setFocusable(false);
        panelCenter.add(buttonPercentage);

        buttonSquared = new JButton();
        buttonSquared.setPreferredSize(new Dimension(80,80));
        buttonSquared.setText("x2");
        buttonSquared.setFont(txtFont);
        buttonSquared.setFocusable(false);
        buttonSquared.addActionListener(e->{
            if(!String.valueOf(operator).equals("N")){
                txtNum.setText("Error");
                operator = 'N';
            }
            else{
                txtNum.setText(String.valueOf(decimalFormat.format(Math.pow(Double.parseDouble(txtNum.getText()),2))));
            }
        });
        panelCenter.add(buttonSquared);


        btnClear = new JButton();
        btnClear.setPreferredSize(new Dimension(80,80));
        btnClear.setText("C");
        btnClear.setFont(txtFont);
        btnClear.setFocusable(false);
        btnClear.addActionListener(e-> txtNum.setText("0"));
        panelCenter.add(btnClear);

        buttonClick(button9,"9");
        buttonClick(button8,"8");
        buttonClick(button7,"7");
        buttonClick(button6,"6");
        buttonClick(button5,"5");
        buttonClick(button4,"4");
        buttonClick(button3,"3");
        buttonClick(button2,"2");
        buttonClick(button1,"1");

       
        panelEast = new JPanel();
        panelEast.setPreferredSize(new Dimension(100,400));
        panelEast.setLayout(new FlowLayout());
        this.add(panelEast,BorderLayout.EAST);

        btnDivide = new JButton();
        btnDivide.setPreferredSize(new Dimension(80,80));
        btnDivide.setText("/");
        btnDivide.setFont(txtFont);
        btnDivide.setFocusable(false);
        btnDivide.addActionListener(e->checkOperator(btnDivide));
        panelEast.add(btnDivide);

        btnTimes = new JButton();
        btnTimes.setPreferredSize(new Dimension(80,80));
        btnTimes.setText("*");
        btnTimes.setFont(txtFont);
        btnTimes.setFocusable(false);
        btnTimes.addActionListener(e->checkOperator(btnTimes));
        panelEast.add(btnTimes);

        btnMinus = new JButton();
        btnMinus.setPreferredSize(new Dimension(80,80));
        btnMinus.setFont(txtFont);
        btnMinus.setText("-");
        btnMinus.setFocusable(false);
        btnMinus.addActionListener(e->checkOperator(btnMinus));
        panelEast.add(btnMinus);

        btnPlus = new JButton();
        btnPlus.setPreferredSize(new Dimension(80,80));
        btnPlus.setText("+");
        btnPlus.setFont(txtFont);
        btnPlus.setFocusable(false);
        btnPlus.addActionListener(e->checkOperator(btnPlus));
        panelEast.add(btnPlus);

        panelSouth = new JPanel();
        panelSouth.setPreferredSize(new Dimension(350,100));
        panelSouth.setLayout(new BorderLayout());
        panelSouth.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(panelSouth,BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,110));
        panel.setLayout(new FlowLayout());
        panelSouth.add(panel,BorderLayout.CENTER);

        buttonSign = new JButton();
        buttonSign.setPreferredSize(new Dimension(80,80));
        buttonSign.setText("+/-");
        buttonSign.setFont(txtFont);
        buttonSign.setFocusable(false);
        buttonSign.addActionListener(e->{
          if(!txtNum.getText().contains("-")){
              txtNum.setText("-"+txtNum.getText());
          }
        });
        panel.add(buttonSign);

        button0 = new JButton();
        button0.setPreferredSize(new Dimension(80,80));
        button0.setText("0");
        button0.setFocusable(false);
        button0.addActionListener(e->{
            if(!txtNum.getText().equals("0")){
                txtNum.setText(txtNum.getText()+button0.getText());
            }});
        button0.setFont(txtFont);
        panel.add(button0);

        buttonDecimal = new JButton();
        buttonDecimal.setPreferredSize(new Dimension(80,80));
        buttonDecimal.setText(".");
        buttonDecimal.setFont(txtFont);
        buttonDecimal.setFocusable(false);
        buttonDecimal.addActionListener(e -> {
            if(!txtNum.getText().contains(".")){
                txtNum.setText(txtNum.getText()+buttonDecimal.getText());
            }
        });
        panel.add(buttonDecimal);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(100,100));
        panelSouth.add(panel1,BorderLayout.EAST);

        btnEqual = new JButton();
        btnEqual.setPreferredSize(new Dimension(80,80));
        btnEqual.setText("=");
        btnEqual.setFocusable(false);
        btnEqual.setFont(txtFont);
        btnEqual.addActionListener(e->{
            try{
                if(String.valueOf(txtNum.getText().charAt(0)).equals("-")){
                    txtNum.setText(txtNum.getText().replace("-", ""));
                    String[] nums = txtNum.getText().split("[-+*/]");
                    num1 = Double.parseDouble(nums[0])*-1;
                    num2 = Double.parseDouble(nums[1]);
                }
                else{
                    String[] nums = txtNum.getText().split("[-+*/]");
                    num1 = Double.parseDouble(nums[0]);
                    num2 = Double.parseDouble(nums[1]);
                }

                switch (operator) {
                    case '+' -> txtNum.setText(String.valueOf(decimalFormat.format(num1 + num2)));
                    case '-' -> txtNum.setText(String.valueOf(decimalFormat.format(num1 - num2)));
                    case '*' -> txtNum.setText(String.valueOf(decimalFormat.format(num1 * num2)));
                    case '/' -> txtNum.setText(String.valueOf(decimalFormat.format(num1 / num2)));
                }
                if(txtNum.getText().equals("Infinity")){
                    txtNum.setText("Error");

                }

                operator = 'N';
            }catch (ArithmeticException exception){
                txtNum.setText("Error");
            }
            catch (ArrayIndexOutOfBoundsException exception){
                txtNum.setText(String.valueOf(num1+num1));
                operator = 'N';

            }
            catch (Exception exception){
                txtNum.setText("Error");
                operator = 'N';
                exception.printStackTrace();
            }
        });
        panel1.add(btnEqual);
    }
    private void buttonClick(JButton button,String numText){
        button = new JButton();
        button.setPreferredSize(new Dimension(80,80));
        button.setText(numText);
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("Courier New",Font.BOLD,25));
        JButton finalButton = button;
        button.addActionListener(e->{
           if(txtNum.getText().equals("0") || txtNum.getText().equals("Error")){
               txtNum.setText(finalButton.getText());
           }
           else {
               txtNum.setText(txtNum.getText()+ finalButton.getText());
           }
        });
        panelCenter.add(button);
    }
    private void checkOperator(JButton button){
        String op = String.valueOf(operator);
        if(op.equals("N")){
            if(!txtNum.getText().contains(button.getText())){
                operator = button.getText().charAt(0);
                txtNum.setText(txtNum.getText()+button.getText());
            }
        }

    }

}
