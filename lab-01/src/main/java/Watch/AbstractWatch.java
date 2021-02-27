package Watch;

/**
 * Abstract watches
 */
public abstract class AbstractWatch {
    private String name;
    private double price;

    public AbstractWatch(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
