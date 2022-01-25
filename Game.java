package main;

/*
Synchronized:
We have to use this keyword when we are dealing with threads

this:
Refers to the "this" class (this is talking about the Game Class)

Thread:
Allow me to make the game run on this
thread.join() allows us to kill/stop the thread
thread.start() allows us to start the thread


 */

//Named the class Game
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    //------------------------------------------------Variables---------------------------------------------------------
    public static final int WIDTH = 1375, HEIGHT = 725;

    private Boolean isRunning = false;

    //---------------------------------------------------Instances-----------------------------------------------------
    private Handler handler;
    private Thread thread;
    private Random r;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    public enum STATE{
        Menu,
        Objective,
        Game,
        End
    };

    public static STATE gameState = STATE.Menu;
    //--------------------------------------------------Constructor-----------------------------------------------------
    public Game(){
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler)); // Tells the computer to we are using keys and we want you to "listen" for when we press a key
        this.addMouseListener(menu);
        new Window(WIDTH, HEIGHT, "Ultimate Dodge", this);

        spawner = new Spawn(handler, hud);
        r = new Random();

        if(gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler)); //We set in the parameters to the same parameters within our GameObject construtor
            handler.addObject(new Enemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy, handler));

        }

    }

    // Made a main method

    //----------------------------------------------Start Method-------------------------------------------------------
    public synchronized void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    //----------------------------------------------End Method-------------------------------------------------------
    public synchronized void stop(){
        try {
            isRunning = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //---------------------------------------------Run and Game Loop-------------------------------------------------
    public void run() {
        this.requestFocus();
        int fps = 60; // Amount of time we want to call the tick and render method per second
        double timePerTick = 1000000000 / fps; // Maximum amount of time in nanoseconds we have to execute the tick and render method to achieve 60 FPS
        double delta = 0; // Amount of time we have to call the tick and render method
        long now;
        long lastTime = System.nanoTime(); // Last time equals to computer time
        long timer = 0; // Going to time until the computer hits 1 sec
        int ticks = 0; // Going to display how many times our tick and render methods were called


        while(isRunning) {
            now = System.nanoTime(); // Current time of computer
            // Fancy division so the time can be ± 1 second
            delta += (now - lastTime)/timePerTick; // Amount of time we called this code and divide that by the max time we can call the tick and render methods
            timer += now - lastTime;
            lastTime = now; // When our loop runs again we have the lasTime when line 53-64 was ran
            if(delta >= 1) { // have to tick and render or we wont get 60 FPS pr whatever number is
                tick();
                render();
                ticks++; // Tells us the computer ticked one more time
                delta --; // Once it ticks the delta decrements to relax
            }
            if(timer >= 1000000000) { // Checks if our timer has exceeded 1 second
                //System.out.println("FPS: " + ticks); // Displays how ticks occurred in that last second
                ticks = 0; // Reset the tick so we can get it over again
                timer = 0; // Reset the timer so we can get it over again
            }

        }

        stop();

    }

    //-----------------------------------------------Tick Method---------------------------------------------
    private void tick(){
        handler.tick();
        if(gameState == STATE.Game){
            hud.tick();
            spawner.tick();

            if(HUD.HEALTH <= 0){
                HUD.HEALTH = 100;
                gameState = STATE.End;
                handler.clearEnemys();

            }

        }else if(gameState == STATE.Menu || gameState == STATE.End){
            menu.tick();
        }

    }

    //--------------------------------------------------Render Method-----------------------------------------
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game){
            hud.render(g);
        } else if(gameState == STATE.Menu){
            menu.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Objective || gameState == STATE.End){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static int bound(int var, int min, int max) {
        if(var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }




    //------------------------------------------------Main Method-------------------------------------------
    public static void main(String[] args)
    {
        new Game();
    }


}
