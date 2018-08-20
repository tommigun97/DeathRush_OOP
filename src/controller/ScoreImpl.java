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
public final class ScoreImpl implements Score {

    private static final Integer MAX_SCORE = 10;

    private Optional<List<Pair<Pair<String, Integer>, String>>> scoreList;
    private final String fileName;
    private boolean save;

    /**
     * The Constructor of the class ScoreImple.
     * 
     * @param fileName
     *            .
     */
    public ScoreImpl(final String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("Invalid File name");
        }
        this.scoreList = Optional.empty();
        this.fileName = fileName;
        this.save = false;
    }

    /**
     * read the data from file.
     */
    private void loadData() {
        final List<Pair<Pair<String, Integer>, String>> list = new LinkedList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(this.fileName))) {
            while (true) {
                final String name = in.readUTF();
                final Integer score = Integer.valueOf(in.readInt());
                final String time = in.readUTF();
                list.add(new Pair<Pair<String, Integer>, String>(new Pair<String, Integer>(name, score), time));
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
     * Mantain the list dimension to 10.
     *
     * @param l
     *            The starting list
     * @return True if the list was modified, False otherwise
     */
    private boolean resizeList(final List<Pair<Pair<String, Integer>, String>> l) {
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
    private void sortList(final List<Pair<Pair<String, Integer>, String>> scoreList2) {
        Collections.sort(scoreList2, (a, b) -> a.getFirst().getSecond() - b.getFirst().getSecond());
    }

    @Override
    public void addScore(final Pair<Pair<String, Integer>, String> p) {
        if (!this.scoreList.isPresent()) {
            this.loadData();
        }
        final List<Pair<Pair<String, Integer>, String>> list = this.scoreList.get();
        list.add(p);
        this.sortList(list);
        this.resizeList(list);
        this.scoreList = Optional.of(list);
        this.save = true;
    }

    @Override
    public void saveOnFile() throws IOException {
        if (this.scoreList.isPresent() && this.save) {
            try (DataOutputStream out = new DataOutputStream(new FileOutputStream(this.fileName))) {
                for (final Pair<Pair<String, Integer>, String> p : this.scoreList.get()) {
                    out.writeUTF(p.getFirst().getFirst());
                    out.writeInt(p.getFirst().getSecond().intValue());
                    out.writeUTF(p.getSecond());
                }
                this.save = false;
            } catch (final Exception e) {
            }
        }
    }

    @Override
    public void deleteAllScore() {
        this.scoreList = Optional.of(new LinkedList<Pair<Pair<String, Integer>, String>>());
        this.save = true;
    }

    @Override
    public List<Pair<Pair<String, Integer>, String>> getScoreList() {
        if (!this.scoreList.isPresent()) {
            this.loadData();
        }
        return new ArrayList<>(this.scoreList.get());
    }

    @Override
    public boolean isRecord(final Pair<String, Integer> a) {
        return a.equals(scoreList.get().get(0));
    }

}
