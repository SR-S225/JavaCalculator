import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Write a description of class Calculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Calculator
{
    // instance variables
    private String input;
    private char operator;
    private boolean positive;
    private boolean numEnter;
    private String calcInp;

    
    
    /**
     * Constructor for objects of class Calculator
     */
    public Calculator()
    {
        input = "";
        operator = 'e';
        positive = true;
        numEnter = false;
        
        calcInp ="";
        
        makeFrame();
    }

    /**
     * This method is used to create the gui interface for the Calculator
     *
     * 
     * 
     */
    public void makeFrame() 
    {
        //creates a new window
        JFrame calcInterface =  new JFrame();
        
        //creates a new panel which can be used to create borders such as grids.
        JPanel borderFrame  = (JPanel)calcInterface.getContentPane();
        borderFrame.setLayout(new BorderLayout(8,8));
        
        
        
        //This creates a new text field which will be used to show the numbers
        JTextField screen  =  new JTextField(10);
        //Makes it at the top of the screen and stops the user from typing in it
        calcInterface.add(screen, BorderLayout.NORTH);
        screen.setEditable(false);
        //This sets the numbers to be shown from the right like a calculator and sets the starting value to 0
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setText("0");
        //This sets the size of the text field and the font. 
        screen.setPreferredSize(new Dimension(200,70));
        screen.setFont(new Font("Arial", Font.BOLD, 70));
        
        
        //This creates a new panel which will be used on borderFrame to make a grid of buttons.
        JPanel keyPad = new JPanel(new GridLayout(4,4));
        
        //This is calling the method createButton with the information of each button
        createButton(keyPad, "7", "Num", screen);
        createButton(keyPad, "8", "Num", screen);
        createButton(keyPad, "9", "Num", screen);
        createButton(keyPad, "÷", "Symbol", screen);
        
        createButton(keyPad, "4", "Num", screen);
        createButton(keyPad, "5", "Num", screen);
        createButton(keyPad, "6", "Num", screen);
        createButton(keyPad, "x", "Symbol", screen);
        
        createButton(keyPad, "1", "Num", screen);
        createButton(keyPad, "2", "Num", screen);
        createButton(keyPad, "3", "Num", screen);
        createButton(keyPad, "-", "Symbol", screen);
        
        createButton(keyPad, "AC", "AC", screen);
        createButton(keyPad, "0", "Num", screen);
        createButton(keyPad, "=", "Equal", screen);
        createButton(keyPad, "+", "Symbol", screen);
        //This adds the keyPad panel to borrderFrame.
        borderFrame.add(keyPad, BorderLayout.CENTER);
        
        
        screen.setText("0");
        
        //This sets the size of the window and then makes it visible to the user. 
        calcInterface.setSize(500,500);
        calcInterface.setVisible(true);
    }
    
    private void createButton(Container keyPad, String value,String type, JTextField screen ) {
        //This creates a new button with the value parsed in. Eg 7
        JButton newButton  = new JButton(value);
        //This sets the font and the look of the button 
        newButton.setFont(new Font("Arial", Font.BOLD, 40));
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        
        
        //This sets the colour of the button depending on the type of button that it is
        if(type.equals("Symbol")) {
            newButton.setForeground(Color.blue);
        }
        if(type.equals("Equal")) {
            newButton.setForeground(Color.red);
        }
        if(type.equals("AC")) {
            newButton.setForeground(Color.yellow);
        }
        
        //This gives each button a function so when it is clicked it will call calcButton
        newButton.addActionListener(e -> calcButton(type,screen,value));
        //Adds the button to the keyPad grid
        keyPad.add(newButton);
    }
    
    
    private void calcButton(String type,JTextField screen, String value) {
        //This checks if the type equals AC if so it resets every value as it is clearing everthing
        if(type.equals("AC")) {
            input = "";
            screen.setText("0");
            operator = 'e';
            numEnter = false;
            positive = true;
            calcInp = "";
        }
        //This checks if the type of button is a symbol
        else if(type.equals("Symbol")) {
            //If type equals +
            if(value.equals("+")) {
                //adds the current value of the input to calcInp
                calcInp = calcInp + input;
                
                //This checks if the current operator is empty or called after a result.
                switch(operator) {
                    //if e sets the operator to +
                    case 'e':
                        operator = '+';
                        
                        break;
                    //if r sets the operator to +
                    case 'r':
                        operator = '+';
                        break;
                }
                //resets the input on the screen and sets the operator.
                input = "";
                operator = '+';
                
                //This checks if a number was previously entered.
                if(numEnter == true) {
                    //if so adds the operator to calcInp and sets numEnter to false
                    calcInp = calcInp + operator;
                    numEnter = false;
                }
                //if calcInp is empty then it just sets numEnter to false
                else if (calcInp.length() == 0) {
                    numEnter = false;
                }
                //If neither it removes the previous character in calcInp and replaces it with the new operator.
                else {
                    calcInp = calcInp.substring(0,calcInp.length()-1);
                    calcInp = calcInp + operator;
                }
                
                //Sets the screen to show +
                screen.setText("+");
                
            }
            //If the value equals -
            else if (value.equals("-")) {
                //This sets positive to true then checks if the screen equals any operators or 0
                positive = true;
                if(screen.getText().equals("+") || screen.getText().equals("÷") ||screen.getText().equals("x") || screen.getText().equals("0") ) {
                    //If so then sets positve to false and shows - on the screen.
                    positive = false;
                    screen.setText("-");
                }
                
                //This then checks if positive is false. 
                if(positive == false) {
                    //If so adds a - before the input is added creating a negative number
                    calcInp = calcInp + "-" + input;
                }
                else {
                    //if not just adds the input as normal
                    calcInp = calcInp + input;
                }
                //Same as previous but changes the operator to - this time. 
                switch(operator) {
                    case 'e':
                        operator = '-';
                        
                        break;
                    case 'r':
                        operator = '+';
                        break;
                }
                //same as above but with different operator
                input = "";
                operator = '-';
                if(positive == true && numEnter == true) {
                    calcInp = calcInp + operator;
                    numEnter = false;
                }
                else if (calcInp.length() == 0) {
                    numEnter = false;
                }
                else {
                    calcInp =  calcInp.substring(0,calcInp.length()-1);
                    calcInp = calcInp + operator;
                }
                screen.setText("-");
            }
            else if (value.equals("x")) {
                
                //This is all same as above but with x operator
                calcInp = calcInp + input;
                switch(operator) {
                    case 'e':
                        operator = 'x';
                        
                        break;
                    case 'r':
                        operator = '+';
                        break;
                }
                input = "";
                operator = 'x';
                if(numEnter == true){
                    calcInp = calcInp + operator;
                    numEnter = false;
                }
                else if (calcInp.length() == 0) {
                    numEnter = false;
                }
                else {
                    calcInp = calcInp.substring(0,calcInp.length()-1);
                    calcInp = calcInp + operator;
                }
                
                screen.setText("x");
                
                
                
            }
            else if (value.equals("÷")) {
                
                //same as above but with / operator
                calcInp = calcInp + input;
                switch(operator) {
                    case 'e':
                        operator = '/';
                        
                        break;
                    case 'r':
                        operator = '+';
                        break;
                }
                input = "";
                operator = '/';
                if(numEnter == true) {
                    calcInp = calcInp + operator;
                    numEnter = false;
                }
                else if (calcInp.length() == 0) {
                    numEnter = false;
                }
                else {
                    calcInp =  calcInp.substring(0,calcInp.length()-1);
                    calcInp = calcInp + operator;
                }
                screen.setText("÷");
                
                
            }
            
        }
        
        else if (type.equals("Equal")) {
            //if equals is pressed it gets the last input and adds it to calcInp.
            calcInp = calcInp + input;
            numEnter = false;
            //This then calls the calculate method
            calculate(screen);
            //This resets anything needs apart from the final result. 
            input = "";
            operator = 'e';
            numEnter = false;
            positive = true;
            calcInp = "";
        }
        else if(type.equals("Num")) {
            //If the button is a number then it just adds the number to the screen and sets numEnter to true.
            input  = input + value;
            screen.setText(input);
            numEnter = true;
        }
    }
    
    private void calculate(JTextField screen) {
        //This splits calcInp up at every operator 
        String[] calcArr = calcInp.split("(?<=[-+x/()])|(?=[-+x/()])");
  
        //This is done so we don't have to deal with any empty operators left at the beginning or the end of the array.
        int lastIndex = lastNum(calcArr);

        //This first loops is used to go through the array checking for divide operators to abide by bidmas
        for (int i = 0; i <= lastIndex; i++) {
            //This checks if the current element equals /
            boolean check = false;
            if (calcArr[i].equals("/")) {
                //If so sets the value before the operator to the left number
                double left = Double.parseDouble(calcArr[i - 1]);
                double right;
                //This then checks if the next value is equal to - 
                if (calcArr[i + 1].equals("-")) {
                    //If so then it goes to the next element after and sets it as a negative number in right
                    right = -Double.parseDouble(calcArr[i + 2]);
                    //It then clears the values in each of these elements 
                    calcArr[i + 1] = "";
                    calcArr[i + 2] = "";
                } else {
                    //If not will just set the next element after the operator to right then clear it
                    right = Double.parseDouble(calcArr[i + 1]);
                    calcArr[i + 1] = "";
                }
                //This then divides the two numbers and adds the result to the element before the operator.
                double result = left / right;
                calcArr[i - 1] = Double.toString(result);
                calcArr[i] = "";
                
            } 
            
            
            
        }
        
        //This works exactly the same as above but with multiplication this is called straight after to abide by bidmas
        for (int i = 0; i <= lastIndex; i++) {
            if (calcArr[i].equals("x")) {
                double left = Double.parseDouble(calcArr[i - 1]);
                double right;
                if (calcArr[i + 1].equals("-")) {
                    right = -Double.parseDouble(calcArr[i + 2]);
                    calcArr[i + 1] = "";
                    calcArr[i + 2] = "";
                } else {
                    right = Double.parseDouble(calcArr[i + 1]);
                    calcArr[i + 1] = "";
                }
                double result = left * right;
                calcArr[i - 1] = Double.toString(result);
                calcArr[i] = "";
            }
            
        }
        
        
        
        //This sets result back to 0 so we can calculate the addition and subtraction. This is last to abide by bidmas
        double result = 0;
        String operator = "+";
        
        //This then loops through calcArr
        for (int i = 0; i <= lastIndex; i++) {
            //if the element equals + or - the operator is set
            if (calcArr[i].equals("+")) {
                operator = "+";
            } else if (calcArr[i].equals("-")) {
                operator = "-";
            } else if (!calcArr[i].isEmpty()) {
                //If the element is not empty (if it is a number)
                double value;
                //If it equals - then as the same as above it sets the next element to a negative number
                if (calcArr[i].equals("-")) {
                    value = -Double.parseDouble(calcArr[i + 1]);
                    i++;
                } 
               
                else {
                    //if not just sets the value as the current element
                    value = Double.parseDouble(calcArr[i]);
                }
                //This then checks if the final operator is + it will add the value to results if not subtract
                if (operator.equals("+")) {
                    result += value;
                } else {
                    result -= value;
                }
            }
        }
        
        //This then outputs the final result onto the screen.
        screen.setText(Double.toString(result));
    
    }
    
    
    
    private int lastNum(String[] calcArr) {
        //Sets the last index as -1 because it hasn't been found yet and sets the index to the size of the array anf found to false
        int lastIndex = -1;
        int index= calcArr.length -1;
        boolean found = false;
        
        //This loops through while the index is greater than 0 and found is equal to false. this is because it is looking backwards through the array
        while (found == false && index >= 0) {
            //If the element is an operator it keeps it as false
            if(calcArr[index].equals("+") || calcArr[index].equals("-") || calcArr[index].equals("x") || calcArr[index].equals("/")) {
                found = false;
            }
            else {
                //if it is a number then we have found the last number and can ignore the rest of the array
                found = true;
                lastIndex = index;
            }
            index--;
        }
        
        return lastIndex;
    }
}
    
    
    

