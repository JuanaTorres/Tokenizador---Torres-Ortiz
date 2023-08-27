package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

import Model.Token;
import Model.Tokenize;
import View.View;

public class Controller implements ActionListener{
	
	private View gui;
	private Tokenize tne;
	private Boolean err;
	
	public Controller() {
		
		gui = new View();
		gui.getPanel1().getLoadFileB().addActionListener(this);
		gui.getPanel1().getValidateTokensB().addActionListener(this);
		gui.getPanel2().getLoadTFileB().addActionListener(this);
		gui.getPanel2().getTokenizeButton().addActionListener(this);
		tne= new Tokenize();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		err=false;
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Load File")) {
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(null);
			File f = jfc.getSelectedFile();
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line = "", load_gui = "";
				while((line = br.readLine())!=null) {
					/// el caracter, numero
					String splitS[]=line.split(",");
					if(splitS.length==2){
						int tokenid= Integer.parseInt(splitS[1].trim());
						System.out.println(splitS[0].trim()+"-"+tokenid);
						load_gui = load_gui + line + "\n";
						tne.add(splitS[0].trim(),tokenid);
					}
					gui.getPanel1().getTokenTextArea().setText(load_gui);
				}
				br.close();
				fr.close();
			} catch (Exception e1) {
				err=true;
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(e.getActionCommand().equals("Validate Tokens")) {
			
			if(err){
				gui.jopMessage("Ocurrio un Error\nRevise el formato del archivo",
				"Error al Validar Tokens",
				 2);
			}
			
		} else if(e.getActionCommand().equals("Load T.File")) {
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(null);
			File f = jfc.getSelectedFile();
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line = "", loadgui = "";
				while((line = br.readLine())!=null) {
					//tne.tokenize(line.trim().toString());
					loadgui = loadgui + line + "\n";
				}
				tne.tokenize(loadgui.trim().toString());
				gui.getPanel2().getInputTextArea().setText(loadgui);
				br.close();
				fr.close();
			} catch (Exception e1) {
				err=true;
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(e.getActionCommand().equals("Tokenize")) {
			String out="";
			for(Token ton : tne.getTokens()){
				out+="[Token:" + ton.token + " Lexema:" + ton.lexeme + " Posicion:" + ton.pos + "]\n";
				gui.getPanel2().getOutputTextArea().setText(out);
			}
		
		}
		
	}
	
}