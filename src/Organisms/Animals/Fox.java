package Organisms.Animals;

import main.World;

import java.util.Random;

public class Fox extends Animal {
    private static final int FOX_STRENGTH = 3;
    private static final int FOX_INITIATIVE = 7;

    public Fox(int posX, int posY, World currWorld) {
        super(FOX_STRENGTH, FOX_INITIATIVE, posX, posY, 'F', "Fox", currWorld);
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
                    if (currWorld.getOrganism(newX, newY) == null) {
                        newPos[0] = newX;
                        newPos[1] = newY;
                        break;
                    }
                    else {
                        if (currWorld.getOrganism(newX, newY).getStrength() > this.strength) {
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Fox clone(int clonePosX, int clonePosY) {
        Fox cloned = new Fox(clonePosX, clonePosY, this.currWorld);
        cloned.decrementAge();
        return cloned;
    }
}
