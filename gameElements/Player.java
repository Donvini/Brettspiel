package edu.kit.informatik.gameElements;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 * Diese Klasse enthält Teile aus einer alten Musterlösung.
 */
public class Player {
    private final int id;

    /**
     * Konstruktor
     * @param id die Player ID anhand derer man den Spieler identifizieren kann.
     */
    public Player(int id) {
        this.id = id;
    }

    /**
     * Returns the identifier for this player.
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
