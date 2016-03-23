package edu.kit.informatik.scanner;

import edu.kit.informatik.exceptions.CellAccessException;
import edu.kit.informatik.gameElements.board.Cell;
import edu.kit.informatik.gameElements.board.StandardBoard;


/**
 * Diese Klasse enthält Teile aus einer alten Musterlösung.
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class StandardScanner extends Scanner {

    /**
     * Konstruktor der Klasse
     * @param board das Board auf dem die Analysen stattfinden sollen.
     */
    public StandardScanner(StandardBoard board) {
        super(board);
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
     * Die Methode um zu überprüfen ob jemand gewonnen hat.
     * @return true wenn jemand gewonnen hat.
     */
    @Override
    public boolean checkForWin() {
        for (int i = 0; i < getBoard().getHeight(); i++) {
            for (int j = 0; j < getBoard().getWidth(); j++) {
                /* scan to the right */
                Cell[] rCells = getRows(i, j);
                if (checkForConnectedFour(rCells))
                    if (haveInCommon(rCells[0], rCells[1], rCells[2], rCells[3]))
                        return true;

                /* scan to bottom */
                Cell[] bCells = getColumns(i, j);
                if (checkForConnectedFour(bCells))
                    if (haveInCommon(bCells[0], bCells[1], bCells[2], bCells[3]))
                        return true;


                /* scan diagonal down */
                Cell[] ddCells = getDiagonalDown(i, j);
                if (checkForConnectedFour(ddCells))
                    if (haveInCommon(ddCells[0], ddCells[1], ddCells[2], ddCells[3]))
                        return true;


                /* scan diagonal up */
                Cell[] duCells = getDiagonalUp(i, j);
                if (checkForConnectedFour(duCells))
                    if (haveInCommon(duCells[0], duCells[1], duCells[2], duCells[3]))
                        return true;
            }
        }
        return false;
    }

}
