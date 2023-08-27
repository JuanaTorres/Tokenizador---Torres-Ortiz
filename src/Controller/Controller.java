package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import Model.Tokenizer;
import View.View;

public class Controller implements ActionListener{
	
	private View gui;
	private Tokenizer tne;
	
	public Controller() {
		
		gui = new View();
		gui.getPanel1().getLoadFileB().addActionListener(this);
		gui.getPanel1().getValidateTokensB().addActionListener(this);
		gui.getPanel2().getTokenizeButton().addActionListener(this);
		tne= new Tokenizer();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Load File")) {
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(null);
			File f = jfc.getSelectedFile();
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				while((line = br.readLine())!=null) {
					/// el caracter, numero
					String splitS[]=line.split(",");
					if(splitS.length==2){
						int tokenid= Integer.parseInt(splitS[0].trim());
						tne.add(splitS[0].trim(),tokenid);
					}
					//code.append(line+"\n");

				}
				br.close();
				fr.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(e.equals("Validate Tokens")) {
			
			
			
		} else if(e.equals("Load T.File")) {
			
			
			
		}else if(e.equals("Tokenize")) {
			
		
		}
		
	}
	
}