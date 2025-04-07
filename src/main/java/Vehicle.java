public class Vehicle {
    private final int number;
    private final String title;
    private final int speed;
    private int range;

    public int getNumber()
    {
        return number;
    }

    public String getTitle()
    {
        return  title;
    }

    public int getSpeed()
    {
        return  speed;
    }

    public int getRange() {return range;}

    public void setRange(int range){ this.range = range; }

    public Vehicle(int number, String title, int speed)
    {
        this.number = number;
        this.title = title;
        this.speed = speed;
    }
}
