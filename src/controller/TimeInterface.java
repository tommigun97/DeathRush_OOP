package controller;

import java.util.List;

public interface TimeInterface {

	/**
	 * start the clock
	 */
	public void start();

	/**
	 * set clock on pause
	 */
	public void pause();

	/**
	 * unPause the clock
	 */
	public void resume();

	/**
	 * delete sec min and hour, and be ready to start a new time
	 */
	public void resetTime();

	/**
	 * 
	 * @return All the time spent in second
	 */
	public int getTotalSecond();

	/**
	 * 
	 * @return the actual seconds
	 */
	public int getSec();

	/**
	 * 
	 * @return the actual mins
	 */
	public int getMin();

	/**
	 * 
	 * @return the actual hours
	 */
	public int getHour();

	/**
	 * 
	 * @return a integer list with hour, minutes and seconds
	 */
	public List<Integer> transformSecondInTime();

}
