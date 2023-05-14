package Organisms.Plants;

import main.World;

public class Wolfberries extends Plant{

    public Wolfberries(int posX, int posY, World currWorld) {
        super(99, PLANT_INITIATIVE, posX, posY, 'W', "Wolfberries", currWorld);
    }

    @Override
    public boolean collision(Organisms.Organism invader) {
        //infostream
        currWorld.removeOrganism(invader);
        return true;
    }

    @Override
    public Wolfberries clone(int clonePosX, int clonePosY) {
        Wolfberries cloned = new Wolfberries(clonePosX, clonePosY, currWorld);
        cloned.decrementAge();
        return cloned;
    }

}
