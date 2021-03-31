package Watch;

import Events.EventManager;
import Events.IListener;
import Events.IPublisher;

public class WatchController implements IPublisher {
    IWatch watch;
    boolean enabled;

    public WatchController(IWatch watch) {
        this.watch = watch;
    }

    EventManager manager = new EventManager();

    @Override
    public void addListener(IListener listener) {
        manager.subscribe(listener);
    }

    @Override
    public void removeListener(IListener listener) {
        manager.unsubscribe(listener);
    }

    protected void broadcastTime() {
        try {
            if (watch.hasSecondsHand()) {
                manager.broadcast(new TimeEvent(watch.getHours(), this.watch.getMinutes(), this.watch.getSeconds()));
            } else {
                manager.broadcast(new TimeEvent(watch.getHours(), this.watch.getMinutes()));
            }
        } catch (Exception e) { }

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
                            this.broadcastTime();
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
                            watch.addMinutes(1);
                            this.broadcastTime();
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
            this.broadcastTime();
        } catch (Exception e) {}
        setDisabled();
    }
}
