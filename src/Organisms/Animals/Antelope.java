package Organisms.Animals;

import Organisms.Organism;
import main.World;

import java.util.Random;

public class Antelope extends Animal {

    private static final int ANTELOPE_STRENGTH = 4;
    private static final int ANTELOPE_INITIATIVE = 4;

    public Antelope(int posX, int posY, World currWorld) {
        super(ANTELOPE_STRENGTH, ANTELOPE_INITIATIVE, posX, posY, 'A', "Antelope", currWorld);
    }


    @Override
    public void makeMove(int[] newPos) {
        int newX = newPos[0];
        int newY = newPos[1];

        int[][] moves = {
            {-2, -2}, {-2, 0}, {-2, 2}, {0, -2}, {0, 2}, {2, -2}, {2, 0}, {2, 2},
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
        };

        Random rand = new Random();
        int tryCounter = 0;

        while(tryCounter < 40) {
            tryCounter++;
            int rand_number = rand.nextInt(16);

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


        if (newX != posX || newY != posY) {
            newPos[0] = newX;
            newPos[1] = newY;
        }
    }

    @Override
    public boolean collision(Organism attacker) {
        Random rand = new Random();
        int rand_number = rand.nextInt(2);
        int counter = 0;

        if(rand_number == 0) {
            int[] newPos = {this.posX, this.posY};
            int oldX, oldY;

            oldX = this.posX;
            oldY = this.posY;

            do {

                this.makeMove(newPos);
                counter ++;

                if(counter > 100) {
                    return false;
                }

            } while(currWorld.getOrganism(newPos[0], newPos[1]) != null);

            this.setNewPosition(newPos[0], newPos[1]);
            attacker.setNewPosition(oldX, oldY);

            currWorld.addToInfoStream(this.getName() + " escaped from " + attacker.getName() + " to " + this.getPosX() + " " + this.getPosY() + "\n");

            return true;

        }
        else{
            return false;
        }



    }



    @Override
    public Antelope clone(int clonePosX, int clonePosY) {
        Antelope cloned = new Antelope(clonePosX, clonePosY, this.currWorld);
        cloned.decrementAge();
        return cloned;
    }


}
