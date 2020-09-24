package com.easymer.client;

import com.easymer.client.dto.OpcionDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarOpcion extends JFrame{
    private JTextField idField;
    private JTextArea descripcionField;
    private JButton AGREGARButton;
    private JButton CANCELARButton;
    private JPanel agregarOpcionPanel;
    private TemaMultipleOpcion framePadre;

    public AgregarOpcion(String title){
        super(title);
        this.setContentPane(agregarOpcionPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        CANCELARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        AGREGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()){

                    OpcionDto opcionDto = new OpcionDto();
                    opcionDto.setId(idField.getText());
                    opcionDto.setDescripcion(descripcionField.getText());
                    framePadre.agregarOpcion(opcionDto);
                    framePadre.refrescarOpciones();
                    dispose();

                }else{
                    JOptionPane.showMessageDialog(null, "Hay campos vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void setFramePadre(TemaMultipleOpcion jFrame){
        this.framePadre = jFrame;
    }

    private boolean validarCampos(){
        return (idField.getText() != null && !idField.getText().isEmpty()) &&
                (descripcionField.getText() != null && !descripcionField.getText().isEmpty());
    }
}
