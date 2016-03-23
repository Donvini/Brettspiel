package edu.kit.informatik.exceptions;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class StoneAccessException extends Exception {
    /**
     * Gegen die Fehlermeldung
     */
    static final long serialVersionUID = 68674L;

    /**
     * Konstruktor der Fehlermeldung
     * @param message Die Fehlermeldung die dem Nutzer mitgeteilt werden soll.
     */
    public StoneAccessException(String message) {
        super(message);
    }
}
