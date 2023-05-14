package Organisms.Animals;

import Organisms.Organism;
import main.World;
import java.util.Random;
public abstract class Animal extends Organism {

    public Animal(int strength, int initiative, int posX, int posY, char prefix, String name, World currWorld){
        super(strength, initiative, posX, posY, prefix, name, currWorld);
    }
    @Override
    public void action() {
        int[] newPos = {posX, posY};
        // dodac infostreama

        this.makeMove(newPos);

        if(newPos[0] == posX && newPos[1] == posY){
            return;
        }

        if(currWorld.getOrganism(newPos[0], newPos[1]) != null){
           Organism defender = currWorld.getOrganism(newPos[0], newPos[1]);

           if(defender instanceof Animal){
               if(this.checkMultiply((Animal) defender)){
                   return;
               }
           }

           if(defender.collision(this)) {
               return;
           }

           if(defender.hasBlocked(this)) {
               //dodac info streama
               currWorld.removeOrganism(this);
           }
           else{
               //dodac infostreama
               currWorld.removeOrganism(currWorld.getOrganism(newPos[0], newPos[1]));
               this.setNewPosition(newPos[0], newPos[1]);
           }


        }
        else {
            //dodac infostreama
            this.setNewPosition(newPos[0], newPos[1]);
        }
    }

    public boolean checkMultiply(Animal defender){
        if(defender == null) {
            return false;
        }

        if(this.getClass().equals(defender.getClass())){
            int[] newPos = {posX, posY};
            int tryCounter = 0;
            Random rand = new Random();
            do {
                int rand_number = rand.nextInt(2) + 1;

                if(rand_number == 1){
                    this.makeMove(newPos);
                }
                else{
                    defender.makeMove(newPos);
                }
                tryCounter++;
            } while( currWorld.getOrganism(newPos[0], newPos[1]) != null && tryCounter < 40);

            if(tryCounter >= 40) {
                return false;
            }

            Animal kid = this.clone(newPos[0], newPos[1]);
            currWorld.addOrganism(kid);
            // infostream
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean collision(Organism invader) {
        return false;
    }

    @Override
    public void makeMove(int[] newPos) {
        int newX = posX;
        int newY = posY;

        int[][] moves = { {0,-1}, {0,1}, {-1,0}, {1,0}, {-1,-1}, {1,-1}, {-1,1}, {1,1} };
        Random rand = new Random();
        int tryCounter = 0;

        while(tryCounter < 40){
            tryCounter++;
            int rand_number = rand.nextInt(8);

            int dx = moves[rand_number][0];
            int dy = moves[rand_number][1];

            if (newX + dx >= 0 && newX + dx < currWorld.getBoardSizeX() &&
                    newY + dy >= 0 && newY + dy < currWorld.getBoardSizeY()) {
                newX += dx;
                newY += dy;

                if (newX != posX || newY != posY) {
                    newPos[0] = newX;
                    newPos[1] = newY;
                    break;
                }
            }
        }
    }



    abstract Animal clone(int clonePosX, int clonePosY);
}
