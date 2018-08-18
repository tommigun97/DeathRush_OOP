package model;

import java.util.List;

/**
 * 
 *
 */
public interface Time {

	/**
	 * start the clock.
	 */
	void start();

	/**
	 * set clock on pause.
	 */
	void pause();

	/**
	 * unPause the clock.
	 */
	void resume();

	/**
	 * delete sec min and hour, and be ready to start a new time.
	 */
	void resetTime();

	/**
	 * 
	 * @return All the time spent in second
	 */
	int getTotalSecond();

	/**
	 * 
	 * @return A string of the time passed
	 */
	String getCurrentTime();

	/**
	 * 
	 * @return the actual seconds
	 */
	int getSec();

	/**
	 * 
	 * @return the actual mins
	 */
	int getMin();

	/**
	 * 
	 * @return the actual hours
	 */
	int getHour();

	/**
	 * 
	 * @return a integer list with hour, minutes and seconds
	 */
	List<Integer> transformSecondInTime();

}
