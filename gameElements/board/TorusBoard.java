package edu.kit.informatik.gameElements.board;

import edu.kit.informatik.exceptions.CellAccessException;
import edu.kit.informatik.gameElements.Player;
import edu.kit.informatik.gameElements.Stone;
import edu.kit.informatik.scanner.Scanner;

import edu.kit.informatik.scanner.TorusScanner;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 **/
public class TorusBoard extends GameBoard {

    private Scanner scanner;
    /**
     * Konstruktor des Torusboards
     * @param rownumber anzahl der reihen
     * @param columnnumber anzahl der spalten
     * @param players die spieler die hier spielen
     */
    public TorusBoard(int rownumber, int columnnumber, Player[] players) {
        super(rownumber, columnnumber, players);
        this.scanner = new TorusScanner(this);
    }

    /**
     * Angepasst für Torus
     * @param row in dieser Zeile
     * @param column und dieser Spalte soll unsere Zelle sein
     * @return die Celle mit der wir arbeiten wollen.
     * @throws CellAccessException eigentlich nie weil es hier keine probleme gibt.
     */
    @Override
    public Cell getCell(int row, int column) throws CellAccessException {
        if (row >= 0 && row <= 5 && column <= 5 && column >= 0)
            return this.getCells()[row][column];
        if (row > 5) {
            if (column > 5)
                return this.getCells()[row % 6][column % 6];
            if (column < 0)
                return this.getCells()[row % 6][5 - (Math.abs(column + 1) % 6)];
            else
                return this.getCells()[row % 6][column];
        }
        if (row < 0) {
            if (column > 5)
                return this.getCells()[5 - (Math.abs(row + 1) % 6)][column % 6];
            if (column < 0)
                return this.getCells()[5 - (Math.abs(row + 1) % 6)][5 - (Math.abs(column + 1) % 6)];
            else
                return this.getCells()[5 - (Math.abs(row + 1) % 6)][column];
        }
        else
            throw new CellAccessException("square root of 2 seems to be rational.");
    }

    @Override
    public void placeStone(Stone stone, int row, int col) throws CellAccessException {
        if (this.getCell(row, col).hasStone())
            throw new CellAccessException("there is already a Stone!");
        this.getCell(row, col).addStone(stone);
        if (getWinner() != null)
            setFinished(true);
    }
    /**
     * Getter für den Scanner
     * @return der Scanner
     */
    private Scanner getScanner() {
        return scanner;
    }

    /**
     * Returns the winner of the current game, if existent.
     * @return the winner or null if not existent
     */
    public Player getWinner() {
        return this.getScanner().getWinner();
    }
}
