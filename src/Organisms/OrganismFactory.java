package Organisms;

import Organisms.Animals.*;
import Organisms.Plants.*;
import main.World;

public class OrganismFactory {
    public static final int NUMBER_OF_ORGANISMS = 11;
    public void organismsList(int posX, int posY, int number, World currWorld) {
        switch (number) {
            case 0 -> currWorld.addOrganism(new Antelope(posX, posY, currWorld));
            case 1 -> currWorld.addOrganism(new Fox(posX, posY, currWorld));
            case 2 -> currWorld.addOrganism(new Sheep(posX, posY, currWorld));
            case 3 -> currWorld.addOrganism(new Turtle(posX, posY, currWorld));
            case 4 -> currWorld.addOrganism(new Wolf(posX, posY, currWorld));
            case 5 -> currWorld.addOrganism(new Dandelion(posX, posY, currWorld));
            case 6 -> currWorld.addOrganism(new Grass(posX, posY, currWorld));
            case 7 -> currWorld.addOrganism(new Guarana(posX, posY, currWorld));
            case 8 -> currWorld.addOrganism(new PineBorscht(posX, posY, currWorld));
            case 9 -> currWorld.addOrganism(new Wolfberries(posX, posY, currWorld));
            case 10 -> currWorld.addOrganism(new Human(posX, posY, currWorld));
        }
    }

    public String getOrganismName(int number) {
        switch (number) {
            case 0 -> {
                return "Antelope";
            }
            case 1 -> {
                return "Fox";
            }
            case 2 -> {
                return "Sheep";
            }
            case 3 -> {
                return "Turtle";
            }
            case 4 -> {
                return "Wolf";
            }
            case 5 -> {
                return "Dandelion";
            }
            case 6 -> {
                return "Grass";
            }
            case 7 -> {
                return "Guarana";
            }
            case 8 -> {
                return "PineBorscht";
            }
            case 9 -> {
                return "Wolfberries";
            }
            case 10 -> {
                return "Human";
            }
            default -> {
                return "Unknown";
            }
        }
    }

}
