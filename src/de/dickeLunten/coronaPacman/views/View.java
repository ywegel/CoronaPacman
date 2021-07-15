package de.dickeLunten.coronaPacman.views;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.views.panels.*;
import util.Bundle;
import util.Data;

import javax.swing.*;

public class View {
    private final JFrame frame;
    private Model model;

    private StartPanel startPanel;
    private GamePanel gamePanel;
    private EndPanel endPanel;
    private CreditsPanel creditsPanel;
    private RulesPanel rulesPanel;
    private ViewListener viewListener;

    public View(Model model, ViewListener viewListener) {
        this.viewListener = viewListener;
        this.model = model;
        frame = initFrame();
        init();
    }

    private void init() {
        initPanels();
        initStart();
    }

    private void initPanels() {
        startPanel = new StartPanel(model.getStartModel(), viewListener);
        gamePanel = new GamePanel(model.getGameModel(),viewListener);
    }

    private JFrame initFrame() {
        JFrame frame = new JFrame("CoronaPacman");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);

        frame.setIconImage(Data.loadImageFromRes("img/VirusVendor.png"));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setUndecorated(true);
        frame.setVisible(true);
        return frame;
    }

    private void initStart() {
        startPanel.getActionMap().get("aa");
        frame.add(startPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void initCreditsPanel(){
        creditsPanel = new CreditsPanel(model.getCreditsModel(), viewListener);
        frame.add(creditsPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void initRulesPanel(){
        rulesPanel = new RulesPanel(model.getRulesModel(), viewListener);
        frame.add(rulesPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void initGamePanel() {
        gamePanel = new GamePanel(model.getGameModel(), viewListener);
        frame.add(gamePanel);
        frame.revalidate();
        frame.repaint();
    }
    private void initEndPanel(Bundle bundle) {
        endPanel = new EndPanel(model.getEndModel(), viewListener, bundle);
        frame.add(endPanel);
        frame.revalidate();
        frame.repaint();
    }

    //Removes all components + panels from frame
    private void resetFrame() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }

    public void onNavigate(NavigationPanels destination, Bundle bundle) {
        switch (destination) {
            case START_PANEL -> {
                resetFrame();
                initStart();
            }
            case GAME_PANEL -> {
                model.resetGameModel();
                resetFrame();
                initGamePanel();
            }
            case END_PANEL -> {
                resetFrame();
                initEndPanel(bundle);
            }
            case CREDITS_PANEL -> {
                resetFrame();
                initCreditsPanel();
            }
            case RULES_PANEL -> {
                resetFrame();
                initRulesPanel();
            }
        }
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}