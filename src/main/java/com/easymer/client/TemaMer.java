package com.easymer.client;

import com.easymer.client.dto.MerComponent;
import com.easymer.client.dto.TemaMerDto;
import com.easymer.client.dto.TemaTeoricoDto;
import com.easymer.client.enumeration.TipoTema;
import com.easymer.client.rest.RestCall;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TemaMer extends JFrame {
    private JPanel panel;
    private JTextField idField;
    private JTextField tituloField;
    private JTextField descripcionField;
    private JTextField ordenField;
    private JComboBox tipoMerCombo;
    private JTextArea contenidoField;
    private JTextArea merCorrectoField;
    private JTextArea merIncompletoField;
    private JButton insertarBtn;
    private JButton cancelarBtn;
    private String operation = "INSERT";

    private static final String COMPLETAR = "COMPLETAR";
    private static final String VERIFICAR = "VERIFICAR";

    public TemaMer(String title){
        super(title);
        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        tipoMerCombo.addItem(VERIFICAR);
        tipoMerCombo.addItem(COMPLETAR);

        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        insertarBtn.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validarCampos()){
                    JOptionPane.showMessageDialog(null, "Hay campos vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    TemaMerDto temaMerDto = new TemaMerDto();
                    temaMerDto.setId(idField.getText());
                    temaMerDto.setContenido(contenidoField.getText());
                    temaMerDto.setTitulo(tituloField.getText());
                    temaMerDto.setDescripcion(descripcionField.getText());
                    temaMerDto.setOrden(Integer.valueOf(ordenField.getText()));
                    temaMerDto.setTipo(TipoTema.MER.name());
                    temaMerDto.setMerCorrecto(merCorrectoField.getText());
                    temaMerDto.setMerIncompleto(merIncompletoField.getText());
                    temaMerDto.setTipoMer((String)tipoMerCombo.getSelectedItem());

                    RestCall restCall = new RestCall();
                    TemaResponse result = null;

                    if(operation.equals("INSERT")){
                        result = insertTemaMer(restCall, temaMerDto);
                    }else{
                        result = updateTemaMer(restCall, temaMerDto);
                    }


                    if(result.getStatus() == 201 && operation.equals("INSERT")){
                        JOptionPane.showMessageDialog(null, "Se insertó correctamente el tema", "OK", JOptionPane.PLAIN_MESSAGE);
                        limpiarCampos();
                    }else if(result.getStatus() == 200 && operation.equals("UPDATE")){
                        limpiarCampos();
                        idField.setEditable(true);
                        operation = "INSERT";
                        String[] options = {"Editar Otro", "Menú principal"};
                        int x = JOptionPane.showOptionDialog(null, "Se actualizó correctamente el tema",
                                "Editar Tema",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                        if(x == 0){
                            EditarTema editarTema = new EditarTema("Editar Tema por ID");
                            dispose();
                        }else{
                            dispose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, result.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public TemaResponse updateTemaMer(RestCall restCall, TemaMerDto temaMerDto) throws JsonProcessingException {
        TemaResponse result = null;
        if(operation.equals("UPDATE") && (String)tipoMerCombo.getSelectedItem() == COMPLETAR && validarMer(merCorrectoField.getText()) && validarMer(merIncompletoField.getText())){
            result = restCall.updateTema(temaMerDto);
        }else if(operation.equals("UPDATE") && (String)tipoMerCombo.getSelectedItem() == VERIFICAR && validarMer(merCorrectoField.getText())) {
            temaMerDto.setMerIncompleto("[]");
            result = restCall.updateTema(temaMerDto);
        }else{
            JOptionPane.showMessageDialog(null, "JSON Inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    private TemaResponse insertTemaMer(RestCall restCall, TemaMerDto temaMerDto) throws JsonProcessingException {
        TemaResponse result = null;
        if((String)tipoMerCombo.getSelectedItem() == COMPLETAR && validarMer(merCorrectoField.getText()) && validarMer(merIncompletoField.getText())){
            result = restCall.postTemaMer(temaMerDto);
        }else if((String)tipoMerCombo.getSelectedItem() == VERIFICAR && validarMer(merCorrectoField.getText())) {
            temaMerDto.setMerIncompleto("[]");
            result = restCall.postTemaMer(temaMerDto);
        }else{
            JOptionPane.showMessageDialog(null, "JSON Inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public void updateTema(TemaMerDto temaTeorico){
        this.operation = "UPDATE";
        idField.setText(temaTeorico.getId().toUpperCase());
        tituloField.setText(temaTeorico.getTitulo());
        descripcionField.setText(temaTeorico.getDescripcion());
        contenidoField.setText(temaTeorico.getContenido());
        tipoMerCombo.setSelectedItem(temaTeorico.getTipoMer());
        ordenField.setText(String.valueOf(temaTeorico.getOrden()));
        merCorrectoField.setText(temaTeorico.getMerCorrecto());
        merIncompletoField.setText(temaTeorico.getMerIncompleto());
        idField.setEditable(false);
    }


    private boolean validarCampos(){
        return (idField.getText() != null && !idField.getText().isEmpty()) &&
                (tituloField.getText() != null && !tituloField.getText().isEmpty()) &&
                (descripcionField.getText() != null && !descripcionField.getText().isEmpty()) &&
                (contenidoField.getText() != null && !contenidoField.getText().isEmpty()) &&
                (ordenField.getText() != null && !ordenField.getText().isEmpty()) &&
                (merCorrectoField.getText() != null && !merCorrectoField.getText().isEmpty()) &&
                validarTipoMer();
    }

    private boolean validarTipoMer(){
        String tipoMer = (String) tipoMerCombo.getSelectedItem();
        if(tipoMer == COMPLETAR){
            return (merIncompletoField.getText() != null && !merIncompletoField.getText().isEmpty());
        }
        return true;
    }

    private void limpiarCampos(){
        idField.setText("");
        tituloField.setText("");
        descripcionField.setText("");
        contenidoField.setText("");
        ordenField.setText("");
        merCorrectoField.setText("");
        merIncompletoField.setText("");
    }


    private boolean validarMer(String jsonStr){
        ObjectMapper objectMapper = new ObjectMapper();
        boolean result = false;

        List<MerComponent> merStructure = new ArrayList<>();
        try {
            merStructure = objectMapper.readValue(jsonStr, List.class);
            result = true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }

        return result;
    }


}
