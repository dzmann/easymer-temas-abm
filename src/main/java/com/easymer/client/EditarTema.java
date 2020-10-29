package com.easymer.client;

import com.easymer.client.dto.TemaDto;
import com.easymer.client.dto.TemaMerDto;
import com.easymer.client.dto.TemaMultipleOpcionDto;
import com.easymer.client.dto.TemaTeoricoDto;
import com.easymer.client.rest.RestCall;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.io.IOException;

public class EditarTema extends JFrame{
    private JPanel panel;
    private JTextField idField;
    private JButton cancelarBtn;
    private JButton editarBtn;

    public EditarTema(String title){
        super(title);
        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        cancelarBtn.addActionListener(a -> dispose());

        editarBtn.addActionListener(a -> {

            if(!validarCampos()){
                JOptionPane.showMessageDialog(null, "Hay campos vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{

                RestCall restCall = new RestCall();
                try {
                    TemaResponse result = restCall.buscarTema(idField.getText());

                    if(result.getStatus() == 200){
                        TemaDto temaDto = result.getResult();
                        if(temaDto.getTipo().equals("TEORICO")){
                            TemaTeorico temaTeoricoFrame = new TemaTeorico("Editar Tema Teórico");
                            temaTeoricoFrame.updateTema((TemaTeoricoDto) temaDto);
                        }else if(temaDto.getTipo().equals("MULTIPLE_OPCION")){
                            TemaMultipleOpcion temaMultipleOpcion = new TemaMultipleOpcion("Editar Tema múltiple opción");
                            temaMultipleOpcion.updateTema((TemaMultipleOpcionDto) temaDto);
                        }else if(temaDto.getTipo().equals("MER")){
                            TemaMer temaMer = new TemaMer("Editar tema MER por ID");
                            temaMer.updateTema((TemaMerDto) temaDto);
                        }else{
                            JOptionPane.showMessageDialog(null, result.getMessage(), "TIPO DE TEMA INVÁLIDO", JOptionPane.ERROR_MESSAGE);
                        }

                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, result.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (JsonProcessingException e) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error llamando a la API", "ERROR", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        });
    }

    private boolean validarCampos(){
        return (idField.getText() != null && !idField.getText().isEmpty());
    }
}
