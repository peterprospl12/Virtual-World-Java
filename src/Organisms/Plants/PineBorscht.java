package Organisms.Plants;
import Organisms.Animals.Animal;
import Organisms.Organism;
import main.World;
public class PineBorscht extends Plant {

    public PineBorscht(int posX, int posY, World currWorld) {
        super(10, PLANT_INITIATIVE, posX, posY, 'P', "PineBorscht", currWorld);
    }


    @Override
    public void action() {
        int[] currPos = {posX, posY};

        int posToKillX, posToKillY;

        int[][] moves = { {0,-1}, {0,1}, {-1,0}, {1,0}, {-1,-1}, {1,-1}, {-1,1}, {1,1} };

        for(int i=0; i<8; i++) {
            posToKillX = currPos[0] + moves[i][0];
            posToKillY = currPos[1] + moves[i][1];

            if (posToKillX >= 0 && posToKillX < currWorld.getBoardSizeX() && posToKillY >= 0 && posToKillY < currWorld.getBoardSizeY()) {
                Organism orgToKill = currWorld.getOrganism(posToKillX, posToKillY);

                if (orgToKill instanceof Animal) {
                    currWorld.removeOrganism(currWorld.getOrganism(posToKillX, posToKillY));
                }
            }

        }
        super.action();
    }

    @Override
    public boolean collision(Organism invader) {
        //infostream
        currWorld.removeOrganism(invader);
        return true;

    }

    @Override
    public PineBorscht clone(int clonePosX, int clonePosY) {
        PineBorscht cloned = new PineBorscht(clonePosX, clonePosY, currWorld);
        cloned.decrementAge();
        return cloned;
    }

}
