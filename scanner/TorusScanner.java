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
    public boolean checkForWin() {
        for (int i = 0; i < getBoard().getHeight() * 2; i++) {
            for (int j = 0; j < getBoard().getWidth() * 2; j++) {
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
