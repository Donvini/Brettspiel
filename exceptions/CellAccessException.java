package edu.kit.informatik.exceptions;

/**
 * Diese Klasse enthält Teile aus einer alten Musterlösung.
 *
 * @author Vincenzo Pace | KIT
 * @version 1.0
 *
 */
public class CellAccessException extends SemanticsException {

    /**
     * Auto generated UID for serializing. Actually not needed according to the task, but absence produces annoying
     * warnings.
     */
    private static final long serialVersionUID = 2902383905608249312L;

    /**
     * Creates a new CellAccessException with the given detailed message.
     * @param message Die Fehlermeldung.
     */
    public CellAccessException(String message) {
        super(message);
    }
}
