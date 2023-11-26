package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

import Model.Lexer.Lexer;
import Model.ast.Document;
import Model.parser.parser;
import View.View;

public class Controller implements ActionListener {

	private View gui;
	
	/*
	 * 
	 * Por si acaso, quita la librería java-cup-11b-runtime.jar del proyecto para poder correr solo la interfaz
	 * 
	 * */
	
	public Controller() throws FileNotFoundException {
		gui = new View();
		gui.getPanel1().getLoadTFileB().addActionListener(this);
		gui.getPanel1().getTokenizeButton().addActionListener(this);
		Reader reader = null;

		File input = new File("SerieNumeros.txt");

		reader = new FileReader(input);

		Lexer scanner = new Lexer(reader);

		parser parser = new parser(scanner);

		Document programa_axioma = null;

		try {
			programa_axioma = (Document) parser.parse().value;
			programa_axioma.interpret();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static int contexterror = 0;

	public static void error(String s) {
		System.out.println((contexterror++) + ". " + s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Load T.File")) {
			gui.jopMessage("En la Linea 62, tienes el sitio de donde se escribe el el campo de texto de la izquierda. PD: En el Main te deje un comentario",
					"Escritura del Panel Sobre el Boton (Load T.File) ", 1);
			JFileChooser jfc = new JFileChooser();
			int result = jfc.showOpenDialog(null); // Mostrar el diálogo de selección de archivo

			if (result == JFileChooser.APPROVE_OPTION) { // Verificar si se seleccionó un archivo
				File f = jfc.getSelectedFile();
				if (!f.getName().toLowerCase().endsWith(".txt")) {
					gui.jopMessage("Ocurrio un Error\nEl formato debe ser TXT", "Error al Validar Tokens", 2);
				} else {
					try {
						FileReader fr = new FileReader(f);
						BufferedReader br = new BufferedReader(fr);
						String line = "", loadgui = "";
						while ((line = br.readLine()) != null) {
							// tne.tokenize(line.trim().toString());
							loadgui = loadgui + line + "\n";
						}
						try {
							// Quita los comentarios
							String pattern = "(#.*$)|(\"\"\".*?\"\"\")";
							Pattern regexPattern = Pattern.compile(pattern, Pattern.MULTILINE);
							Matcher matcher = regexPattern.matcher(loadgui);
							String comm = matcher.replaceAll("");

							// tne.tokenize(comm.trim().toString());
							gui.getPanel1().getInputTextArea().setText(comm);
							gui.getPanel1().getTokenizeButton().setEnabled(true);
						} catch (Exception el) {
							gui.jopMessage("Ocurrio un Error\nNo esta un caracter en los tokens",
									"Error al Validar Tokens", 2);
						}
						br.close();
						fr.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else if (result == JFileChooser.CANCEL_OPTION) {
				System.out.println("Load File Operation Canceled.");
			}
		} else if (e.getActionCommand().equals("Tokenize")) {
			/*
			 * 
			 * Aquí es donde se debe conectar el modelo
			 * 
			 */
			gui.jopMessage("En la Linea 108, tienes el sitio de donde se debe implementar el modelo",
					"Escritura del Panel Sobre el Boton (Tokenize) ", 1);
			gui.getPanel1().getOutputTextArea().setText("===================TOKENS=====================\n\nHOLA JUANA");
			String out = "";
			/*
			 * try { gui.getPanel2().getOutputTextArea().setText(
			 * "==============TOKENS=================\n"); out =
			 * gui.getPanel2().getOutputTextArea().getText() + ""; for (Token ton :
			 * tne.getTokens()) { out += "[    Token:" + ton.token + "\tLexema:" +
			 * ton.sequence + "\tPosicion:" + ton.pos + "     ]\n"; } } catch (Exception e1)
			 * { gui.getPanel2().getOutputTextArea().setText("");
			 * gui.jopMessage(e1.getMessage()+"\n", "Error al tokenizar ", 2); } try {
			 * out+=("==============Parser=================\n");
			 * out+=(p.parse(tne.getTokens()));
			 * gui.getPanel2().getOutputTextArea().setText(out); } catch (Exception el) {
			 * gui.getPanel2().getOutputTextArea().setText("");
			 * gui.jopMessage(el.getMessage()+"\n", "Error al Parser", 2); }
			 */
		}

	}

}