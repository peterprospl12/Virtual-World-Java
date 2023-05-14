package Organisms.Animals;

import main.World;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Human extends Animal{

    private static final int HUMAN_STRENGTH = 5;
    private static final int HUMAN_INITIATIVE = 4;

    private static final int LEFT_ARROW = 37;
    private static final int RIGHT_ARROW = 39;
    private static final int UP_ARROW = 38;
    private static final int DOWN_ARROW = 40;

    public Human(int posX, int posY, World currWorld) {
        super(HUMAN_STRENGTH, HUMAN_INITIATIVE, posX, posY, 'H', "Human", currWorld);
    }

    @Override
    public void makeMove(int[] newPos) {
        int newX = newPos[0];
        int newY = newPos[1];

        currWorld.drawWorld();
        int key = 0;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Use arrow keys to move");
            key = 0;

            switch ((key = scanner.next().charAt(0))) {
                case 'w' -> {
                    if (newY >= 1) {
                        newY--;
                    }
                }
                case 's' -> {
                    if (newY < currWorld.getBoardSizeY() - 1) {
                        newY++;
                    }
                }
                case 'a' -> {
                    if (newX >= 1) {
                        newX--;
                    }
                }
                case 'd' -> {
                    if (newX < currWorld.getBoardSizeX() - 1) {
                        newX++;
                    }
                }

            }
            if(newX != posX || newY != posY) {
                newPos[0] = newX;
                newPos[1] = newY;
                break;
            }
        }
    }



    @Override
    public Human clone(int clonePosX, int clonePosY) {
        return null;
    }
}
