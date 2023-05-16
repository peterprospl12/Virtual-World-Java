package Organisms.Animals;

import Organisms.Organism;
import main.GUIKeyListener;
import main.World;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Human extends Animal{

    private static final int HUMAN_STRENGTH = 5;
    private static final int HUMAN_INITIATIVE = 4;
    private int skillCD = 0;
    private boolean skillUsed = false;


    public Human(int posX, int posY, World currWorld) {
        super(HUMAN_STRENGTH, HUMAN_INITIATIVE, posX, posY, 'H', "Human", currWorld);
    }

    @Override
    public void makeMove(int[] newPos) throws IOException {
        final int[] newX = {newPos[0]};
        final int[] newY = {newPos[1]};

        currWorld.drawWorld();

        GUIKeyListener keyListener = new GUIKeyListener(currWorld.getGui().getFrame());
        currWorld.getGui().getFrame().addKeyListener(keyListener);
        currWorld.getGui().getFrame().requestFocus();
        while (currWorld.isHumanAlive()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            int keyCode = keyListener.getKeyCode();
            if (keyCode == KeyEvent.VK_UP) {
                if (newY[0] >= 1) {
                    newY[0]--;
                    break;
                }
            } else if (keyCode == KeyEvent.VK_DOWN) {
                if (newY[0] < currWorld.getBoardSizeY() - 1) {
                    newY[0]++;
                    break;
                }
            } else if (keyCode == KeyEvent.VK_LEFT) {
                if (newX[0] >= 1) {
                    newX[0]--;
                    break;
                }
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                if (newX[0] < currWorld.getBoardSizeX() - 1) {
                    newX[0]++;
                    break;
                }
            } else if (keyCode == KeyEvent.VK_SPACE) {
                if (skillCD == 0) {
                    skillUsed = true;
                    skillCD = 11;
                    currWorld.addToInfoStream( this.getOrganismInfo() + " used his special skill! \n");
                    break;
                }
            } else if (keyCode == KeyEvent.VK_ESCAPE) {
                currWorld.getGui().loadGameMenu();
                break;
            }

        }

        newPos[0] = newX[0];
        newPos[1] = newY[0];

        if (skillUsed) {
            skillCD--;
            if (skillCD < 6) {
                if (skillCD == 0) {
                    skillUsed = false;
                }
            }
            else {
                currWorld.addToInfoStream( this.getOrganismInfo() + " skill is activated for " + (skillCD-5) + " turns! \n");
            }
        }
    }


    @Override
    public boolean collision(Organism invader) {
        if (!skillUsed) {
            return false;
        }
        int currPosX = this.posX;
        int currPosY = this.posY;
        int PosToSkipX = -10;
        int PosToSkipY = -10;
        int PosToKillX, PosToKillY;
        int[][] moves = { {0,-1}, {0,1}, {-1,0}, {1,0}, {-1,-1}, {1,-1}, {-1,1}, {1,1} };


        for (int i = 0; i < 8; i++) {
            PosToKillX = currPosX + moves[i][0];
            PosToKillY = currPosY + moves[i][1];

            if (PosToKillX == PosToSkipX && PosToKillY == PosToSkipY) {
                continue;
            }

            if (PosToKillX >= 0 && PosToKillX < currWorld.getBoardSizeX() && PosToKillY >= 0 && PosToKillY < currWorld.getBoardSizeY()) {
                if (currWorld.getOrganism(PosToKillX, PosToKillY) != null) {
                    currWorld.addToInfoStream( this.getOrganismInfo() + " has killed " + currWorld.getOrganism(PosToKillX, PosToKillY).getOrganismInfo() + " with his skill! \n");
                    currWorld.removeOrganism(currWorld.getOrganism(PosToKillX, PosToKillY));
                }
            }

        }
        return true;
    }

    public int getCooldown() {
        return skillCD;
    }

    public boolean getSkillUsed() {
        return skillUsed;
    }

    public void setSkillUsed(boolean skillUsed) {
        this.skillUsed = skillUsed;
    }

    public void setCooldown(int skillCD) {
        this.skillCD = skillCD;
    }

    @Override
    public Human clone(int clonePosX, int clonePosY) {
        return null;
    }


}
