import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subtractButton, multiplyButton, divideButton;
    JButton decimalButton, equalsButton, deleteButton, clearButton, negativeButton;
    JPanel panel;
    Font myFont = new Font("SansSerif", Font.BOLD, 24);

    double number1 = 0, number2 = 0, result = 0;
    char operator;

    //------------------------

    Calculator() {
        // frame settings
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        // text field settings
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setBorder(new EmptyBorder(10, 10, 10, 10));

    // ------------------------------------------------------

                    // initialize function buttons
                    addButton = createButton("+");
                    subtractButton = createButton("-");
                    multiplyButton = createButton("*");
                    divideButton = createButton("/");
                    decimalButton = createButton(".");
                    equalsButton = createButton("=");
                    deleteButton = createButton("Delete");
                    clearButton = createButton("Clear");
                    negativeButton = createButton("(-)");
                    // add function buttons to array
                    functionButtons[0] = addButton; // index 0
                    functionButtons[1] = subtractButton;
                    functionButtons[2] = multiplyButton;
                    functionButtons[3] = divideButton;
                    functionButtons[4] = decimalButton;
                    functionButtons[5] = equalsButton;
                    functionButtons[6] = deleteButton;
                    functionButtons[7] = clearButton;
                    functionButtons[8] = negativeButton;
                    // Configure function buttons
                    for (JButton button : functionButtons) { // for each button in functionButtons...
                            button.addActionListener(this); // add action to function buttons
                            styleButton(button); // add style to function buttons
                    }

                    // Configure number buttons
                    for (int i = 0; i < 10; i++) {
                        numberButtons[i] = createButton(String.valueOf(i)); // for each number button...
                            numberButtons[i].addActionListener(this); // add action to number buttons
                            styleButton(numberButtons[i]); // add style to number buttons
                    }

    // ------------------------------------------------------

        // bounds for negative, delete, clear (which are outside the panel)
        negativeButton.setBounds(50, 430, 100, 50);
        deleteButton.setBounds(150, 430, 100, 50);
        clearButton.setBounds(250, 430, 100, 50);

        // panel layout
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        // Add buttons to panel in layout order
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);
        panel.add(decimalButton);
        panel.add(numberButtons[0]);
        panel.add(equalsButton);
        panel.add(divideButton);

        // Add components to frame
        frame.add(textField);

        frame.add(panel);

        frame.add(negativeButton);
        frame.add(deleteButton);
        frame.add(clearButton);
        frame.setVisible(true);

    }
    // ------------------------------------------------------

                // Method to create styled buttons
                private JButton createButton(String text) {
                    JButton button = new JButton(text);
                    button.setFont(myFont);
                    return button;
                }

                // Method to style buttons
                private void styleButton(JButton button) {
                    button.setBackground(new Color(70, 130, 180));
                    button.setForeground(Color.WHITE);
                    button.setFocusable(false);
                    button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
                    button.setOpaque(true);
                }

    // ------------------------------------------------------
                @Override
                public void actionPerformed(ActionEvent e) {
                        // Loop through number buttons
                        for (int i = 0; i < 10; i++) {
                            // If a number button is pressed
                            if (e.getSource() == numberButtons[i]) {
                                // Add the number to the display
                                textField.setText(textField.getText() + i);
                            }
                        }

                        // Check if the decimal button was pressed
                        if (e.getSource() == decimalButton) {
                            // If there's no decimal point already, add one
                            if (!textField.getText().contains(".")) {
                                textField.setText(textField.getText().concat("."));
                            }
                        }

                        // Check if an operator button (+, -, *, /) was pressed
                        if (e.getSource() == addButton || e.getSource() == subtractButton ||
                                e.getSource() == multiplyButton || e.getSource() == divideButton) {

                            // Get the number currently in the display
                            number1 = Double.parseDouble(textField.getText());
                            // Figure out which operator was pressed
                            operator = e.getSource() == addButton ? '+' :
                                    e.getSource() == subtractButton ? '-' :
                                            e.getSource() == multiplyButton ? '*' : '/';
                            // Show the operator on the display
                            textField.setText(textField.getText() + " " + operator + " ");
                        }

                        // Check if the equals button was pressed
                        if (e.getSource() == equalsButton) {
                            // Break the display text into parts to get the second number
                            String[] parts = textField.getText().split(" ");
                            if (parts.length == 3) {
                                // Get the second number
                                number2 = Double.parseDouble(parts[2]);
                                // Do the calculation based on the operator
                                switch (operator) {
                                    case '+' -> result = number1 + number2; // Add
                                    case '-' -> result = number1 - number2; // Subtract
                                    case '*' -> result = number1 * number2; // Multiply
                                    case '/' -> result = number1 / number2; // Divide
                                }
                                // Show the result in the display
                                textField.setText(String.valueOf(result));
                                // Prepare for the next calculation
                                number1 = result;
                            }
                        }

                        // Check if the clear button was pressed
                        if (e.getSource() == clearButton) {
                            // Clear the display
                            textField.setText("");
                        }
                        // Check if the delete button was pressed
                        else if (e.getSource() == deleteButton) {
                            String text = textField.getText();
                            // If there's text to delete, remove the last character
                            if (text.length() > 0) {
                                textField.setText(text.substring(0, text.length() - 1));
                            }
                        }
                        // Check if the negative button was pressed
                        else if (e.getSource() == negativeButton) {
                            // Change the sign of the current number
                            double temp = Double.parseDouble(textField.getText());
                            textField.setText(String.valueOf(-temp)); // Show the new number
                        }
                }


}
