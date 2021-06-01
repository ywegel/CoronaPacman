package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.models.panel.EndModel;
import de.dickeLunten.coronaPacman.models.panel.GameModel;
import de.dickeLunten.coronaPacman.models.panel.StartModel;
import de.dickeLunten.coronaPacman.views.panels.EndPanel;
import de.dickeLunten.coronaPacman.views.panels.GamePanel;
import de.dickeLunten.coronaPacman.views.panels.StartPanel;

import javax.swing.*;
import java.awt.*;

public class Controller implements ViewListener {
    private final JFrame frame;

    public Controller(){
        frame = initFrame();
        init();
    }

    private void init(){
        initStart();
    }

    private JFrame initFrame(){
        JFrame frame = new JFrame("CoronaPacman");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setUndecorated(true); //TODO wenn undekoriert sieht man keine elemente
        frame.setVisible(true);
        return frame;
    }

    private void initStart(){
        StartModel model = new StartModel();
        StartPanel panel = new StartPanel(model, this);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void initGamePanel(){
        GameModel model = new GameModel();
        GamePanel panel = new GamePanel(model);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void initEndPanel(){
        EndModel model = new EndModel();
        EndPanel panel = new EndPanel(model);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    //Removes all components + panels from frame
    private void resetFrame(){
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void onNavigate(NavigationPanels destination) {
        switch (destination){
            case START_PANEL -> {
                resetFrame();
                initStart();
            }
            case GAME_PANEL -> {
                resetFrame();
                initGamePanel();
            }
            case END_PANEL -> {
                resetFrame();
                initEndPanel();
            }
        }
    }
}