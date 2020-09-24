package com.easymer.client;

import com.easymer.client.dto.TemaTeoricoDto;
import com.easymer.client.enumeration.TipoTema;
import com.easymer.client.rest.RestCall;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        CANCELARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        INSERTARButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validarCampos()){
                    JOptionPane.showMessageDialog(null, "ERROR", "Hay campos vacíos", JOptionPane.ERROR_MESSAGE);
                }else{
                    TemaTeoricoDto temaTeoricoDto = new TemaTeoricoDto();
                    temaTeoricoDto.setId(idField.getText());
                    temaTeoricoDto.setContenido(contenidoField.getText());
                    temaTeoricoDto.setTitulo(tituloField.getText());
                    temaTeoricoDto.setDescripcion(descripcionField.getText());
                    temaTeoricoDto.setOrden(Integer.valueOf(ordenField.getText()));
                    temaTeoricoDto.setTipo(TipoTema.TEORICO.name());

                    RestCall restCall = new RestCall();
                    boolean result = restCall.postTemaTeorico(temaTeoricoDto);

                    if(result){
                        JOptionPane.showMessageDialog(null, "OK", "Se insertó correctamente el tema", JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "ERROR", "Ocurrió un error al guardar el tema", JOptionPane.ERROR_MESSAGE);
                    }
                    limpiarCampos();
                }
            }
        });
    }

    private boolean validarCampos(){
        return (idField.getText() != null && !idField.getText().isEmpty()) &&
                (tituloField.getText() != null && !tituloField.getText().isEmpty()) &&
                (descripcionField.getText() != null && !descripcionField.getText().isEmpty()) &&
                (contenidoField.getText() != null && !contenidoField.getText().isEmpty()) &&
                (ordenField.getText() != null && !ordenField.getText().isEmpty());
    }

    private void limpiarCampos(){
        idField.setText("");
        tituloField.setText("");
        descripcionField.setText("");
        contenidoField.setText("");
        ordenField.setText("");
        imageField.setText("");
    }

}
