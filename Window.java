package main;

/*
Named the class Window
Canvas: It will store my graphics that I will display in the future
JFrame: Will "frame" the canvas and display it
Extends: Allows me to use other classes within Java
 */

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    /*
    Made my constructor
    Always has the same name as the class file
     */

    int width, height;
    String game_Title;

    public Window(int width, int height, String game_Title, Game game){
        JFrame frame = new JFrame(game_Title);

        frame.setFocusable(true);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();

    }
}
