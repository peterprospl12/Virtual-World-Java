package main;
import Organisms.Animals.*;
import Organisms.Organism;

import java.util.Vector;
public class World {
    private Organism[][] board;
    private int boardSizeX;
    private int boardSizeY;
    private Vector<Organism> organisms;
    private boolean humanAlive;
    private boolean gameSaved;

    World(int boardSizeX, int boardSizeY) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.board = new Organism[boardSizeY][boardSizeX];
        this.organisms = new Vector<Organism>();
        this.humanAlive = false;
        this.gameSaved = false;
    }
    private void sortOrganisms() {

    }

    public void performTurn() {

        this.addOrganism(new Human(15, 15, this));
        this.addOrganism(new Sheep(13, 1, this));
        this.addOrganism(new Turtle(5, 5, this));
        this.addOrganism(new Fox(15, 9, this));

        this.drawWorld();

        while (humanAlive) {
            this.sortOrganisms();
            for (int i = 0; i < organisms.size(); i++) {
                if (!humanAlive)
                {
                    break;
                }

                if (gameSaved) {
                    continue;
                }

                Organism currOrg = organisms.get(i);
                if (currOrg.getAge() > 0) {
                    currOrg.action();
                    currOrg.incrementAge();
                }
                else
                {
                    currOrg.incrementAge();
                }
            }
            gameSaved = false;
        }
        this.drawWorld();
    }




    public void drawWorld() {
        //system("cls");
        System.out.println("[ Piotr Sulewski 19254 ] ");

        for (int i = -1; i < boardSizeY+1; i++) {
            for (int j = -1; j < boardSizeX+1; j++) {
                if (j == -1) {
                    System.out.print("# ");
                } else if (j == boardSizeX) {
                    System.out.println("#");
                } else if (i == -1 || i == boardSizeY) {
                    System.out.print("# ");
                } else {
                    if (board[i][j] == null) {
                        System.out.print(". ");
                    } else {
                        board[i][j].draw();
                        System.out.print(" ");
                    }
                }
            }
        }
    }

    public void addOrganism(Organism organism) {
        if(organism instanceof Human) {
            humanAlive = true;
        }
        organisms.add(organism);
        board[organism.getPosY()][organism.getPosX()] = organism;
    }

    public int getBoardSizeX() {
        return boardSizeX;
    }

    public int getBoardSizeY() {
        return boardSizeY;
    }

    public Organism getOrganism(int x, int y) {
        return board[y][x];
    }

    public void setOrganism(Organism organism, int x, int y) {
        board[y][x] = organism;
    }
}