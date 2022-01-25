package main;

import java.awt.*;

public class HUD {

    public static int HEALTH = 150;
    private int score = 0;
    private int level = 1;



    public void tick(){
        HEALTH = Game.bound(HEALTH, 0, 150);
        score++; // 60 score per second
    }

    public void render(Graphics g){
        g.setColor(Color.gray); // BG of Health Bar
        g.fillRect(15, 15, 150, 25);
        g.setColor(Color.GREEN);
        g.fillRect(15, 15, HEALTH, 25);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 150, 25);

        g.drawString("Score: " + score, 1250, 15);
        g.drawString("Level: " + level, 1250, 30);
    }

    public void score(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }
}
