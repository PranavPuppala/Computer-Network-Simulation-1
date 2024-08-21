import java.util.*;
public class ArrayEventList implements FutureEventList {
    private int size = 0;
    private int capacity = 5;
    private Timer[] timerArray = new Timer[capacity];
    private int simulationTime  = 0; // arrival time of the last event

    private void removeElement(int index){
        int k = 0;
        Timer[] tempArray = new Timer[capacity];
        for(int i = 0; i < size; ++i){
            if(i == index){
                continue;
            }
            tempArray[k++] = timerArray[i];
        }
        timerArray = tempArray;
        --size;
    }

    private void selectionSort(){
        int min_index;
        Timer tempObj;
        for(int i = 0;i < size-1; ++i){
            min_index = i;
            for(int j = i + 1; j < size; ++j){
                if(timerArray[min_index].getArrivalTime() > timerArray[j].getArrivalTime()){
                    min_index = j;
                }
            }

            tempObj = timerArray[i];
            timerArray[i] = timerArray[min_index];
            timerArray[min_index] = tempObj;
        }
    }

    private boolean binarySearch(int first,int last, Timer obj){
        if(first <= last){
            int mid = (first + last) / 2;
            if(timerArray[mid].getArrivalTime() == obj.getArrivalTime() && timerArray[mid].getID() == obj.getID()){
                removeElement(mid);
                return true;
            }
            else if(timerArray[mid].getArrivalTime() < obj.getArrivalTime()){
                return binarySearch(mid + 1, last, obj);
            }
            else if(timerArray[mid].getArrivalTime() > obj.getArrivalTime()){
                return binarySearch(first, mid - 1, obj);
            }

            else if(timerArray[mid].getArrivalTime() == obj.getArrivalTime() && timerArray[mid].getID() != obj.getID()){
                int index = 0;
                for(int i = 0; i < size; ++i){
                    if(timerArray[i].getID() == obj.getID()){
                        index = i;
                        break;
                    }
                }
                return binarySearch(index, index, obj);
            }
        }

        return false;
    }

    @Override
    public Event removeFirst() {
        Timer removedEvent = timerArray[0];
        removedEvent.setInsertionTime(removedEvent.getArrivalTime());
        simulationTime = removedEvent.getArrivalTime();
        timerArray = Arrays.copyOfRange(timerArray,1,capacity+1);
        --size;
        return removedEvent;
    }

    @Override
    public boolean remove(Event e){
        boolean found = false;
        found = binarySearch(0,size, (Timer) e);
        return found;
    }

    @Override
    public void insert(Event e) {
        if(size < capacity){
            timerArray[size] = (Timer) e;
            ++size;
        }

        else if(size == capacity){
            capacity *= 2;
            timerArray = Arrays.copyOf(timerArray, capacity);
            timerArray[size] = (Timer) e;
            ++size;
        }

        selectionSort();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int getSimulationTime() {
       return simulationTime;
    }
    
}


