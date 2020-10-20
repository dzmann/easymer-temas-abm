package com.easymer.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static JFrame jFrame;
    private JPanel mainPanel;
    private JButton temaTeóricoButton;
    private JButton temaMúltipleOpciónButton;
    private JButton temaMerButton;
    private JButton salirButton;
    private JButton eliminarBtn;

    public Main() {
        temaTeóricoButton.addActionListener(e -> {
            JFrame frame = new TemaTeorico("Crear Tema Teórico");
            frame.setVisible(true);
        });
        temaMúltipleOpciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new TemaMultipleOpcion("Crear Tema Múltiple Opción");
                frame.setVisible(true);
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new EliminarTema("Eliminar tema por ID");
                frame.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        jFrame = new JFrame("ABM para temas de EasyMer");
        jFrame.setContentPane(new Main().mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
