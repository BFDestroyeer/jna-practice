package Watch;

public class BWatchWithAlarmClock {
        public static IWatchWithAlarmClock build(WatchType type, String name, double price) {
            return switch (type) {
                case SimpleWatch -> new SimpleWatch(name, price);
                case AdvancedWatch -> new AdvancedWatch(name, price);
                default -> null;
            };
        }
}
