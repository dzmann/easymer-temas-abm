package com.easymer.client;

import com.easymer.client.dto.TemaTeoricoDto;
import com.easymer.client.enumeration.TipoTema;
import com.easymer.client.rest.RestCall;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

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
    private JButton verImagenBtn;
    private String operation = "INSERT";
    private VerImagen verImagenFrame;

    public TemaTeorico(String title){
        super(title);
        this.setContentPane(teoricoPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        verImagenFrame = new VerImagen("Ver imagen");

        imageField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                verImagenFrame.setImagen(null);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {



                try {
                    verImagenFrame.setImagen(getImagen(imageField.getText().getBytes()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ERROR DECODIFICANDO LA IMAGEN", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {

            }
        });

        verImagenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verImagenFrame.setVisible(true);
            }
        });


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
                    JOptionPane.showMessageDialog(null, "Hay campos vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    TemaTeoricoDto temaTeoricoDto = new TemaTeoricoDto();
                    temaTeoricoDto.setId(idField.getText().toUpperCase());
                    temaTeoricoDto.setContenido(contenidoField.getText());
                    temaTeoricoDto.setTitulo(tituloField.getText());
                    temaTeoricoDto.setDescripcion(descripcionField.getText());
                    temaTeoricoDto.setOrden(Integer.valueOf(ordenField.getText()));
                    temaTeoricoDto.setTipo(TipoTema.TEORICO.name());
                    temaTeoricoDto.setImagen(imageField.getText());

                    RestCall restCall = new RestCall();
                    TemaResponse result = null;

                    if(operation.equals("UPDATE")){
                         result = restCall.updateTema(temaTeoricoDto);
                    }else{
                         result = restCall.postTemaTeorico(temaTeoricoDto);
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

    private ImageIcon getImagen(byte[] imageBytes) throws IOException {
        byte[] btDataFile = Base64.getDecoder().decode(imageBytes);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(btDataFile));
        ImageIcon imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    private boolean validarCampos(){
        return (idField.getText() != null && !idField.getText().isEmpty()) &&
                (tituloField.getText() != null && !tituloField.getText().isEmpty()) &&
                (descripcionField.getText() != null && !descripcionField.getText().isEmpty()) &&
                (contenidoField.getText() != null && !contenidoField.getText().isEmpty()) &&
                (ordenField.getText() != null && !ordenField.getText().isEmpty());
    }

    public void updateTema(TemaTeoricoDto temaTeorico) throws IOException {
        this.operation = "UPDATE";
        idField.setText(temaTeorico.getId());
        tituloField.setText(temaTeorico.getTitulo());
        descripcionField.setText(temaTeorico.getDescripcion());
        contenidoField.setText(temaTeorico.getContenido());
        ordenField.setText(String.valueOf(temaTeorico.getOrden()));
        imageField.setText(temaTeorico.getImagen());

        idField.setEditable(false);
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
