package com.easymer.client;

import javax.swing.*;

public class TemaTeorico extends JFrame {
    private JTextField idField;
    private JTextField tituloField;
    private JTextField descripcionField;
    private JTextArea contenidoField;
    private JTextArea imageField;
    private JButton INSERTARButton;
    private JButton CANCELARButton;
    private JPanel teoricoPanel;
    private JTextField ordenField;

    public TemaTeorico(String title){
        super(title);
        this.setContentPane(teoricoPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
