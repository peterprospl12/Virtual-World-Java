package Organisms.Animals;

import main.World;

public class Sheep extends Animal{
    private static final int SHEEP_STRENGTH = 4;
    private static final int SHEEP_INITIATIVE = 4;


    public Sheep(int posX, int posY, World currWorld) {
        super(SHEEP_STRENGTH, SHEEP_INITIATIVE, posX, posY, 'S', "Sheep", currWorld);
    }

    @Override
    public Sheep clone(int clonePosX, int clonePosY) {
        Sheep cloned = new Sheep(clonePosX, clonePosY, this.currWorld);
        cloned.decrementAge();
        return cloned;
    }
}
