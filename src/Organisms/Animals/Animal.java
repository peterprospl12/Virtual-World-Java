package Organisms.Animals;

import Organisms.Organism;
import main.World;
public abstract class Animal extends Organism {

    public Animal(int strength, int initiative, int posX, int posY, char prefix, String name, World currWorld){
        super(strength, initiative, posX, posY, prefix, name, currWorld);
    }
    @Override
    public void action() {
        int newX, newY;
        // dodac infostreama


    }

    @Override
    public void collision(Organism invader) {

    }

    public boolean checkMultiply(Animal defender){

    }

    abstract Animal clone(int clonePosX, int clonePosY);
}
