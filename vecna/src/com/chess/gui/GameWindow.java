package com.chess.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow {
    private final JFrame gameFrame;
    private final static float SCALE = 1.0f;
    private static Dimension OUTER_FRAME_DIMENSION = new Dimension((int) (600 * SCALE), (int) (600 * SCALE));

    public GameWindow() {
        this.gameFrame = new JFrame("Vecna");
        final JMenuBar windowMenuBar = populateMenuBar();
        this.gameFrame.setJMenuBar(windowMenuBar);

        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.gameFrame.add(gamePanel);
        this.gameFrame.setResizable(false);
        this.gameFrame.pack();
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setVisible(true);
    }

    private JMenuBar populateMenuBar() {
        final JMenuBar windowMenuBar = new JMenuBar();

        windowMenuBar.add(createFileOption());

        return  windowMenuBar;
    }

    private JMenu createFileOption() {
        final JMenu fileOption = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(actionEvent -> System.out.println("Open that PGN file!"));
        fileOption.add(openPGN);

        return fileOption;
    }
}
