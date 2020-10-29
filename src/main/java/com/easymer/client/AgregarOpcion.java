package com.easymer.client;

import com.easymer.client.dto.OpcionDto;
import com.easymer.client.util.Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;

public class AgregarOpcion extends JFrame{
    private JTextArea descripcionField;
    private JButton AGREGARButton;
    private JButton CANCELARButton;
    private JPanel agregarOpcionPanel;
    private JTextArea imagenField;
    private JLabel imageLabel;
    private TemaMultipleOpcion framePadre;

    public AgregarOpcion(String title){
        super(title);
        this.setContentPane(agregarOpcionPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        CANCELARButton.addActionListener(e -> dispose());

        AGREGARButton.addActionListener(e -> {
            if(validarCampos()){

                OpcionDto opcionDto = new OpcionDto();
                opcionDto.setId("OP" + (framePadre.obtenerCantOpciones() + 1));
                opcionDto.setDescripcion(descripcionField.getText());
                opcionDto.setImagen(imagenField.getText());
                framePadre.agregarOpcion(opcionDto);
                framePadre.refrescarOpciones();
                dispose();

            }else{
                JOptionPane.showMessageDialog(null, "Hay campos vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        imagenField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                imageLabel.setIcon(null);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    imageLabel.setIcon(Utils.getImagen(imagenField.getText().getBytes()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ERROR DECODIFICANDO LA IMAGEN", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {

            }
        });
    }



    public void setFramePadre(TemaMultipleOpcion jFrame){
        this.framePadre = jFrame;
    }

    private boolean validarCampos(){
        return (descripcionField.getText() != null && !descripcionField.getText().isEmpty());
    }
}
