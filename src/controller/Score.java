package controller;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import utilities.Pair;

/**
 * 
 * 
 *
 */
public class Score implements ScoreInterface {

	private static final Integer MAX_SCORE = 10;

	private Optional<List<Pair<String, Integer>>> scoreList;
	private final String fileName;
	private boolean save;

	/**
	 * 
	 * @param fileName
	 *            .
	 */
	public Score(final String fileName) {
		if (fileName.isEmpty()) {
			throw new IllegalArgumentException("fileName must be valid.");
		}
		this.scoreList = Optional.empty();
		this.fileName = fileName;
		this.save = false;
	}

	@Override
	public void addScore(final Pair<String, Integer> p) {
		if (!this.scoreList.isPresent()) {
			this.loadData();
		}
		final List<Pair<String, Integer>> list = this.scoreList.get();
		list.add(p);
		this.sortList(list);
		this.resizeList(list);
		this.scoreList = Optional.of(list);
		this.save = true;
	}

	@Override
	public final void saveOnFile() throws IOException {
		if (this.scoreList.isPresent() && this.save) {
			try (DataOutputStream out = new DataOutputStream(new FileOutputStream(this.fileName))) {
				for (final Pair<String, Integer> p : this.scoreList.get()) {
					out.writeUTF(p.getFirst());
					out.writeInt(p.getSecond().intValue());
				}
				this.save = false;
			} catch (final Exception e) {
			}
		}
	}

	@Override
	public final void deleteAllScore() {
		this.scoreList = Optional.of(new LinkedList<Pair<String, Integer>>());
		this.save = true;
	}

	@Override
	public final List<Pair<String, Integer>> getScoreList() {
		if (!this.scoreList.isPresent()) {
			this.loadData();
		}
		return new ArrayList<>(this.scoreList.get());
	}

	@Override
	public final boolean isRecord(final Pair<String, Integer> a) {
		return a.equals(scoreList.get().get(0));
	}

	/**
	 * read the data from file.
	 */
	private void loadData() {
		final List<Pair<String, Integer>> list = new LinkedList<>();
		try (DataInputStream in = new DataInputStream(new FileInputStream(this.fileName))) {
			while (true) {
				final String name = in.readUTF();
				final Integer score = Integer.valueOf(in.readInt());
				list.add(new Pair<String, Integer>(name, score));
			}
		} catch (final Exception ex) {
		}
		this.sortList(list);
		if (this.resizeList(list)) {
			this.save = true;
		}
		this.scoreList = Optional.of(list);
	}

	/**
	 * Mantain the list dimension to 10
	 *
	 * @param l
	 *            The starting list
	 * @return True if the list was modified, False otherwise
	 */
	private boolean resizeList(final List<Pair<String, Integer>> l) {
		final boolean changed = l.size() > MAX_SCORE;
		if (l.size() > MAX_SCORE) {
			l.remove(l.size() - 1);
		}
		return changed;
	}

	/**
	 * sort the scoreList2.
	 * 
	 * @param scoreList2
	 */
	private void sortList(final List<Pair<String, Integer>> scoreList2) {
		Collections.sort(scoreList2, (a, b) -> a.getSecond() - b.getSecond());
	}

}
