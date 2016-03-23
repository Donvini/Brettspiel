package edu.kit.informatik;

import edu.kit.informatik.commandline.Commandline;
import edu.kit.informatik.exceptions.UserInputException;
import edu.kit.informatik.gameElements.Player;
import edu.kit.informatik.gameElements.board.GameBoard;
import edu.kit.informatik.gameElements.board.StandardBoard;
import edu.kit.informatik.gameElements.board.TorusBoard;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class Main {
    private Main() { }

    /**
     * Die Hauptmethode
     * @param args Kommandozeilenargument: Entweder torus oder standard werden akzeptiert.
     */
    public static void main(String[] args) {
        try {
            // Ohne Kommandozeilenargument geht hier nix!
            if (args.length != 1)
                throw new IllegalArgumentException("exatly one argument is needed to start the game!");
         Player[] players = new Player[2];
         players[0] = new Player(1);
         players[1] = new Player(2);
         GameBoard board;
            switch (args[0].toLowerCase()) {
                //erzeugen wir genau das board was wir brauchen
                case "standard":
                    board = new StandardBoard(6, 6, players);
                    break;
                case "torus":
                    board = new TorusBoard(6, 6, players);
                    break;
                default:
                    throw new UserInputException("board can only be torus or standard.");
            }
            // Und schmeißen dann unser Spiel an!
            Commandline.startGame(board);
        } catch (IllegalArgumentException e) {
            Terminal.printLine("Error, " + e.getMessage());
        }
    }
}
