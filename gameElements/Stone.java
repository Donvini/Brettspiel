package edu.kit.informatik.gameElements;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Stone {

    private final String size;
    private final String shape;
    private final String color;
    private final String density;
    private final int number;

    /**
     * Konstruktor of the Class
     * @param size Größe des Steins
     * @param shape Form des Steins
     * @param color Farbe des Steins
     * @param density Dichte des Steins
     * @param number Nummer des Steins im bag
     */
    public Stone(int number, String color, String shape, String size, String density) {
        this.number = number;
        this.color = color;
        this.shape = shape;
        this.size = size;
        this.density = density;
    }

    /**
     * Getter für die Zahl
     * @return Zahl des steins.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Methode um zu überprüfen ob die Steine
     * @param a a
     * @param b b
     * @param c c
     * @param d und d
     * @return irgendwo eine gemeinsamkeit haben.
     */
    public static boolean haveInCommon(Stone a, Stone b, Stone c, Stone d) {
        if (a.getColor().equals(b.getColor()) && a.getColor().equals(c.getColor())
                && a.getColor().equals(d.getColor()))
            return true;
        else if (a.getDensity().equals(b.getDensity()) && a.getDensity().equals(c.getDensity())
                && a.getDensity().equals(d.getDensity()))
            return true;
        else if (a.getShape().equals(b.getShape()) && a.getShape().equals(c.getShape())
                && a.getShape().equals(d.getShape()))
            return true;
        else if (a.getSize().equals(b.getSize()) && a.getSize().equals(c.getSize())
                && a.getSize().equals(d.getSize()))
            return true;

        return false;
    }

    /**
     * Getter
     * @return für die Größe
     */
    private String getSize() {
        return size;
    }

    /**
     *
     * @return Form
     */
    private String getShape() {
        return shape;
    }

    /**
     *
     * @return Farbe
     */
    private String getColor() {
        return color;
    }

    /**
     *
     * @return Dichte.
     */
    private String getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
