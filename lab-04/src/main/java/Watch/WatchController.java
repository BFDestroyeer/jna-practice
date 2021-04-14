package Watch;

import Events.EventManager;
import Events.IListener;
import Events.IPublisher;

public class WatchController implements IPublisher {
    IWatch watch;
    Thread thread = null;

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
        manager.broadcast(watch.getTimeEvent());
    }

    public void setEnabled() {
        if (this.thread != null) {
            return;
        }
        this.thread = new Thread(() -> {
            int tickTime = watch.getTickTime();
            try {
               while (true) {
                   Thread.sleep(tickTime);
                   watch.doTick();
                   broadcastTime();
               }
           } catch (InterruptedException e) { }
        });
        thread.start();
    }

    public void setDisabled() {
        thread.interrupt();
        thread = null;
        broadcastTime();
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
