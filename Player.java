package main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }



    public void collision(){
        for(int i =0; i < handler.object.size(); i++){ // Go through all the objects within the game

            GameObject tempObject = handler.object.get(i); // Create a temporary object || Gets each instance of our for loop

            if (tempObject.getID() == ID.Enemy) { // If our temporary object is equal to the enemy || The block of code only applies to the enemy object
                if(getBounds().intersects(tempObject.getBounds())){ // If our rectangle intersects with our temporary object (enemy)
                    HUD.HEALTH -= 2; // Subtract 2 from the health as long as they are colliding
                }
            }

            if (tempObject.getID() == ID.StrongEnemy) { // If our temporary object is equal to the enemy || The block of code only applies to the enemy object
                if(getBounds().intersects(tempObject.getBounds())){ // If our rectangle intersects with our temporary object (enemy)
                    HUD.HEALTH -= 5; // Subtract 2 from the health as long as they are colliding
                }
            }

        }
    }

    @Override
    public void tick() {
        x += velX; // If the xvelocity is 1 pixel then its going to set our player's s coordinate to 1 right
        y += velY; // If the yvelocity is 1 pixel then its going to set our player's s coordinate to 1 up

        x = Game.bound(x, 0, Game.WIDTH - 50);
        y = Game.bound(y, 0, Game.HEIGHT - 72);


        collision();
    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g; // Created a Graphics g method and cast it into a new variable og graphics 2d || Also Graphics 2D has a method  we want to use that graphcis doesn't
        if(id == ID.Player) g.setColor(Color.GREEN);
        g.fillRect(x,y,32,32);
    }
}
