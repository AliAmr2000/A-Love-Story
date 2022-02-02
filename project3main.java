import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class project3main {
	public static void main(String[] args) throws FileNotFoundException {
	      Scanner in = new Scanner(new File (args[0]));
	      
	      PrintStream out = new PrintStream(new File (args[1]));
	      
	      int timeLimit = in.nextInt();
	      int noCities = in.nextInt();
	      
	      String mID_String = in.next();
	      String lID_String = in.next();
	      mID_String = mID_String.replace("c", "");
	      lID_String = lID_String.replace("c", "");
	      int mID_int = Integer.valueOf(mID_String);
	      int lID_int = Integer.valueOf(lID_String);
	      Map map = new Map(lID_int,noCities,mID_int);
          in.nextLine();
	      ///////////////////////
	      
	      //Reading input file and extracting data from strings
	      while (in.hasNext()) {
	    	String line = in.nextLine();
	    	String [] charStream;
	    	int [] intStream;
	    	if ( line.length() - line.replaceAll("c", "").length() >=1 && line.length() - line.replaceAll("d", "").length() >= 1) {
	    		charStream = line.split(" ");
	    		for (int j = 1; j<charStream.length ; j+=2) {
	    			if (charStream[j].contains("c")) {
	    				int ID = Integer.valueOf(charStream[j].replace("c", ""));
	    				int value = Integer.valueOf(charStream[j+1]);
	    				map.addConnectionsBeforeBridge(map.getLeylaNode(), ID, value);
	    				
	    			}
	    			else {
	    				int ID = Integer.valueOf(charStream[j].replace("d", ""));
	    				int value = Integer.valueOf(charStream[j+1]);
	    				map.addConnectionsAfterBridge(0, ID, value);

	    			}
		    		
		    	}
	    	}
	    	else if (line.contains("d") && !line.contains("c")){
                 line = line.replaceAll("d", "");
 		    	 charStream = line.split(" ");
 		    	 intStream = new int [charStream.length];
 		    	 for (int i = 0; i<charStream.length;i++) {
 		    		intStream[i] = Integer.valueOf(charStream[i]);
 		    	 }
 		    	 for (int j = 1; j<intStream.length ; j+=2) {
 		    		map.addConnectionsAfterBridge(intStream[0], intStream[j], intStream[j+1]);
		    		
 		    	 }

	    		
	    	}
	    	else {
	    		 line = line.replaceAll("c", "");
			     charStream = line.split(" ");
			     intStream = new int [charStream.length];
			     for (int i = 0; i<charStream.length;i++) {
			    	intStream[i] = Integer.valueOf(charStream[i]);	
			     }
			     for (int j = 1; j<intStream.length ; j+=2) {
			    	 map.addConnectionsBeforeBridge(intStream[0] - 1, intStream[j] - 1, intStream[j+1]);
			     }
	    	}
	      }
	      
	      
	      
	      /////////////////////////
	      
	      //Implement Dijkstra
	      map.findMinPath();
	      
	      //Print results 
	      
	      if (map.isPathStatus() == true) {
	    	  int limit = map.getFinalPath().size() - 1;
	    	  for (int counter = 0; counter < limit;counter ++) {
		    	  out.print(map.getFinalPath().get(counter) + " ");
	    	  }
	    	  out.print(map.getFinalPath().get(limit));
		      out.print("\n");
	      }
	      else {
	    	  out.print("-1");
		      out.print("\n");
	      }
	      
	      
	      ///implement Prim
	      
	      map.honneymoonFees();
	      //////////////////////////
	      
	      
	      //Print results
	      if (map.isPathStatus() == true & map.getFinalDistance() <= timeLimit) {
	    	  if (map.isHoneymoonStatus() == true) {
	    		  out.print(map.getFinalFee());
	    	  }
	    	  else {
	    		  out.print("-2");
	    	  }
	      }
	      else {
	    	  out.print("-1");
	      }  
	     /////////////////////
	      in.close();
	      out.close();
	}
}
