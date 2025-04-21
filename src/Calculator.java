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

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setBorder(new EmptyBorder(10, 10, 10, 10));

        addButton = createButton("+");
        subtractButton = createButton("-");
        multiplyButton = createButton("*");
        divideButton = createButton("/");
        decimalButton = createButton(".");
        equalsButton = createButton("=");
        deleteButton = createButton("Delete");
        clearButton = createButton("Clear");
        negativeButton = createButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = decimalButton;
        functionButtons[5] = equalsButton;
        functionButtons[6] = deleteButton;
        functionButtons[7] = clearButton;
        functionButtons[8] = negativeButton;

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            styleButton(button);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            styleButton(numberButtons[i]);
        }

        negativeButton.setBounds(50, 430, 100, 50);
        deleteButton.setBounds(150, 430, 100, 50);
        clearButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

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

        frame.add(textField);
        frame.add(panel);
        frame.add(negativeButton);
        frame.add(deleteButton);
        frame.add(clearButton);
        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(myFont);
        return button;
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
        button.setOpaque(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }

        if (e.getSource() == decimalButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
        }

        if (e.getSource() == addButton || e.getSource() == subtractButton ||
                e.getSource() == multiplyButton || e.getSource() == divideButton) {

            number1 = Double.parseDouble(textField.getText());
            operator = e.getSource() == addButton ? '+' :
                    e.getSource() == subtractButton ? '-' :
                            e.getSource() == multiplyButton ? '*' : '/';
            textField.setText(textField.getText() + " " + operator + " ");
        }

        if (e.getSource() == equalsButton) {
            String[] parts = textField.getText().split(" ");
            if (parts.length == 3) {
                number2 = Double.parseDouble(parts[2]);
                switch (operator) {
                    case '+' -> result = number1 + number2;
                    case '-' -> result = number1 - number2;
                    case '*' -> result = number1 * number2;
                    case '/' -> result = number1 / number2;
                }
                textField.setText(String.valueOf(result));
                number1 = result;
            }
        }

        if (e.getSource() == clearButton) {
            textField.setText("");
        } else if (e.getSource() == deleteButton) {
            String text = textField.getText();
            if (text.length() > 0) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        } else if (e.getSource() == negativeButton) {
            double temp = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(-temp));
        }
    }
}
