package com.easymer.client;

import com.easymer.client.dto.OpcionDto;
import com.easymer.client.util.Utils;

import javax.swing.*;
import java.io.IOException;

public class VerOpcion extends JFrame {

    private JTextField idField;
    private JTextArea descripcionField;
    private JTextArea imagenField;
    private JButton CERRARButton;
    private JLabel imagenLabel;
    private JPanel verOpcionPanel;

    public VerOpcion(String title, OpcionDto opcionDto) throws IOException {
        super(title);
        this.setContentPane(verOpcionPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        cargarDatos(opcionDto);

        CERRARButton.addActionListener(e -> dispose());

    }

    private void cargarDatos(OpcionDto opcionDto) throws IOException {

        idField.setText(opcionDto.getId());
        descripcionField.setText(opcionDto.getDescripcion());
        imagenField.setText(opcionDto.getImagen());
        imagenLabel.setIcon(Utils.getImagen(opcionDto.getImagen().getBytes()));

    }



}
