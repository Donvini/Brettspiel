package edu.kit.informatik.exceptions;

/**
 * übernommen aus Musterlösung
 * @author Vincenzo Pace | KIT
 * @version edu.kit.informatik.0
 *
 */
public class SemanticsException extends Exception {

    /**
     * Auto generated UID for serializing. Actually not needed according to the task, but absence produces annoying
     * warnings.
     */
    private static final long serialVersionUID = 2902883905608249312L;

    /**
     * Creates a new SemanticsException with the given detailed message.
     * @param pMessage some detailed error message (null is not allowed)
     */
    public SemanticsException(String pMessage) {
        super(pMessage);
    }
}
