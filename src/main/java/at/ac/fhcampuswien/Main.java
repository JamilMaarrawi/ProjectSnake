package at.ac.fhcampuswien;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Snake");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.w.add(gamePanel.h);
        gamePanel.w.add(gamePanel.b);
        gamePanel.w.add(gamePanel.b2);
        gamePanel.w.add(gamePanel.t);

        gamePanel.startGameThread();
    }
}