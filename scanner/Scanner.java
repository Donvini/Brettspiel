package edu.kit.informatik.scanner;

import edu.kit.informatik.exceptions.CellAccessException;
import edu.kit.informatik.gameElements.Player;
import edu.kit.informatik.gameElements.Stone;
import edu.kit.informatik.gameElements.board.Cell;
import edu.kit.informatik.gameElements.board.GameBoard;

/**
 * Diese Klasse wird genutzt um das Spiel auf einen Sieg zu überprüfen.
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public abstract class Scanner {
    private final GameBoard board;

    /**
     * Konstruktor für den Scanner
     * @param board auf diesem Board sollen alle scans ausgeführt werden.
     */
    Scanner(GameBoard board) {
        this.board = board;
    }

    /**
     * Die Methode um den Gewinner zu ermitteln.
     * @return der Gewinner.
     */
    public Player getWinner() {
        if (checkForWin())
            return this.board.getCurrentActivePlayer();
        else
            return null;
    }

    /**
     *
     * @return ob es einen gewinner gibt.
     */
    public abstract boolean checkForWin();
    /**
     * Hiermit holen wir uns die horizontal die Zellen rechts vom Start
     * @param x Start
     * @param y Ende
     * @return Zellen dazwischen
     */
    public Cell[] getRows(int x, int y) {
        Cell[] cells = new Cell[4];
        for (int i = 0; i < 4; i++) {
            try {
                cells[i] = getBoard().getCell(x, y + i);
            } catch (CellAccessException e) {
                cells[i] = null;
            }
        }

        return cells;
    }

    /**
     * Hiermit holen wir uns vertikal die zellen von oben nach unten
     * @param x start
     * @param y ende
     * @return zellen dazwischen
     */
    public Cell[] getColumns(int x, int y) {
        Cell[] cells = new Cell[4];
        for (int i = 0; i < 4; i++) {
            try {
                cells[i] = getBoard().getCell(x + i, y);
            } catch (CellAccessException e) {
                cells[i] = null;
            }
        }

        return cells;
    }

    /**
     * Hiermit holen wir alle Zellen von oben links nach unten rechts.
     * @param x start
     * @param y ziel
     * @return die zellen dazwischen
     */
    public Cell[] getDiagonalDown(int x, int y) {
        Cell[] cells = new Cell[4];
        for (int i = 0; i < 4; i++) {
            try {
                cells[i] = getBoard().getCell(x + i, y + i);
            } catch (CellAccessException e) {
                cells[i] = null;
            }
        }

        return cells;
    }
    /**
     * Hiermit holen wir alle Zellen von oben rechts nach unten links.
     * @param x start
     * @param y ziel
     * @return die zellen dazwischen
     */
    public Cell[] getDiagonalUp(int x, int y) {
        Cell[] cells = new Cell[4];
        for (int i = 0; i < 4; i++) {
            try {
                cells[i] = getBoard().getCell(x - i, y + i);
            } catch (CellAccessException e) {
                cells[i] = null;
            }
        }

        return cells;
    }

    /**
     * Getter für das Board
     * @return das Board
     */
    public GameBoard getBoard() {
        return this.board;
    }

    /**
     * Damit schauen wir ob die
     * @param a Zelle a
     * @param b Zelle b
     * @param c Zelle c
     * @param d Zelle d
     * @return eine gemeinsame Eigenschaft haben.
     */
    boolean haveInCommon(Cell a, Cell b, Cell c, Cell d) {
        return Stone.haveInCommon(a.getStone(), b.getStone(), c.getStone(), d.getStone());
    }

    /**
     * Damit überprüfen wir ob überhaupt erst mal 4 nachbarn inhalte haben, wenn nicht
     * müssen wir da auch nichts vergleichen.
     * @param cells die zellen die wir untersuchen
     * @return wahr wenn 4 zellen alle inhalt haben.
     */
    boolean checkForConnectedFour(Cell[] cells) {
        for (Cell cell : cells) {
            if (cell == null || cell.getStone() == null) {
                return false;
            }

        }
        return true;
    }
}
