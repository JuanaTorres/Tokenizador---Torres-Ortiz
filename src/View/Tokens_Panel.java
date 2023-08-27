package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Tokens_Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea tokenTextArea;
	private JScrollPane field1;
	private JButton loadFileB, validateTokensB;

    public Tokens_Panel() {
        
    	setLayout(null);
    	
    	TitledBorder border = BorderFactory.createTitledBorder("Tokens");
		border.setTitleFont(new Font("Arial Black", Font.BOLD,12));
		border.setTitleColor(Color.BLACK);
		setBorder(border);

        tokenTextArea = new JTextArea(20, 20);

        field1 = new JScrollPane(tokenTextArea);
        field1.setBounds(10, 16, 300, 300);
        add(field1);
        
        loadFileB = new JButton("Load File");
        loadFileB.setBounds(20, 330, 130, 50);
        add(loadFileB);
        
        validateTokensB = new JButton("Validate Tokens");
        validateTokensB.setBounds(170, 330, 130, 50);
		validateTokensB.setEnabled(false);
        add(validateTokensB);
        
    }

	public JTextArea getTokenTextArea() {
		return tokenTextArea;
	}

	public void setTokenTextArea(JTextArea tokenTextArea) {
		this.tokenTextArea = tokenTextArea;
	}

	public JScrollPane getField1() {
		return field1;
	}

	public void setField1(JScrollPane field1) {
		this.field1 = field1;
	}

	public JButton getLoadFileB() {
		return loadFileB;
	}

	public void setLoadFileB(JButton loadFileB) {
		this.loadFileB = loadFileB;
	}

	public JButton getValidateTokensB() {
		return validateTokensB;
	}

	public void setValidateTokensB(JButton validateTokensB) {
		this.validateTokensB = validateTokensB;
	}
    
}