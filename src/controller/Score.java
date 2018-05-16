package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utilities.Pair;
/**
 * 
 * 
 *
 */
public class Score implements ScoreInterface {

    private static final Integer MAX_SCORE = 10;

    private List<Pair<String, Integer>> scoreList = new ArrayList<>();
	private final String fileName;

	/**
	 * 
	 * @param fileName
	 *            .
	 */
	public Score(final String fileName) {
		this.fileName = fileName;
	}

    @Override
    public final void saveOnFile(final Pair<String, Integer> a) throws IOException {
        File file = new File(this.fileName);
        if (!file.exists()) {
            System.out.println("Creazione nuovo file in corso...");
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        putScoreInAList(a);
        this.scoreList.forEach(x -> {
            try {
                bw.write(x.getFirst());
                bw.newLine();
                bw.write(String.valueOf(x.getSecond()));
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }

	/**
	 * call isRecord and sortList method, to remove sort the score and keep only the
	 * best ten score
	 * 
	 * @param a is the new score
	 * @throws IOException
	 */
	private void putScoreInAList(final Pair<String, Integer> a) throws IOException {
		this.scoreList = this.getScoreList();
		this.scoreList.add(a);
		this.sortList(scoreList);
        if (this.isRecord(a)) {
            System.out.println("NEW RECORD!!");
        }
        if (scoreList.size() > MAX_SCORE) {
            scoreList.remove(scoreList.size() - 1);
        }
    }

	/**
	 * sort the scoreList2
	 * @param scoreList2
	 */
    private void sortList(final List<Pair<String, Integer>> scoreList2) {
        Collections.sort(scoreList2, (a, b) -> a.getSecond() - b.getSecond());
    }

    @Override
    public final void deleteAllScore() {
        File f = new File(fileName);
        if (!f.exists()) {
            throw new IllegalArgumentException("File Inesistente!");
        }
        f.delete();
        this.scoreList.clear();
    }

    @Override
    public final List<Pair<String, Integer>> getScoreList() throws IOException {

        List<Pair<String, Integer>> list = new ArrayList<>();
        FileReader file = new FileReader(this.fileName);
        BufferedReader br = new BufferedReader(file);
        while (br.readLine() != null) {
            final String name = br.readLine();
            final int score = Integer.parseInt(br.readLine());
            list.add(new Pair<String, Integer>(name, score));
        }
        br.close();
        return list;
    }

    @Override
    public final boolean isRecord(final Pair<String, Integer> a) {
        return a.equals(scoreList.get(0));
    }

}
