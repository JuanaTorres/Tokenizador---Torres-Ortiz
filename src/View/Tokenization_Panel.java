package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Tokenization_Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea inputTextArea, outputTextArea;
	private JScrollPane field1, field2;
	private JButton tokenizeButton, loadTFileB;

    public Tokenization_Panel() {
        
    	setLayout(null);
    	
    	TitledBorder border = BorderFactory.createTitledBorder("Tokenization");
		border.setTitleFont(new Font("Arial Black", Font.BOLD,12));
		border.setTitleColor(Color.BLACK);
		setBorder(border);
    	
        inputTextArea = new JTextArea(100, 50);
        
        outputTextArea = new JTextArea(100, 50);
        outputTextArea.setEditable(false);

        field1 = new JScrollPane(inputTextArea);
        field1.setBounds(10, 20, 350, 340);
        add(field1);
        
        field2 = new JScrollPane(outputTextArea);
        field2.setBounds(376, 20, 350, 340);
        add(field2);

        loadTFileB = new JButton("Load T.File");
        loadTFileB.setBounds(10, 370, 350, 50);
        add(loadTFileB);
        
        tokenizeButton = new JButton("Tokenize");
        tokenizeButton.setBounds(376, 370, 350, 50);
        add(tokenizeButton);

    }

	public JTextArea getInputTextArea() {
		return inputTextArea;
	}

	public void setInputTextArea(JTextArea inputTextArea) {
		this.inputTextArea = inputTextArea;
	}

	public JTextArea getOutputTextArea() {
		return outputTextArea;
	}

	public void setOutputTextArea(JTextArea outputTextArea) {
		this.outputTextArea = outputTextArea;
	}

	public JScrollPane getField1() {
		return field1;
	}

	public void setField1(JScrollPane field1) {
		this.field1 = field1;
	}

	public JScrollPane getField2() {
		return field2;
	}

	public void setField2(JScrollPane field2) {
		this.field2 = field2;
	}

	public JButton getTokenizeButton() {
		return tokenizeButton;
	}

	public void setTokenizeButton(JButton tokenizeButton) {
		this.tokenizeButton = tokenizeButton;
	}
	
}
