package edu.kit.informatik.gameElements.board;

import edu.kit.informatik.exceptions.CellAccessException;
import edu.kit.informatik.gameElements.Player;
import edu.kit.informatik.gameElements.Stone;



/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 * {@link Player Spieler} platzieren ihre {@link Stone steine} in die {@link Cell zellen}.
 */

public abstract class GameBoard {
    // Alle Eigenschaften die unser Spielfeld so hat.
    private final Cell[][] cells;
    private final Player[] players;
    private boolean finished = false;
    private final int height;
    private final int width;
    private final Bag bag;
    private int cActivePlayerIndex = 0;

    /**
     * Konstruktor der Klasse.
     * @param rownumber Anzahl der Reihen.
     * @param columnnumber und der Spalten.
     * @param players außerdem die Spieler die hier spielen dürfen.
     */
    GameBoard(int rownumber, int columnnumber, Player[] players) {
        this.height = rownumber;
        this.width = columnnumber;
        this.cells = new Cell[rownumber][columnnumber];
        this.players = players;
        this.bag = new Bag();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.getCells()[i][j] = new Cell();
            }
        }

    }

    /**
     * So aus einer alten Musterlösung entnommen.
     */
    public void changeCurrentActivePlayer() {
        this.cActivePlayerIndex = (this.cActivePlayerIndex + 1) % this.players.length;
    }

    /**
     * Methode um zu überorüfen ob das Spiel bereits entschieden ist.
     * @return finished wenn ja.
     */

    public boolean isFinished() {
        return finished;
    }

    /**
     * Methode um den Spielstatus auf beendet zu setzen.
     * @param finished wird nur mit True aufgerufen.
     */
    void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Getter für den Beutel
     * @return bag halt der Beutel mit den Steinen.
     */

    public Bag getBag() {
        return bag;
    }


    /**
     * Ermittelt den Gewinner
     * @return der Gewinner.
     */
    public abstract Player getWinner();

    /**
     * Getter für eine bestimmte Zelle
     * @param row in dieser Zeile
     * @param column und dieser Spalte soll unsere Zelle sein
     * @return die wir hier returnen.
     * @throws CellAccessException Oder eine Exception werfen falls der Nutzer quatsch eingibt.
     */
    public abstract Cell getCell(int row, int column) throws CellAccessException;

    /**
     * Getter für die Zellen
     * @return Die Zellen.
     */
    Cell[][] getCells() {
        return this.cells;
    }

    /**
     * Getter für die Weite
     * @return Die Weite
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter für die Höhe.
     * @return Die Höhe
     */
    public int getHeight() {
        return height;
    }



    /** Aus alter Musterlösung übernommen.
     * Returns the player who is currently active.
     * @return the active player
     */
    public Player getCurrentActivePlayer() {
        return this.players[this.cActivePlayerIndex];
    }

    /**
     * Methode um eine Zeile auszugeben.
     * @param row genau die Zeile wollen wir.
     * @return und hier kommt der schöne String zurück wie wir ihn wollen.
     */
    public String rowPrint(int row) {
        if (row < 0 || row > 5)
            throw new NumberFormatException("row must be between 0 and 5");
        String rowContent = "";
        for (int i = 0; i < width; i++) {
            if (!cells[row][i].hasStone())
                rowContent += cells[row][i].toString();
            else
                rowContent += cells[row][i].getStone().getNumber() + " ";
        }
        rowContent = rowContent.substring(0, rowContent.length() - 1);
        return rowContent;
    }

    /**
     * Methode um eine Zeile auszugeben.
     * @param col genau die Zeile wollen wir.
     * @return und hier kommt der schöne String zurück wie wir ihn wollen.
     */
    public String colPrint(int col) {
        if (col < 0 || col > 5)
            throw new NumberFormatException("col must be between 0 and 5");
        String colContent = "";
        for (int i = 0; i < width; i++) {
            if (!cells[i][col].hasStone())
                colContent += cells[i][col].toString();
            else
                colContent += cells[i][col].getStone().getNumber() + " ";
        }
        colContent = colContent.substring(0, colContent.length() - 1);
        return colContent;

    }

    /**
     * Methode um einen Stein zu platzieren
     * @param stone der Stein der platziert werden soll
     * @param row in genau dieser zeile
     * @param col und spalte
     * @throws CellAccessException oder auch nciht wenn der benutzer quatsch eingibt.
     */
    public void placeStone(Stone stone, int row, int col) throws CellAccessException {
    }

}
