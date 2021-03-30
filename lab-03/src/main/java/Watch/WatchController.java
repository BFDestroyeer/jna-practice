package Watch;

public class WatchController {
    IWatch watch;
    boolean enabled;

    public WatchController(IWatch watch) {
        this.watch = watch;
    }

    public void setEnabled() {
        if (this.enabled) {
            return;
        }
        this.enabled = true;
        new Thread(() -> {
            if (this.watch.hasSecondsHand()) {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        if (enabled) {
                            watch.addSeconds(1);
                        }
                        else {
                            return;
                        }
                    }
                } catch (Exception e) { }
            } else {
                try {
                    while (true) {
                        Thread.sleep(60000);
                        if (enabled) {
                            watch.addSeconds(1);
                        }
                        else {
                            return;
                        }
                    }
                } catch (Exception e) { }
            }

        }).start();
    }

    public void setDisabled() {
        this.enabled = false;
    }

    public void reset() {
        try {
            this.watch.setHours(0);
            this.watch.setMinutes(0);
            this.watch.setSeconds(0);
        } catch (Exception e) {}
        setDisabled();
    }
}
