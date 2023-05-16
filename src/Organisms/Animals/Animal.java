package Organisms.Animals;

import Organisms.Organism;
import main.World;

import java.io.IOException;
import java.util.Random;
public abstract class Animal extends Organism {

    public Animal(int strength, int initiative, int posX, int posY, char prefix, String name, World currWorld){
        super(strength, initiative, posX, posY, prefix, name, currWorld);
    }
    @Override
    public void action() throws IOException {
        int[] newPos = {posX, posY};

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
               currWorld.addToInfoStream(defender.getOrganismInfo() + " blocked and killed " + this.getOrganismInfo() + "\n");
               currWorld.removeOrganism(this);
           }
           else{
               currWorld.addToInfoStream(this.getOrganismInfo() + " killed " + defender.getOrganismInfo() + " and moved to (" + newPos[0] + ", " + newPos[1] + ") \n");
               currWorld.removeOrganism(currWorld.getOrganism(newPos[0], newPos[1]));
               this.setNewPosition(newPos[0], newPos[1]);
           }


        }
        else {
            currWorld.addToInfoStream(this.getOrganismInfo() + " moved to (" + newPos[0] + ", " + newPos[1] + ") \n");
            this.setNewPosition(newPos[0], newPos[1]);
        }
    }

    public boolean checkMultiply(Animal defender) throws IOException {
        if(defender == null) {
            return false;
        }

        if(this.getClass().equals(defender.getClass())){
            int[] newPos = {0, 0};
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
                return true;
            }

            Animal kid = this.clone(newPos[0], newPos[1]);
            currWorld.addOrganism(kid);
            currWorld.addToInfoStream(kid.getOrganismInfo() + " was born on (" + newPos[0] + ", " + newPos[1] + ") \n");
            return true;
        }
        return false;
    }

    @Override
    public boolean collision(Organism invader) {
        return false;
    }

    @Override
    public void makeMove(int[] newPos) throws IOException {
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
