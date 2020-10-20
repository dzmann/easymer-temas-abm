package com.easymer.client;

import com.easymer.client.rest.RestCall;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;

public class EliminarTema extends JFrame {
    private JTextField idField;
    private JButton eliminarBtn;
    private JButton cancelarBtn;
    private JPanel eliminarPanel;

    public EliminarTema(String title){
        super(title);
        this.setContentPane(eliminarPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        cancelarBtn.addActionListener(a -> dispose());

        eliminarBtn.addActionListener(a -> {

            if(!validarCampos()){
                JOptionPane.showMessageDialog(null, "Hay campos vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{

                RestCall restCall = new RestCall();
                try {
                    TemaResponse result = restCall.borrarTema(idField.getText());

                    if(result.getStatus() == 200){
                        JOptionPane.showMessageDialog(null, "Se eliminó el tema correctamente", "OK", JOptionPane.PLAIN_MESSAGE);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, result.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (JsonProcessingException e) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error llamando a la API", "ERROR", JOptionPane.ERROR_MESSAGE);
                }


            }



        });
    }

    private boolean validarCampos(){
        return (idField.getText() != null && !idField.getText().isEmpty());
    }


}
