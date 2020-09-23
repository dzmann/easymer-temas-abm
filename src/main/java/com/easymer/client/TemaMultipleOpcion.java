package com.easymer.client;

import javax.swing.*;

public class TemaMultipleOpcion extends JFrame {
    private JPanel multipleOpcionPanel;
    private JTextField idField;
    private JTextField tituloField;
    private JTextField descripcionField;
    private JTextField ordenField;
    private JTextArea contenidoField;
    private JButton CANCELARButton;
    private JButton INSERTARButton;
    private JButton AGREGAROPCIONButton;
    private JTextArea opcionesArea;

    public TemaMultipleOpcion(String title){
        super(title);
        this.setContentPane(multipleOpcionPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
