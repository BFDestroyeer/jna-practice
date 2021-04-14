package Watch;

import Event.EventManager;
import Event.IListener;
import Event.IPublisher;

public class WatchController implements IPublisher {
    IWatch watch;
    Thread thread = null;
    EventManager manager = new EventManager();

    public WatchController(IWatch watch) {
        this.watch = watch;
    }

    @Override
    public void addListener(IListener listener) {
        manager.subscribe(listener);
    }

    @Override
    public void removeListener(IListener listener) {
        manager.unsubscribe(listener);
    }

    protected void broadcastTime() {
        manager.broadcast(watch.getTimeUpdateEvent());
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
