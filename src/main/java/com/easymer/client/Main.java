package com.easymer.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JPanel mainPanel;
    private JButton temaTeóricoButton;
    private JButton temaMúltipleOpciónButton;
    private JButton temaMerButton;
    private JButton salirButton;

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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ABM para temas de EasyMer");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
