package model.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.room.Room;

public class ReadEntityImpl implements ReadEntity {

    private final static double HEIGHT = 1;
    private final static double WEIGHT = 1;

    private String file;
    private Room currentRoom;

    public ReadEntityImpl(String fileName, Room room) {
        this.file = fileName;
        this.currentRoom = room;
    }

    public void populateRoom() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            int column = bf.readLine().length();
            int row = calculateRow(file);
            double columnProportion = WEIGHT / column;
            double rowProportion = HEIGHT / row;
            bf.reset();
            String line;
            char currentChar;
            for (int i = 0; i<row; i++ ) {
            	line = bf.readLine();
            	for(int j = 0; j<column; j++) {
            		currentChar = line.charAt(j);
            		if(currentChar != 0) {
            			this.scanFind(currentChar, i*rowProportion, j*columnProportion);
            		}
            	}
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    
    }

    private int calculateRow(String filename) throws IOException {
        int rowCount = 0;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            while (bf.readLine() != null) {
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowCount;

    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    private void scanFind(char type, double x, double y ) {
    	
    }
}