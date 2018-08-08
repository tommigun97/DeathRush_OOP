package controller;

import java.io.IOException;
import java.util.List;
import utilities.Pair;

/**
 * Interface to Save the top ten time spent to win the game.
 */
public interface ScoreInterface {

	/**
	 * Add a new Pair of Player Name and his time score (Integer) to the Score. The
	 * list will be sorted and resized.
	 * 
	 * @param p
	 *            The new score (Pair<String, Integer>)
	 */
	public void addScore(final Pair<Pair<String, Integer> ,String> p );

	/**
	 * Save the score into the file
	 * 
	 * @param a
	 *            the new Pair to add with the name and the time of player
	 * @throws IOException
	 *             If unable to save data
	 */
	void saveOnFile() throws IOException;

	/**
	 * Delete the scoreList
	 * 
	 */
	void deleteAllScore();

	/**
	 * Check if themscore is also the best.
	 * 
	 * @param a
	 *            the score to test
	 * @return true if is the shortest time
	 */
	boolean isRecord(Pair<String, Integer> a);

	/**
	 * Returns the list of scores.
	 *
	 * @return The scoreList
	 */
	List<Pair<Pair<String, Integer>, String>> getScoreList() throws IOException;

}
