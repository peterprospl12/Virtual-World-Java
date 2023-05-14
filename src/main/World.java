package main;
import Organisms.Animals.*;
import Organisms.Organism;
import Organisms.Plants.Dandelion;
import Organisms.Plants.Grass;
import Organisms.Plants.Guarana;
import Organisms.Plants.PineBorscht;

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
        this.createWindow();
    }
    private void sortOrganisms() {
        int size = organisms.size();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (organisms.get(j).getInitiative() < organisms.get(j + 1).getInitiative()) {
                    swap(organisms, j, j + 1);
                }
			else if (organisms.get(j).getInitiative() == organisms.get(j + 1).getInitiative()) {
                    if (organisms.get(j).getAge() < organisms.get(j + 1).getAge()) {
                        swap(organisms, j, j + 1);
                    }
                }
            }
        }
    }

    private void createWindow() {
        GUI gui = new GUI(this);
    }

    public void changeBoardSize() {
        this.board = new Organism[boardSizeY][boardSizeX];
    }

    public boolean performTurn() {



        this.drawWorld();

        if (humanAlive) {
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
            this.drawWorld();
            return true;
        }
        return false;

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

    public void removeOrganism(Organism organism) {
        if(organism instanceof Human) {
            humanAlive = false;
        }

        if (organisms.contains(organism)) {
            organisms.remove(organism);
            board[organism.getPosY()][organism.getPosX()] = null;
        }
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

    public void setBoardSizeX(int boardSizeX) {
        this.boardSizeX = boardSizeX;
    }

    public void setBoardSizeY(int boardSizeY) {
        this.boardSizeY = boardSizeY;
    }
    public void clearOrganisms() {
        this.organisms.clear();
    }
    public static <T> void swap(Vector<T> vector, int index1, int index2) {
        T tmp = vector.get(index1);
        vector.set(index1, vector.get(index2));
        vector.set(index2, tmp);
    }

}