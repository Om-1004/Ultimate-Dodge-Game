package main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    //-----------------------------------------Tick Method------------------------------------------------------------
    public void tick(){ // Updates all the objects within the GameObject LinkedList
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    //------------------------------------------Render Method---------------------------------------------------------
    public void render(Graphics g) { // Renders all the game objects within the GameObject LinkedList
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }


    public void clearEnemys(){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if(tempObject.getID() != ID.Player) {
                object.clear();
                if(Game.gameState != Game.STATE.End)

                    addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
            }
        }
    }
    //--------------------------------------------Add Object Method----------------------------------------------------
    public void addObject(GameObject object){ // Method made to add objects to the GameObject LinkedList
        this.object.add(object);
    }

    //----------------------------------------Remove Object Method----------------------------------------------------
    public void removeObject(GameObject object){
        this.object.remove(object);
    }


}
