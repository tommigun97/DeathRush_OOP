package model;

/**
 * Location Define the center point and the area of an entity. Center Point
 * defined as x and y values in a Cartesian plane.
 */
public final class Location {

    private double x;
    private double y;
    private Area area;

    /**
     * Constructor with one parameters. Create a new Location with the same fields
     * as the input Location
     * 
     * @param loc
     *            input location
     */
    public Location(final Location loc) {
        this.x = loc.x;
        this.y = loc.y;
        this.area = new Area(loc.area);
    }

    /**
     * Constructor with parameters. Set location with input parameters
     * 
     * @param initx
     *            abscissa value
     * @param inity
     *            ordinate value
     * @param initarea
     *            Area occupied by the entity
     */
    public Location(final double initx, final double inity, final Area initarea) {
        this.x = initx;
        this.y = inity;
        this.area = initarea;
    }

    /**
     * Getter of x coordinate of the Location.
     * 
     * @return x abscissa value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getter of y coordinate of the Location.
     * 
     * @return y ordinate value
     */
    public double getY() {
        return this.y;
    }

    /**
     * Getter of the Area of the Location.
     * 
     * @return area as an Area
     */
    public Area getArea() {
        return this.area;
    }

    /**
     * Setter of x coordinate of the Location.
     * 
     * @param newx
     *            abscissa value
     */
    public void setX(final double newx) {
        this.x = newx;
    }

    /**
     * Setter of y coordinate of the Location.
     * 
     * @param newy
     *            ordinate value
     */
    public void setY(final double newy) {
        this.y = newy;
    }

    /**
     * Setter of the Area of the Location.
     * 
     * @param newarea
     *            as the new Area
     */
    public void setArea(final Area newarea) {
        this.area = newarea;
    }

    /**
     * Method for identify that to things are colliding.
     * 
     * @param l
     *            location of other object
     * @return true if the location is the same
     */
    public boolean locationsCollide(final Location l) {
        return this.x == l.x && this.y == l.y;
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + ", area=" + area + "]";
    }
}
