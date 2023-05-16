package Organisms.Plants;

import main.World;
import Organisms.Organism;
public class Guarana extends Plant{

    public Guarana(int posX, int posY, World currWorld) {
        super(0, PLANT_INITIATIVE, posX, posY, 'U', "Guarana", currWorld);
    }

    @Override
    public boolean collision(Organism invader) {
        invader.setStrength(invader.getStrength() + 3);
        currWorld.addToInfoStream(invader.getOrganismInfo() + " has eaten " + this.getOrganismInfo() + " and gained 3 strength points\n");
        return false;

    }

    @Override
    public Guarana clone(int clonePosX, int clonePosY) {
        Guarana cloned = new Guarana(clonePosX, clonePosY, currWorld);
        cloned.decrementAge();
        return cloned;
    }
}
