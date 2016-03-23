package edu.kit.informatik.gameElements.board;

import edu.kit.informatik.gameElements.Stone;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Cell {
    private Stone stone;

    /**
     * Neue leere Zelle erstellen am anfang.
     */
    Cell() {
    }


    /**
     * Getter für
     * @return einen bestimmten Stein
     */
    public Stone getStone() {
        return this.stone;
    }

    /**
     * Überprüfen wir ob hier ein stein liegt
     * @return true wenn ja.
     */
    boolean hasStone() {
        return this.stone != null;
    }


    /**
     * Fügen wir den übergeben Stein der Zelle hinzu.
     * @param stone der Stein den wir übergeben wollen.
     */
    void addStone(Stone stone) {
        this.stone = stone;
    }

    @Override
    public String toString() {
        if (this.hasStone())
            return this.stone.toString();
        else
            return "# ";
    }
}
