import Watch.AdvancedWatch;

public class Main {
    public static void main(String[] args) throws Exception{
        Watch.AdvancedWatch my_watches = new Watch.AdvancedWatch("Casio", 100);
        my_watches.setTime(10, 40, 40);
        my_watches.addTime(60, 70, 80);
        System.out.println(my_watches.getName());
    }
}