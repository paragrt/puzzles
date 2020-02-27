package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FindNum {

	
		//finds all matching pairs except the 2 nbrs that are not
	
		public static void main (String[] args) {
			//code
			
			Scanner sc = new Scanner(System.in);
			int numTests = sc.nextInt();
			for(int i = 0; i < numTests; i++)
			{
			    int numInts = ( sc.nextInt() * 2 ) + 2;
			    int[] arr = new int[numInts];
			    for(int j = 0; j < numInts;j++)
			    {
			        arr[j] = sc.nextInt();
			    }
			    findNum(arr);
			    
			}
		}
		
		static void findNum(int[] arr) {
		    
		    HashSet<Integer> set = new HashSet<>();
		    for(int i = 0; i < arr.length; i++)
		    {
		        if ( !set.contains(arr[i]) ) {
		            set.add(arr[i]);
		        } else {
		            set.remove(arr[i]);
		        }
		    }
		    List<Integer> list = new ArrayList<Integer>(set);
		    Collections.sort(list);
		    System.out.println(list.get(0) + " " + list.get(1) );
		    
		}
	

}
