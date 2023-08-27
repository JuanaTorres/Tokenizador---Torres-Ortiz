package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFileChooser;

import Model.Lexerexception;
import Model.Token;
import Model.Tokenize;
import View.View;

public class Controller implements ActionListener {

    private View gui;
    private Tokenize tne;
    private Boolean err;

    public Controller() {
        err = false;
        gui = new View();
        gui.getPanel1().getLoadFileB().addActionListener(this);
        gui.getPanel1().getValidateTokensB().addActionListener(this);
        gui.getPanel2().getLoadTFileB().addActionListener(this);
        gui.getPanel2().getTokenizeButton().addActionListener(this);
        tne = new Tokenize();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("Load File")) {
            JFileChooser jfc = new JFileChooser();
            int result = jfc.showOpenDialog(null); // Mostrar el diálogo de selección de archivo

            if (result == JFileChooser.APPROVE_OPTION) { // Verificar si se seleccionó un archivo
                File f = jfc.getSelectedFile();
                if (!f.getName().toLowerCase().endsWith(".txt")) {
                    gui.jopMessage("Ocurrio un Error\nEl formato debe ser TXT",
                            "Error al Validar Tokens",
                            2);
                } else {
                    try {
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        String line = "", load_gui = "";
                        while ((line = br.readLine()) != null) {
                            /// el caracter, numero
                            String splitS[] = line.split(",");
                            if (splitS.length == 2) {
                                int tokenid = Integer.parseInt(splitS[1].trim());
                                //System.out.println(splitS[0].trim()+"-"+tokenid);
                                load_gui = load_gui + line + "\n";
                                try {
                                    tne.add(splitS[0].trim(), tokenid);
                                } catch (PatternSyntaxException ep) {
                                    System.out.println("entro");
                                    this.err = true;
                                }

                            }
                            gui.getPanel1().getTokenTextArea().setText(load_gui);
                        }
                        br.close();
                        fr.close();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("Operación de carga de archivo cancelada.");
            }
        } else if (e.getActionCommand().equals("Validate Tokens")) {

            if (err) {
                gui.jopMessage("Ocurrio un Error\nRevise la declaracion de la expresion regular",
                        "Error al Validar Tokens",
                        2);
            }

        } else if (e.getActionCommand().equals("Load T.File")) {
            JFileChooser jfc = new JFileChooser();
            int result = jfc.showOpenDialog(null); // Mostrar el diálogo de selección de archivo

            if (result == JFileChooser.APPROVE_OPTION) { // Verificar si se seleccionó un archivo
                File f = jfc.getSelectedFile();
                if (!f.getName().toLowerCase().endsWith(".txt")) {
                    gui.jopMessage("Ocurrio un Error\nEl formato debe ser TXT",
                            "Error al Validar Tokens",
                            2);
                } else {
                    try {
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        String line = "", loadgui = "";
                        while ((line = br.readLine()) != null) {
                            //tne.tokenize(line.trim().toString());
                            loadgui = loadgui + line + "\n";
                        }
                        try {
                            tne.tokenize(loadgui.trim().toString());
                            gui.getPanel2().getInputTextArea().setText(loadgui);
                        } catch (Lexerexception el) {
                            gui.jopMessage("Ocurrio un Error\nno se esta un caracter en los tokens",
                                    "Error al Validar Tokens",
                                    2);
                        }
                        br.close();
                        fr.close();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("Operación de carga de archivo cancelada.");
            }
        } else if (e.getActionCommand().equals("Tokenize")) {
            String out = "";
            for (Token ton : tne.getTokens()) {
                out += "[Token:" + ton.token + " Lexema:" + ton.lexeme + " Posicion:" + ton.pos + "]\n";
                gui.getPanel2().getOutputTextArea().setText(out);
            }

        }

    }

}