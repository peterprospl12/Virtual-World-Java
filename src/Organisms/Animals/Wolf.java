package Organisms.Animals;

import main.World;

public class Wolf extends Animal {
    private static final int WOLF_STRENGTH = 9;
    private static final int WOLF_INITIATIVE = 5;

    public Wolf(int posX, int posY, World currWorld) {
        super(WOLF_STRENGTH, WOLF_INITIATIVE, posX, posY, 'W', "Wolf", currWorld);
    }

    @Override
    public Wolf clone(int clonePosX, int clonePosY) {
        Wolf cloned = new Wolf(clonePosX, clonePosY, this.currWorld);
        cloned.decrementAge();
        return cloned;
    }
}
