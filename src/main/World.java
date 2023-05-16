package main;
import Organisms.Animals.*;
import Organisms.Organism;
import Organisms.Plants.*;

import java.io.*;
import java.util.Objects;
import java.util.Vector;
public class World {
    private Organism[][] board;
    private final GUI gui;
    private int boardSizeX;
    private int boardSizeY;
    private final Vector<Organism> organisms;
    private boolean humanAlive;
    private boolean gameSaved;
    private StringBuilder infoStream;

    World(int boardSizeX, int boardSizeY) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.board = new Organism[boardSizeY][boardSizeX];
        this.organisms = new Vector<Organism>();
        this.humanAlive = false;
        this.gameSaved = false;
        this.infoStream = new StringBuilder();
        this.gui = new GUI(this);
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



    public void changeBoardSize() {
        this.board = new Organism[boardSizeY][boardSizeX];
    }

    public void performTurn() throws IOException {

        this.drawWorld();



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

    public void saveWorld(String filename) {

        filename = filename + ".world";

        try {
            FileWriter saveFile = new FileWriter(filename);
            saveFile.write(boardSizeX + " " + boardSizeY + "\n");


            for (Organism organism : organisms) {
                saveFile.write(organism.getPrefix() + " ");
                if (organism instanceof Human) {
                    Human human = (Human) organism;
                    saveFile.write(human.getCooldown() + " " + human.getSkillUsed() + " ");
                }
                saveFile.write(organism.getStrength() + " " + organism.getPosX() + " " +
                        organism.getPosY() + " " + organism.getAge() + "\n");
            }

            saveFile.close();
            drawWorld();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loadWorld(File file) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            String[] size = line.split(" ");
            boardSizeX = Integer.parseInt(size[0]);
            boardSizeY = Integer.parseInt(size[1]);
            this.board = new Organism[boardSizeY][boardSizeX];
            organisms.clear();

            int strength;
            int posX;
            int posY;
            int age;
            int cooldown = 0;
            boolean skillUsed = false;
            int counter = 0;

            while ((line = reader.readLine()) != null) {
                String[] organism = line.split(" ");
                if(Objects.equals(organism[counter++], "H")) {
                    cooldown = Integer.parseInt(organism[counter++]);
                    skillUsed = Boolean.parseBoolean(organism[counter++]);

                }
                strength = Integer.parseInt(organism[counter++]);
                posX = Integer.parseInt(organism[counter++]);
                posY = Integer.parseInt(organism[counter++]);
                age = Integer.parseInt(organism[counter]);
                counter = 0;
                switch (organism[0]) {
                    case "H" -> {
                        Human human = new Human(posX, posY, this);
                        human.setCooldown(cooldown);
                        human.setSkillUsed(skillUsed);
                        human.setAge(age);
                        human.setStrength(strength);
                        this.addOrganism(human);
                    }
                    case "A" -> {
                        Antelope antelope = new Antelope(posX, posY, this);
                        antelope.setStrength(strength);
                        antelope.setAge(age);
                        this.addOrganism(antelope);
                    }
                    case "F" -> {
                        Fox fox = new Fox(posX, posY, this);
                        fox.setStrength(strength);
                        fox.setAge(age);
                        this.addOrganism(fox);
                    }
                    case "S" -> {
                        Sheep sheep = new Sheep(posX, posY, this);
                        sheep.setStrength(strength);
                        sheep.setAge(age);
                        this.addOrganism(sheep);
                    }
                    case "T" -> {
                        Turtle turtle = new Turtle(posX, posY, this);
                        turtle.setStrength(strength);
                        turtle.setAge(age);
                        this.addOrganism(turtle);
                    }
                    case "W" -> {
                        Wolf wolf = new Wolf(posX, posY, this);
                        wolf.setStrength(strength);
                        wolf.setAge(age);
                        this.addOrganism(wolf);
                    }
                    case "D" -> {
                        Dandelion dandelion = new Dandelion(posX, posY, this);
                        dandelion.setStrength(strength);
                        dandelion.setAge(age);
                        this.addOrganism(dandelion);
                    }
                    case "G" -> {
                        Grass grass = new Grass(posX, posY, this);
                        grass.setAge(age);
                        this.addOrganism(grass);
                    }
                    case "U" -> {
                        Guarana guarana = new Guarana(posX, posY, this);
                        guarana.setAge(age);
                        this.addOrganism(guarana);
                    }
                    case "P" -> {
                        PineBorscht pineBorscht = new PineBorscht(posX, posY, this);
                        pineBorscht.setAge(age);
                        this.addOrganism(pineBorscht);
                    }
                    case "B" -> {
                        Wolfberries wolfberries = new Wolfberries(posX, posY, this);
                        wolfberries.setAge(age);
                        this.addOrganism(wolfberries);
                    }

                }
            }
            this.gameSaved = true;
            drawWorld();


        } catch (FileNotFoundException e) {
            e.printStackTrace();

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

    public boolean isHumanAlive() {
        return humanAlive;
    }

    public GUI getGui() {
        return gui;
    }


    public void addToInfoStream(String info) {
        infoStream.append(info);
    }

    public void clearInfoStream() {
        infoStream = new StringBuilder();
    }

    public StringBuilder getInfoStream() {
        return infoStream;
    }



    public static <T> void swap(Vector<T> vector, int index1, int index2) {
        T tmp = vector.get(index1);
        vector.set(index1, vector.get(index2));
        vector.set(index2, tmp);
    }





    public void setHumanAlive(boolean humanAlive) {
        this.humanAlive = humanAlive;
    }

    public int getOrganismsSize() {
        return organisms.size();
    }

}