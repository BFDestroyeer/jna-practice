import Watch.AdvancedWatch;

public class Main {
    public static void main(String[] args) throws Exception{
        Watch.AdvancedWatch my_watch = new Watch.AdvancedWatch("Famous brand", 100);
        System.out.format("My \"%s\" watch cost %.2f$\n", my_watch.getName(), my_watch.getPrice());
        my_watch.setTime(10, 25, 40);
        System.out.format("Init time: %d:%d:%d\n", my_watch.getHours(), my_watch.getMinutes(), my_watch.getSeconds());
        my_watch.addTime(4, 16, 10);
        System.out.format("New time: %d:%d:%d", my_watch.getHours(), my_watch.getMinutes(), my_watch.getSeconds());
    }
}