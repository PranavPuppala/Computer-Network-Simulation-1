import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
public class Main{
	public static void main(String[] args) throws IOException{
        ArrayList<Timer> events= new ArrayList<Timer>();
        ArrayEventList eventList = new ArrayEventList();
		FileInputStream fs  = new FileInputStream("events.txt");
		Scanner scn = new Scanner(fs);
		String command = " ";
		int duration = 0;
		
		while(scn.hasNextLine()){
		  command = scn.next();
		  if(command.equals("I")){
		      duration = scn.nextInt();
			  if(duration > 0){
				Timer event = new Timer(duration);
			  	events.add(event);
			  	eventList.insert(event);
			  	System.out.println(event);
			  }

			  else{
				System.out.println("Duration is invalid");
			  }
			  
		  }
		  
		  else if(command.equals("R")){
			  if(eventList.size() > 0){
				Timer tempObj = (Timer) eventList.removeFirst();
				tempObj.handle();
			  }
			  else{
				System.out.println("Array is empty");
			  }
		  }
		  
		  else if(command.equals("C")){
			   if(eventList.size() > 0){
					duration = scn.nextInt();
					boolean removed = false;
			  		Timer tempObj = null;
			  		int cancelledTime = 0;
					for(int i = 0; i < events.size(); ++i){
						if(duration == events.get(i).getID()){
							removed = eventList.remove(events.get(i));
							tempObj = events.get(i);
							cancelledTime = eventList.getSimulationTime();
							break;
						}
					  }
					if(removed){
						tempObj.cancel(cancelledTime);
					}
					else{
						System.out.println("Invalid ID");
					}	
			   }

			   else{
					System.out.println("Array is empty");
			   }	
		  }
		  
		  else{
		      System.out.println("incorrect command.");
		  }
		  
		}
		System.out.println("\n" + "Future event list size: " + eventList.size());
		System.out.println("Future event list capacity: " + eventList.capacity());
		scn.close();
	}

}
