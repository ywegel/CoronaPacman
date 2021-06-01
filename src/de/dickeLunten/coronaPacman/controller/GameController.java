package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.models.panel.StartModel;
import de.dickeLunten.coronaPacman.views.View;
import de.dickeLunten.coronaPacman.views.panels.StartPanel;

import javax.swing.*;
import java.awt.*;

public class GameController {
    private JFrame frame;

    public GameController(){
        frame = initFrame();
        init();
    }

    private void init(){
        initStart();
    }

    private JFrame initFrame(){
        JFrame frame = new JFrame("CoronaPacman");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //frame.setUndecorated(true); //TODO wenn undekoriert sieht man keine elemente
        frame.setVisible(true);
        frame.setBackground(Color.black);
        return frame;
    }

    private void initStart(){
        StartModel startModel = new StartModel();
        StartPanel startPanel = new StartPanel(startModel);

        frame.add(startPanel);
    }

    //Removes all components + panels from frame
    private void resetFrame(){
        frame.getContentPane().removeAll();
        frame.repaint();
    }

}