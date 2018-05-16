package controller;

import java.util.Timer;
import java.util.TimerTask;

public class Time {
	
	private int totalSec;
	private int secondPassed;
	private int minutePassed;
	private int hourPassed;
	private Timer myTimer;
	
	public Time() {
		this.totalSec = 0;
		this.hourPassed = 0;
		this.minutePassed = 59;
		this.secondPassed = 55;
		this.myTimer = new Timer();
	}
	
	TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			if(secondPassed == 59) {
				minutePassed++;
				secondPassed = -1;
			}
			if(minutePassed == 60) {
				hourPassed++;
				minutePassed = 0;
			}
			secondPassed++;
			totalSec++;
			System.out.println("hour passed: " + hourPassed + "min passed: " + minutePassed + " sec passed: " + secondPassed);
		}
	};
	
	public void start() {
		myTimer.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	public static void main(String[] args) {
		Time time = new Time();
		time.start();
	}
	
}
