package com.easymer.client;

import com.easymer.client.dto.OpcionDto;
import com.easymer.client.dto.TemaMultipleOpcionDto;
import com.easymer.client.enumeration.TipoTema;
import com.easymer.client.rest.RestCall;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TemaMultipleOpcion extends JFrame {

    private JPanel multipleOpcionPanel;
    private JTextField idField;
    private JTextField tituloField;
    private JTextField descripcionField;
    private JTextField ordenField;
    private JTextArea contenidoField;
    private JTextField correctaField;
    private JButton CANCELARButton;
    private JButton INSERTARButton;
    private JButton AGREGAROPCIONButton;
    private JTextArea opcionesArea;


    private List<OpcionDto> opcionesDto = new ArrayList<>();

    public TemaMultipleOpcion(String title){
        super(title);
        this.setContentPane(multipleOpcionPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        CANCELARButton.addActionListener(e -> dispose());

        AGREGAROPCIONButton.addActionListener(e -> {
            AgregarOpcion frame = new AgregarOpcion("Crear nueva opción.");
            frame.setFramePadre(getThis());
            frame.setVisible(true);
        });

        INSERTARButton.addActionListener(e -> {

            Optional<OpcionDto> idMatch = opcionesDto.stream().filter(opcionDto -> correctaField.getText().equals(opcionDto.getTag())).findAny();

            if(!idMatch.isPresent()){
                JOptionPane.showMessageDialog(null, "La opción correcta no se encuentra en el listado de opciones", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else if (validarCampos()){
                TemaMultipleOpcionDto temaMultipleOpcionDto = new TemaMultipleOpcionDto();
                temaMultipleOpcionDto.setId(idField.getText());
                temaMultipleOpcionDto.setCorrecta(correctaField.getText());
                temaMultipleOpcionDto.setOpciones(this.opcionesDto);
                temaMultipleOpcionDto.setContenido(contenidoField.getText());
                temaMultipleOpcionDto.setOrden(Integer.valueOf(ordenField.getText()));
                temaMultipleOpcionDto.setDescripcion(descripcionField.getText());
                temaMultipleOpcionDto.setTitulo(tituloField.getText());
                temaMultipleOpcionDto.setTipo(TipoTema.MULTIPLE_OPCION.name());

                RestCall restCall = new RestCall();

                try {
                    TemaResponse result = restCall.postTemaMultipleOpcion(temaMultipleOpcionDto);

                    if(result.getStatus() == 201){
                        JOptionPane.showMessageDialog(null, "Se insertó correctamente el tema", "OK", JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, result.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (JsonProcessingException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(null, "Hay campos vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private TemaMultipleOpcion getThis(){
        return this;
    }

    public void agregarOpcion(OpcionDto opcionDto){
        this.opcionesDto.add(opcionDto);
    }

    public int obtenerCantOpciones(){
        return this.opcionesDto.size();
    }

    public void refrescarOpciones(){
        StringBuilder stringBuilder = new StringBuilder();
        opcionesDto.forEach(opcionDto -> {
            stringBuilder.append(opcionDto + "\n");
        });
        opcionesArea.setText(stringBuilder.toString());
    }

    private boolean validarCampos(){
        return (idField.getText() != null && !idField.getText().isEmpty()) &&
                (tituloField.getText() != null && !tituloField.getText().isEmpty()) &&
                (descripcionField.getText() != null && !descripcionField.getText().isEmpty()) &&
                (ordenField.getText() != null && !ordenField.getText().isEmpty()) &&
                (contenidoField.getText() != null && !contenidoField.getText().isEmpty()) &&
                (correctaField.getText() != null && !correctaField.getText().isEmpty()) &&
                (this.opcionesDto.size() > 1);
    }
}
