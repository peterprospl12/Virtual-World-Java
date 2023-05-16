package Organisms.Animals;

import Organisms.Organism;
import main.World;

import java.util.Random;

public class Turtle extends Animal{

    private static final int TURTLE_STRENGTH = 2;
    private static final int TURTLE_INITIATIVE = 1;

    public Turtle(int posX, int posY, World currWorld) {
        super(TURTLE_STRENGTH, TURTLE_INITIATIVE, posX, posY, 'T', "Turtle", currWorld);
    }

    @Override
    public void makeMove(int[] newPos) {
        int newX = newPos[0];
        int newY = newPos[1];

        Random rand = new Random();
        int rand_number = rand.nextInt(4);

        if(rand_number != 1) {
            return;
        }

        super.makeMove(newPos);
    }

    @Override
    public boolean collision(Organism attacker) {
        if(attacker.getStrength() < 5) {
            currWorld.addToInfoStream(this.getOrganismInfo() + " has defended itself from " + attacker.getOrganismInfo() + " attack! \n");
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Turtle clone(int clonePosX, int clonePosY) {
        Turtle cloned = new Turtle(clonePosX, clonePosY, this.currWorld);
        cloned.decrementAge();
        return cloned;
    }
}
