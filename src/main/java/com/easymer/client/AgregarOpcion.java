package com.easymer.client;

import com.easymer.client.dto.OpcionDto;
import com.easymer.client.util.Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class AgregarOpcion extends JFrame{
    private JTextArea descripcionField;
    private JButton AGREGARButton;
    private JButton CANCELARButton;
    private JPanel agregarOpcionPanel;
    private JTextArea imagenField;
    private JButton verImagenButton;
    private TemaMultipleOpcion framePadre;
    private VerImagen verImagenFrame;

    public AgregarOpcion(String title){
        super(title);
        this.setContentPane(agregarOpcionPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        verImagenFrame = new VerImagen("Ver Imagen");

        CANCELARButton.addActionListener(e -> dispose());

        AGREGARButton.addActionListener(e -> {
            if(validarCampos()){

                OpcionDto opcionDto = new OpcionDto();
                opcionDto.setDescripcion(descripcionField.getText());
                opcionDto.setImagen(imagenField.getText());
                framePadre.agregarOpcion(opcionDto);
                dispose();

            }else{
                JOptionPane.showMessageDialog(null, "Hay campos vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        verImagenButton.addActionListener(e -> verImagenFrame.setVisible(true));

        imagenField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                verImagenFrame.setImagen(null);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                verImagenFrame.setImagen(Utils.getImagen(imagenField.getText().getBytes()));
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
