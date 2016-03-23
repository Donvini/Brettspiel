package edu.kit.informatik.exceptions;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class StoneIsAlreadyUsedException extends StoneAccessException {
    /**
     * Gegen die Fehlermeldung
     */
    static final long serialVersionUID = 1283891230981273L;

    /**
     * Konstruktor der Exception
     * @param message Die Fehlermeldung
     */
    public StoneIsAlreadyUsedException(String message) {
        super(message);
    }
}
