package controller;

import java.io.IOException;
import java.util.List;
import utilities.Pair;

/**
 * Interface to Save the top ten time spent to win the game.
 */
public interface ScoreInterface {

	/**
	 * Create a new File and Save scores on it.
	 * 
	 * @param a
	 *            the new Pair to add with the name and the time of player
	 * @throws IOException
	 *             If unable to save data
	 */
	public void saveOnFile(Pair<String, Integer> a) throws IOException;

	/**
	 * Delete the score file if it exist and clear the list used to save and sort
	 * score.
	 * 
	 */
	public void deleteAllScore();

	/**
	 * Check if the new score is also the best.
	 * 
	 * @param a
	 *            the pair with the score to test
	 * @return true if is the shortest time
	 */
	public boolean isRecord(Pair<String, Integer> a);

	/**
	 * read all the score on file and put in a list.
	 * 
	 * @return the score's list
	 * @throws IOException
	 *             If unable to reach file
	 */
	public List<Pair<String, Integer>> getScoreList() throws IOException;

}
