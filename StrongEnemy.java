package main;

import java.awt.*;

public class StrongEnemy extends GameObject{

    private Handler handler;

    public StrongEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 1;
        velY = 1;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y, 32, 32);
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;

        /*
         * Calculations:
         * Z = velY * -1
         *   = -5  * -1    <--- velY is negative meaning the enemy is moving up
         *   = 5           <--- velY will be positive meaning that the enemy is moving down
         *
         * Z = velX * -1
         *   = 5 * -1      <--- velX is positive meaning the enemy is moving to the right
         *   = -5          <--- velX is negative meaning the enemy is moving to the left
         */
        if(y <= 0 || y >= Game.HEIGHT - 88) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH-55) velX *= -1;


    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, 40 , 40);
    }
}
