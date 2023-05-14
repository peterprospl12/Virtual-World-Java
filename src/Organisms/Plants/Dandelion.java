package Organisms.Plants;

import main.World;

public class Dandelion extends Plant{

    private static final int CHANCES_TO_MULTIPLY = 3;

    public Dandelion(int posX, int posY, World currWorld) {
        super(0, PLANT_INITIATIVE, posX, posY, 'D', "Dandelion", currWorld);
    }

    @Override
    public void action() {
        for(int i = 0; i < CHANCES_TO_MULTIPLY; i++) {
            super.action();
        }
    }

    @Override
    public Dandelion clone(int clonePosX, int clonePosY) {
        Dandelion cloned = new Dandelion(clonePosX, clonePosY, this.currWorld);
        cloned.decrementAge();
        return cloned;
    }

}
