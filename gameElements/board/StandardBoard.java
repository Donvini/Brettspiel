package edu.kit.informatik.gameElements.board;

import edu.kit.informatik.exceptions.CellAccessException;
import edu.kit.informatik.gameElements.Player;
import edu.kit.informatik.gameElements.Stone;
import edu.kit.informatik.scanner.Scanner;
import edu.kit.informatik.scanner.StandardScanner;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class StandardBoard extends GameBoard {

    private Scanner scanner;
    /**
     * Konstruktor des StandardBoards
     * @param height höhe des boards
     * @param width weite des boards
     * @param players die spieler die hier herumtoben
     */
    public StandardBoard(int height, int width, Player[] players) {
        super(height, width, players);
        this.scanner = new StandardScanner(this);
    }

    /**
     * Dreist aus einer alten Musterlösung übernommen.
     * Return the cell with givem row index {@code x} and column index {@code y}.
     * @param row the row index
     * @param column the column index
     * @return the cell with given coordinates
     * @throws CellAccessException thrown if the requested cell is not existent on this board
     */

    public Cell getCell(int row, int column) throws CellAccessException {

        if (row < 0 || column < 0 || this.getHeight() - 1 < row
                || this.getWidth() - 1 < column) {
            throw new CellAccessException("a cell with the coordinates " + row
                    + "," + column + " does not exist.");
        }
        return this.getCells()[row][column];
    }


    /**
     * Der Place befehl
     * @param stone der Stein der platziert werden soll
     * @param row in genau dieser zeile
     * @param col und spalte
     * @throws CellAccessException falls der spieler einen illegalen zugriff plant.
     */
    public void placeStone(Stone stone, int row, int col) throws CellAccessException {
        if (row >= 0 && row <= getHeight() && col >= 0 && col <= getWidth())
            if (!this.getCell(row, col).hasStone())
                this.getCell(row, col).addStone(stone);
            else
                throw new CellAccessException("there is already a stone!");
        else
            throw new CellAccessException("you went out of the board!");
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
