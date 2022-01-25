package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i =0; i< handler.object.size(); i++){
            GameObject temporaryObj = handler.object.get(i);
            if (temporaryObj.getID() == ID.Player) {
                if (key == KeyEvent.VK_W) temporaryObj.setVelY(-5);
                if (key == KeyEvent.VK_A) temporaryObj.setVelX(-5);
                if (key == KeyEvent.VK_S) temporaryObj.setVelY(5);
                if (key == KeyEvent.VK_D) temporaryObj.setVelX(5);
            }

        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);


        // Prints out ASCII Key
        System.out.println(key);
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i =0; i<handler.object.size(); i++){
            GameObject temporaryObj = handler.object.get(i);
            if (temporaryObj.getID() == ID.Player){
                if(key == KeyEvent.VK_W) temporaryObj.setVelY(0);
                if(key == KeyEvent.VK_A) temporaryObj.setVelX(0);
                if(key == KeyEvent.VK_S) temporaryObj.setVelY(0);
                if(key == KeyEvent.VK_D) temporaryObj.setVelX(0);
            }

        }
    }
}
