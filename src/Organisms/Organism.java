package Organisms;
import main.World;
public abstract class Organism {
    public static final String images_path = "images/";
    protected int strength;
    protected int initiative;
    protected int posX;
    protected int posY;
    protected int age;
    protected String name;
    protected char prefix;
    protected World currWorld;

    public Organism(int strength, int initiative, int posX, int posY, char prefix, String name, World currWorld) {
        this.strength = strength;
        this.initiative = initiative;
        this.posX = posX;
        this.posY = posY;
        this.prefix = prefix;
        this.name = name;
        this.currWorld = currWorld;
    }

    public abstract void action();
    public abstract boolean collision(Organism invader);
    public abstract void makeMove(int[] newPos);

    public boolean hasBlocked(Organism invader) {
        int invaderStrength = invader.strength;
        int defenderStrength = this.strength;

        return invaderStrength < defenderStrength;
    }

    public void draw() {
        System.out.print(this.prefix);
    }


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void decrementAge() {
        this.age--;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAge() {
        return age;
    }

    public int getInitiative() {
        return initiative;
    }

    public void incrementAge() {
        this.age++;
    }

    public String getImagePath() {
        return IconsPath.valueOf(name.toUpperCase()).getPath();
    }


    public void setNewPosition(int newX, int newY) {
        currWorld.setOrganism(null, this.posX, this.posY);
        this.posX = newX;
        this.posY = newY;
        currWorld.setOrganism(this, this.posX, this.posY);
    }

}