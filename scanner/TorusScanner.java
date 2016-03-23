package edu.kit.informatik.scanner;


import edu.kit.informatik.gameElements.board.Cell;
import edu.kit.informatik.gameElements.board.TorusBoard;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class TorusScanner extends Scanner {
    /**
     * Konstruktor der Klasse
     * @param board Das Torusboard das wir überprüfen wollen.
     */
    public TorusScanner(TorusBoard board) {
        super(board);
    }

    @Override
    public Cell[] getRows(int x, int y) {
        return new Cell[0];
    }

    @Override
    public Cell[] getDiagonalUp(int x, int y) {
        return new Cell[0];
    }

    @Override
    public Cell[] getColumns(int x, int y) {
        return new Cell[0];
    }

    @Override
    public Cell[] getDiagonalDown(int x, int y) {
        return new Cell[0];
    }


    @Override
    public boolean checkForWin() {
        return false;
    }

}
