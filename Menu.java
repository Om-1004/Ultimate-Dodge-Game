package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;



    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX(); // Stores the x position when the mouse is clicked
        int my = e.getY(); // Stores the y position when the mouse is clicked
        if(hover(mx, my, 550, 200, 300, 64)){
            game.gameState = Game.STATE.Game;
            handler.clearEnemys();
            handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); //We set in the parameters to the same parameters within our GameObject construtor
            handler.addObject(new Enemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy, handler));
        }


        if(game.gameState == Game.STATE.Menu) {
            if(hover(mx, my, 550, 350, 300, 64)){
                game.gameState = Game.STATE.Objective;
            }

            if (hover(mx, my, 550, 500, 300, 64)){
                System.exit(1);
            }
        }

        if(game.gameState == Game.STATE.End){
            if(hover(mx, my, 600, 425, 150, 50)){
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.score(0);
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); //We set in the parameters to the same parameters within our GameObject construtor
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy, handler));
            }
        }

        if(game.gameState == Game.STATE.Objective){
            if(hover(mx, my, 9, 11, 60, 22)){
                game.gameState = Game.STATE.Menu;
            }
        }


        }

    public void mouseReleased(MouseEvent e){

    }

    private boolean hover(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){ // Checks if the x position of the mouse is greater than the top left corner and if the x position of the mouse is less than the top right corner
            if(my > y && my < y + height){ // Checks if the y position of the mouse is greater than the height of a certain box
                // and if the y position is less than the lowest height of a certain code, execute the following code
                return true;
            } else return false;
        }else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 70);
            Font fnt3 = new Font("arial", 1, 30);

            g.setFont(fnt2);
            g.setColor(Color.GREEN);
            g.drawString("Ultimate Dodge" , 450, 80);

            g.setColor(Color.white);
            g.setFont(fnt);
            g.drawRect(550, 200, 300, 64);
            g.drawString("Play", 650, 250);

            g.setFont(fnt);
            g.drawRect(550, 350, 300, 64);
            g.drawString("Objective", 585, 400);

            g.setFont(fnt);
            g.drawRect(550, 500, 300, 64);
            g.drawString("Exit", 650, 550);

        } else if(game.gameState ==  Game.STATE.Objective){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 25);
            Font fnt3 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Objective", 585, 100);

            g.setFont(fnt3);
            g.drawString("Use WASD Keys To Dodge The Enemies", 400, 300);

            g.setFont(fnt2);
            g.drawString("Back",10,30);
            //g.drawRect(9, 11, 60, 22);

        }else if(game.gameState ==  Game.STATE.End){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 25);
            Font fnt3 = new Font("arial", 1, 30);
            Font fnt4 = new Font("arial", 1, 100);

            g.setFont(fnt4);
            g.setColor(Color.RED);
            g.drawString("Game Over", 425, 350);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.setFont(fnt3);
            g.drawString("Your Score Is: " + hud.getScore(), 550, 400);

            g.setFont(fnt2);
            g.drawRect(600, 425, 150, 50);
            g.drawString("Try Again", 618, 457);

        }
    }
}
