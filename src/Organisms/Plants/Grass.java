package Organisms.Plants;

import main.World;

public class Grass extends Plant{

        public Grass(int posX, int posY, World currWorld) {
            super(0, PLANT_INITIATIVE, posX, posY, 'G', "Grass", currWorld);
        }

        @Override
        public Grass clone(int clonePosX, int clonePosY) {
            Grass cloned = new Grass(clonePosX, clonePosY, currWorld);
            cloned.decrementAge();
            return cloned;
        }
}
