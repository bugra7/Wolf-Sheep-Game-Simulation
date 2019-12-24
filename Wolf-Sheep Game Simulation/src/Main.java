import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	private static boolean isWolfDead = false;
	private static boolean isSheepDead = false;
	
	public static void main(String[] args) {
		
		List<String> corners = new ArrayList();
		
		corners.add("A");
		corners.add("B");
		corners.add("C");
		corners.add("D");
		corners.add("E");
		corners.add("F");
		corners.add("G");
		corners.add("H");
		corners.add("I");
		corners.add("J");
				
		for(int sheepStartIndex = 1; sheepStartIndex < corners.size(); sheepStartIndex++) {
			
			long wolfDeadCount = 0;
			
			for(int gameNo = 1; gameNo <= 10000000; gameNo++) {
				
				isWolfDead = false;
				isSheepDead = false;
				
				List<String> wolfTraveled = new ArrayList();
				wolfTraveled.add("A");
				
				String wolfCurrentCorner = corners.get(0);
				int wolfCurrentIndex = 0;
				
				while(isEveryoneAlive(wolfTraveled, corners, wolfCurrentIndex, sheepStartIndex)) {
					
					wolfCurrentCorner = corners.get(getRandomDirection(wolfCurrentIndex));
					
					wolfCurrentIndex = corners.indexOf(wolfCurrentCorner);
				}
				if(isWolfDead)	wolfDeadCount++;
			}
			Float winRatio = (float)wolfDeadCount/100000;
			
			System.out.println("Sheep on Point : " + corners.get(sheepStartIndex));
			System.out.print("Win Ratio : %");
			System.out.printf("%.2f", winRatio);
			System.out.println("\n");
		}
	}
	
	private static boolean isEveryoneAlive(List<String> wolfTraveled, List<String> corners, int wolfCurrentIndex, int sheepStartIndex) {
				
		if(wolfCurrentIndex == 0)	return true;
		
		boolean isSame = false;
		
		for(String w : wolfTraveled) {
						
			if(w.equals(corners.get(wolfCurrentIndex))) {
				isSame = true;
				break;
			}
			if(isSame)	break;
		}
		
		if(!isSame) 
			wolfTraveled.add(corners.get(wolfCurrentIndex));
		
		if(wolfTraveled.get(wolfTraveled.size()-1).equals(corners.get(sheepStartIndex))) {
			isSheepDead = true;
			return false;
		}
		
		if(wolfTraveled.size() == 9) {
			isWolfDead = true;
			return false;
		}
				
		return true;
	}

	public static int goClockWise(int index) {
		if(index == 9)	return 0;
		
		else return (index + 1);
	}
	
	public static int goCounterClockWise (int index) {
		if(index == 0)	return 9;
		
		else return (index - 1);
	}

	public static int getRandomDirection(int index){
	    Random random = new Random();
	    int randNo = random.ints(0,(1+1)).findFirst().getAsInt();
	    if(randNo == 0)	return goClockWise(index);
	    return goCounterClockWise(index);
	}
}
