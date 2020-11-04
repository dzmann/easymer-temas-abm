package com.easymer.client;

import javax.swing.*;

public class VerImagen extends JFrame {
    private JPanel panel1;
    private JLabel imagenLabel;

    public VerImagen(String title){
        super(title);
        this.setContentPane(panel1);
        this.pack();
        this.setLocationRelativeTo(null);
    }


    public void setImagen(Icon imagen){
        imagenLabel.setIcon(imagen);
    }
}
