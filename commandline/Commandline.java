package edu.kit.informatik.commandline;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.CellAccessException;
import edu.kit.informatik.exceptions.SemanticsException;
import edu.kit.informatik.exceptions.StoneAccessException;
import edu.kit.informatik.exceptions.UserInputException;
import edu.kit.informatik.gameElements.Stone;
import edu.kit.informatik.gameElements.board.GameBoard;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class Commandline {

    /**
     * Die akzeptierten Befehle
     */
    private static final String QUIT = "quit";
    private static final String BAG = "bag";
    private static final String PLACE = "place";
    private static final String SELECT = "select";
    private static final String ROWPRINT = "rowprint";
    private static final String COLPRINT = "colprint";

    // Der Stein der vom Spieler ausgewählt wird.
    private static Stone theChosenOne;
    // Wird genutzt um den Spielfluss zu regulieren.
    private static boolean youMaySelect = true;
    // Hier werden die Runden mitgezählt.
    private static int roundcounter = -1;

    // Leerer Konstruktor um Instantiierung zu vermeiden.
    private Commandline() { }

    /**
     * Die Methode die das Spiel zum laufen bringt.
     * @param board das Spielbrett auf dem alles passiert.
     */
    public static void startGame(GameBoard board) {
        while (true) {
            try {
                // Hier werden die Befehle eingelesen.
                String commands = Terminal.readLine().toLowerCase();
                // Kein Befehl wird nicht akzeptiert.
                if (commands.length() == 0)
                    throw new UserInputException("you have to enter an argument.");
                String[] parts = commands.split("\\s", 2);
                // Hier wird nun unterschieden was für ein Befehl vom Nutzer eingegeben wurde.
                switch (parts[0]) {
                    case QUIT:
                        if (parts.length != 1)
                            throw new UserInputException("quit takes no argument!");
                        quit();
                        break;
                    case BAG:
                        if (parts.length != 1)
                            throw new UserInputException("bag takes no argument!");
                        bag(board);
                        break;
                    case PLACE:
                        if (parts.length != 2)
                            throw new UserInputException("place needs two integers sperated by ; as position!");
                        place(board, parts[1]);
                        break;
                    case SELECT:
                        if (parts.length != 2)
                            throw new UserInputException("select takes exactly one integer between 0 and 15!");
                        select(board, parts[1]);
                        break;
                    case ROWPRINT:
                        if (parts.length != 2)
                            throw new UserInputException("rowprint takes exactly one integer between 0 and 5!");
                        rowPrint(board, parts[1]);
                        break;
                    case COLPRINT:
                        if (parts.length != 2)
                            throw new UserInputException("colprint takes exactly one integer between 0 and 5!");
                        colPrint(board, parts[1]);
                        break;
                    default:
                        throw new UserInputException("you entered an invalid argument!");
                }
            } catch (UserInputException | StoneAccessException | SemanticsException e ) {
                Terminal.printLine("Error, " + e.getMessage());
            } catch (NumberFormatException e) {
                Terminal.printLine("Error, invalid input!");
            }
        }
    }

    /**
     * Diese Methode wird genutzt um das Spiel zu beenden.
     */
    private static void quit() {
        System.exit(0);
    }


    /**
     * Diese Methode wird genutzt um alle Spielsteine die im Beutel sind auszugeben.
     * @param board    Zu diesem Board gehört der beutel.
     */
    private static void bag(GameBoard board) {
        Terminal.printLine(board.getBag().print());
    }


    /**
     * Der Place Befehl.
     * @param board auf diesem Board wird der Stein platziert.
     * @param coordinates und zwar genau auf diesen Koordinaten.
     * @throws UserInputException falsch der Benutzer Quatsch eingibt.
     * @throws SemanticsException falls regeln des Spiels verletzt werden.
     */
    private static void place(GameBoard board, String coordinates)
            throws SemanticsException {
        try {
            String[] parts = coordinates.split(";", 2);
            if (parts.length != 2)
                throw new UserInputException("place needs 2 integer values seperated by ;");
            if (youMaySelect)
                throw new UserInputException("your opponent has to select a stone first!");
            if (board.isFinished())
                throw new SemanticsException("game is already over!");

            board.placeStone(theChosenOne, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            if (!board.getBag().getStones().isEmpty()) {
                youMaySelect = true;
                roundcounter++;
                if (!board.isFinished())
                    Terminal.printLine("OK");
            }

            // Sieg falls das Spiel beendet ist.
            if (board.isFinished()) {
                Terminal.printLine("P" + board.getWinner().toString() + " wins");
                youMaySelect = false;
                Terminal.printLine(String.valueOf(roundcounter));
            }
            // Draw falls der Beutel ohne Sieger leer ist.
            if (board.getBag().getStones().isEmpty() && !board.isFinished())
                Terminal.printLine("draw");
        } catch (CellAccessException | UserInputException | NumberFormatException e) {
            Terminal.printLine("Error, " + e.getMessage());
            // Spielstein wieder zurücklegen, falls irgendwas bei der Platzierung schief gegangen ist.
            board.getBag().getStones().add(theChosenOne);
            // Ursprünglicher Spieler wieder am zug.
            board.changeCurrentActivePlayer();
            // Damit man bei Fehlschlag wieder zurück an der Select stelle ist, wie in ILIAS beschrieben.
            youMaySelect = true;
        }
    }


    /**
     * Mit dieser Methode wird ein Spielstein aus dem beutel genommen und auch entfernt.
     * @param board Hier spielt sich wieder alles ab.
     * @param stone der Stein der gewählt werden soll.
     * @throws StoneAccessException falls der Stein schon entnommen wurde.
     */
    private static void select(GameBoard board, String stone) throws StoneAccessException, SemanticsException,
            NumberFormatException {
        if (Integer.parseInt(stone) < 0 || Integer.parseInt(stone) > 15)
            throw new UserInputException("select takes exactly 1 Integer between 0 and 15!");
        if (board.isFinished())
            throw new SemanticsException("game is already over!");
        if (youMaySelect) {
            int stoneNumber = Integer.parseInt(stone);
            theChosenOne = board.getBag().getStoneByNumber(stoneNumber);
            board.getBag().getStones().remove(board.getBag().getStoneByNumber(stoneNumber));
            youMaySelect = false;
            board.changeCurrentActivePlayer();
            Terminal.printLine("OK");
        }
        else
            throw new UserInputException("select cant be called two times in a row!");
    }


    /**
     * Diese Methode wird genutzt um den Inhalt einer Zeile auszugeben.
     * @param board das Board wieder auf dem alles passiert.
     * @param row besagte Zeile deren Inhalt wir sehen möchten.
     * @throws UserInputException falls der Nutzer quatsch eingibt.
     */
    private static void rowPrint(GameBoard board, String row) throws UserInputException {
        if (row.length() == 1)
            Terminal.printLine(board.rowPrint(Integer.parseInt(row)));
        else
            throw new UserInputException("rowprint takes only one Integer value between 0 and 5!");
    }


    /**
     * Diese Methode wird genutzt um den Inhalt einer Zeile auszugeben.
     * @param board das Board wieder auf dem alles passiert.
     * @param col besagte Zeile deren Inhalt wir sehen möchten.
     * @throws UserInputException falls der Nutzer quatsch eingibt.
     */
    private static void colPrint(GameBoard board, String col) throws UserInputException {
        if (col.length() == 1)
            Terminal.printLine(board.colPrint(Integer.parseInt(col)));
        else
            throw new UserInputException("colprint takes only one Integer Value between 0 and 5!");
    }
}
