package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.models.entities.PlayerDirection;
import de.dickeLunten.coronaPacman.views.View;
import de.dickeLunten.coronaPacman.views.panels.GamePanel;
import de.dickeLunten.coronaPacman.views.panels.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

enum InputAction {
    ACTION_UP,
    ACTION_DOWN,
    ACTION_LEFT,
    ACTION_RIGHT
}

public class Controller implements ViewListener {

    private ActionUp actionUp;
    private ActionDown actionDown;
    private ActionLeft actionLeft;
    private ActionRight actionRight;
    private ActionEnter actionEnter;

    public Controller(View view) {
        this.view = view;
        initStartInput(view.getStartPanel());
        initGameInput(view.getGamePanel());

        while (true) {

        }
    }

    private void initStartInput(StartPanel panel) {
        actionEnter = new Controller.ActionEnter();
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("VK_RETURN"), "Enter_action");
        panel.getActionMap().put("Enter_action", actionEnter);
    }

    private void initGameInput(GamePanel panel) {
        actionUp = new ActionUp();
        actionDown = new ActionDown();
        actionLeft = new ActionLeft();
        actionRight = new ActionRight();

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("VK_UP"), InputAction.ACTION_UP);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), InputAction.ACTION_UP);
        panel.getActionMap().put(InputAction.ACTION_UP, actionUp);

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("VK_DOWN"), InputAction.ACTION_DOWN);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), InputAction.ACTION_DOWN);
        panel.getActionMap().put(InputAction.ACTION_DOWN, actionDown);

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("VK_LEFT"), InputAction.ACTION_LEFT);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), InputAction.ACTION_LEFT);
        panel.getActionMap().put(InputAction.ACTION_LEFT, actionLeft);

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("VK_RIGHT"), InputAction.ACTION_RIGHT);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), InputAction.ACTION_RIGHT);
        panel.getActionMap().put(InputAction.ACTION_RIGHT, actionRight);
    }


    public class ActionUp extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ActionDown extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ActionLeft extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ActionRight extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ActionEnter extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            onNavigate(NavigationPanels.GAME_PANEL);
        }
    }


}