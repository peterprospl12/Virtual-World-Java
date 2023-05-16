package Organisms.Plants;
import Organisms.Organism;
import main.World;

import java.util.Random;

public abstract class Plant extends Organism{
    protected static final int PLANT_INITIATIVE = 0;

    public Plant(int strength, int initiative, int posX, int posY, char prefix, String name, World currWorld) {
        super(strength, initiative, posX, posY, prefix, name, currWorld);
    }


    @Override
    public boolean collision(Organism invader) {
        return false;
    }

    @Override
    public void action() {
        Random rand = new Random();
        int[][] moves = { {0,-1}, {0,1}, {-1,0}, {1,0}, {-1,-1}, {1,-1}, {-1,1}, {1,1} };
        int rand_number = rand.nextInt(8);

        if(rand_number == 2) {
            int[] newPos = {posX, posY};

            int tryCounter = 0;

            do {
                this.makeMove(newPos);
                tryCounter++;
            } while( currWorld.getOrganism(newPos[0], newPos[1]) != null && tryCounter < 40);

            if(tryCounter >= 40) {
                return;
            }

            Plant kid = this.clone(newPos[0], newPos[1]);
            currWorld.addOrganism(kid);
            currWorld.addToInfoStream(kid.getOrganismInfo() + " has multiplied on (" + newPos[0] + ", " + newPos[1] + ")\n");

        }

    }

    @Override
    public void makeMove(int[] newPos) {
        int newX = newPos[0];
        int newY = newPos[1];

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


    abstract Plant clone(int clonePosX, int clonePosY);
}
