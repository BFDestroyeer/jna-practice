import Watch.AdvancedWatch;

public class Main {
    public static void main(String[] args) throws Exception{
        Watch.AdvancedWatch my_watch = new Watch.AdvancedWatch("Famous brand", 100);
        System.out.format("My \"%s\" watch cost %.2f$\n", my_watch.getName(), my_watch.getPrice());
        my_watch.setHours(10);
        my_watch.setMinutes(20);
        my_watch.setSeconds(30);
        System.out.println(my_watch);
    }
}