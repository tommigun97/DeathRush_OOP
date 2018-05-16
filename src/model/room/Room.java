package model.room;

import java.util.Random;
import java.util.Set;

import model.entity.Door;



public interface Room {
    
         void addDoor(Door doorList);

         Set<Door> getDoor();

         int getRoomID();

         boolean isComplited();

         void setComplited(boolean complited);
}
