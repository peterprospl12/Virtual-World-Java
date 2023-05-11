package Organisms;
import main.World;
public abstract class Organism {
    int strength;
    int initiative;
    int posX;
    int posY;
    int age;
    String name;
    char prefix;
    World currWorld;

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
    public abstract void collision(Organism invader);
    public abstract void makeMove();
    public abstract void hasBlocked();

    public void draw() {
        System.out.println(this.prefix);
    }
}