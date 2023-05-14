package Organisms;

public enum IconsPath {
    ANTELOPE("images/antelope.png"),
    FOX("images/fox.png"),
    WOLF("images/wolf.png"),
    SHEEP("images/sheep.png"),
    TURTLE("images/turtle.png"),
    GRASS("images/grass.png"),
    DANDELION("images/dandelion.png"),
    GUARANA("images/guarana.png"),
    PINEBORSCHT("images/pineborscht.png"),
    WOLFBERRIES("images/wolfberries.png"),
    HUMAN("images/human.png"),
    EMPTY("images/empty.png");

    private String name;

    IconsPath(String name) {
        this.name = name;
    }

    public String getPath() {
        return name;
    }
}
