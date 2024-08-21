public class Timer implements Event {
    public static int nextEventID = 0;
    public static int nextInsertionTime = 0;
    private int insertionTime;
    private int arrivalTime;
    private int duration;
    private int eventID;

    public Timer(int dur){
        this.duration = dur;
        this.eventID = nextEventID;
        ++nextEventID;
        this.insertionTime = nextInsertionTime;
        this.arrivalTime = this.insertionTime + this.duration;
    }

    public int getID(){ // addtional method.
        return this.eventID;
    }

    @Override
    public void setInsertionTime(int currentTime){
        nextInsertionTime = currentTime; // called when the "R" command is processed. currentTime is the arrivalTime of the event with the smallest arrival time.
    }

    @Override
    public int getInsertionTime() {
        return insertionTime;
        
    }

    @Override
    public int getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public void cancel(int currentTime) {
        System.out.println("Timer " + eventID + " cancelled at time: " + currentTime); // Here currentTime is the arrival time of the latest removed event
    }

    @Override
    public void handle() {
        System.out.println("Timer " + eventID + " handled (arrival time: " + arrivalTime + ")");
    }

    public String toString(){
        return "Timer " + eventID + " (insertion time: " + insertionTime + ", arrival time: " + arrivalTime + ")";
    }
    
}
