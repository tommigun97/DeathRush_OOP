package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import view.ViewImpl;
import view.View;

/**
 * 
 *
 */
public class TimeImpl {

    private int totalSec;
    private int secondPassed;
    private int minutePassed;
    private int hourPassed;
    private Timer myTimer;
    private boolean running;

    /**
     * 
     */
    public TimeImpl() {
        this.totalSec = 0;
        this.hourPassed = 0;
        this.minutePassed = 0;
        this.secondPassed = 0;
        this.myTimer = new Timer();
        this.running = false;
    }

    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            if (running) {
                if (secondPassed == 59) {
                    minutePassed++;
                    secondPassed = -1;
                }
                if (minutePassed == 60) {
                    hourPassed++;
                    minutePassed = 0;
                }
                secondPassed++;
                totalSec++;
            }
        }
    };

    /**
     * 
     */
    public void start() {
        this.running = true;
        this.myTimer.scheduleAtFixedRate(task, 1000, 1000);
    }

    /**
     * 
     */
    public void pause() {
        if (this.running) {
            this.running = false;
        }
    }

    public void resume() {
        if (!this.running) {
            this.running = true;
        }
    }

    public void resetTime() {
        this.pause();
        this.hourPassed = 0;
        this.minutePassed = 0;
        this.secondPassed = 0;
        this.totalSec = 0;
    }

    public int getTotalSecond() {
        return this.totalSec;
    }

    public String getCurrentTime() {
        return ("Time " + hourPassed + ":" + minutePassed + ":" + secondPassed);
    }

    public int getSec() {
        return this.secondPassed;
    }

    public int getMin() {
        return this.minutePassed;
    }

    public int getHour() {
        return this.hourPassed;
    }

    public List<Integer> transformSecondInTime() {
        List<Integer> list = new LinkedList<>();
        list.add(getHour());
        list.add(getMin());
        list.add(getSec());
        return list;
    }
}
