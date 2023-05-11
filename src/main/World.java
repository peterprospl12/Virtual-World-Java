package main;
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

}