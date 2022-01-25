package main;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Enemy enemy;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;

        if(scoreKeep >= 100){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if(hud.getLevel() == 2){
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));
            }else if(hud.getLevel() == 3){
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));

            }
            else if(hud.getLevel() == 4){
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.FastEnemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));

            }
            else if(hud.getLevel() == 5){
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));
            }
            else if(hud.getLevel() == 6){
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.FastEnemy, handler));
                handler.addObject(new StrongEnemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.StrongEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.FastEnemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));
            }
            else if(hud.getLevel() == 7){
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.Enemy, handler));
                handler.addObject(new StrongEnemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.StrongEnemy, handler));
                handler.addObject(new StrongEnemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.StrongEnemy, handler));

            }
        }

    }
}
