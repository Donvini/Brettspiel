package edu.kit.informatik.gameElements.board;

import edu.kit.informatik.exceptions.StoneAccessException;
import edu.kit.informatik.exceptions.StoneIsAlreadyUsedException;
import edu.kit.informatik.gameElements.Stone;

import java.util.ArrayList;

/**
 * Diese Klasse repräsentiert den Beutel in dem die Steine für das Spiel sind.
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class Bag {
    // Nachfolgend alle Eigenschaften.
    private static String[] colors = {"black", "white"};
    private static String[] shapes = {"eckig", "zylinderförmig"};
    private static String[] sizes = {"small", "big"};
    private static String[] densities = {"empty", "massive"};
    // Die Datenstruktur für die Steine.
    private ArrayList<Stone> stones = new ArrayList<>();


    /**
     * Konstruktor und Initialisierung des Beutels mit allen Steinen
     */
    Bag() {
        int i = 0;
        while (i < 16) {
            for (String color : colors) {
                for (String shape : shapes) {
                    for (String size : sizes) {
                        for (String density : densities) {
                            this.stones.add(i, new Stone(i, color, shape, size, density));
                            i++;
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @return Ein String wie er in der Ausgabe des bag befehls gefordert wird.
     */
    public String print() {
        String result = "";
        for (Stone stone : stones) {
            if (stone != null)
                result += stone.getNumber() + " ";
        }
        return result;
    }

    /**
     * Getter für
     * @return die Steine
     */
    public ArrayList<Stone> getStones() {
        return stones;
    }

    /**
     * Methode um einen bestimmten Stein zu holen.
     * @param number hiermit erkennen wir unseren Stein.
     * @return der Stein den wir wollten.
     * @throws StoneAccessException falls wir eine ungültige Zahl eingeben
     */
    public Stone getStoneByNumber(int number) throws StoneAccessException {
        if (!(number >= 0 && number <= 15))
            throw new StoneAccessException("number must be between 0 and 15!");

        for (Stone stone : stones) {
            if (stone == null)
                continue;
            if (stone.getNumber() == number)
                return stone;
        }
        throw new StoneIsAlreadyUsedException("stone is already used!");
    }
}
