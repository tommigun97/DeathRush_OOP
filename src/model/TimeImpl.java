package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is used to take care of the time, we used it on the game cause we
 * set the player score by the time the he used to finish the game.
 *
 */
public final class TimeImpl implements Time {

    private static final Integer SEC = 59;
    private static final Integer MIN = 60;

    private int totalSec;
    private int secondPassed;
    private int minutePassed;
    private int hourPassed;
    private final Timer myTimer;
    private boolean running;

    /**
     * The Constructor of the class TimeImpl.
     */
    public TimeImpl() {
        this.totalSec = 0;
        this.hourPassed = 0;
        this.minutePassed = 0;
        this.secondPassed = 0;
        this.myTimer = new Timer();
        this.running = false;
    }

    private final TimerTask task = new TimerTask() {

        @Override
        public void run() {
            if (running) {
                if (secondPassed == SEC) {
                    minutePassed++;
                    secondPassed = -1;
                }
                if (minutePassed == MIN) {
                    hourPassed++;
                    minutePassed = 0;
                }
                secondPassed++;
                totalSec++;
            }
        }
    };

    @Override
    public void start() {
        this.running = true;
        this.myTimer.scheduleAtFixedRate(task, 1000, 1000);
    }

    @Override
    public void pause() {
        if (this.running) {
            this.running = false;
        }
    }

    @Override
    public void resume() {
        if (!this.running) {
            this.running = true;
        }
    }

    @Override
    public void resetTime() {
        this.pause();
        this.hourPassed = 0;
        this.minutePassed = 0;
        this.secondPassed = 0;
        this.totalSec = 0;
    }

    @Override
    public int getTotalSecond() {
        return this.totalSec;
    }

    @Override
    public String getCurrentTime() {
        return ("Time " + hourPassed + ":" + minutePassed + ":" + secondPassed);
    }

    @Override
    public int getSec() {
        return this.secondPassed;
    }

    @Override
    public int getMin() {
        return this.minutePassed;
    }

    @Override
    public int getHour() {
        return this.hourPassed;
    }

    @Override
    public List<Integer> transformSecondInTime() {
        final List<Integer> list = new LinkedList<>();
        list.add(getHour());
        list.add(getMin());
        list.add(getSec());
        return list;
    }
}
