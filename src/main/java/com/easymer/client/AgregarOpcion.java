package com.easymer.client;

import com.easymer.client.dto.OpcionDto;

import javax.swing.*;

public class AgregarOpcion extends JFrame{
    private JTextArea descripcionField;
    private JButton AGREGARButton;
    private JButton CANCELARButton;
    private JPanel agregarOpcionPanel;
    private JTextArea imagenField;
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
    }

    public void setFramePadre(TemaMultipleOpcion jFrame){
        this.framePadre = jFrame;
    }

    private boolean validarCampos(){
        return (descripcionField.getText() != null && !descripcionField.getText().isEmpty());
    }
}
