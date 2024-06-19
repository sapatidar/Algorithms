/*
   Student Name: Saurabh Patidar(B01039148)
   Date: Nov 19, 2023
   Command to compile(Java): javac MaxFlow.java
   Program name after compilation: MaxFlow
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class ConvexHull{
	static int coOrdinates[][] = null;
	public static void main(String[]args) {
		int n = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
			n = Integer.parseInt(br.readLine());
			if(n<3) {
				System.out.print("Convex Hull not possible");
				return;
			}
			coOrdinates = new int[n][2];
			for(int i=0; i<n; i++) {
				String input[] = br.readLine().split(" ");
				coOrdinates[i][0] = Integer.parseInt(input[0]);
				coOrdinates[i][1] = Integer.parseInt(input[1]);
			}
		} catch (FileNotFoundException e) {
			System.out.print("File not found");
			return;
		} catch (NumberFormatException e) {
			System.out.print("Invalid data in file");
			return;
		} catch (IOException e) {
			System.out.print("Error while taking input from file");
			return;
		}
		
			System.out.print(calculateConvexHull());
		
	}

	public static int calculateConvexHull() {

		//Finding the lowest, leftmost point
		int lowest=0;
		for(int i=0; i<coOrdinates.length; i++) {
			if(coOrdinates[i][1]<coOrdinates[lowest][1]) {
				lowest = i;
			}
			
			if(coOrdinates[i][1] == coOrdinates[lowest][1] 
					&& coOrdinates[i][0]<coOrdinates[lowest][0]) {
				lowest = i;
			}
		}
		//Bring the point to index 0
		int temp[] = coOrdinates[0];
		coOrdinates[0] = coOrdinates[lowest];
		coOrdinates[lowest] = temp;
		
		//sorting remaining n-1 coordinates based on crossProductComparator
		Arrays.sort(coOrdinates,1,coOrdinates.length, (a,b)->compare(a,b));
		
		//removing all points lying on path to a farther point
		int n = 1;
		   for (int i=1; i<coOrdinates.length; i++)
		   {
		       while ((i < coOrdinates.length-1) 
		    		   && getDirection(coOrdinates[0] ,
		    				   coOrdinates[i],coOrdinates[i+1]) == 0) { i++; }
		       coOrdinates[n] = coOrdinates[i];
		       n++;
		   }
		if(n<3) return 0;
		
		Stack<Integer> s = new Stack<>();
		s.push(0);
		s.push(1);
		s.push(2);
		for (int i = 3; i < n; i++)
		   {
		      while (s.size()>1 && getDirection(coOrdinates[s.elementAt(s.size()-2)], 
		    		  coOrdinates[s.peek()], coOrdinates[i])>=0)
		         s.pop();
		         s.push(i);
		   }
		
		return s.size();
	}
	
	public static int compare(int[] a, int[] b) {
		
		int x0 = coOrdinates[0][0];
		int y0 = coOrdinates[0][1];
		int direction = getDirection(coOrdinates[0],a, b);
		int distanceSquareA = (x0 - a[0])*(x0 - a[0]) +
		          (y0 - a[1])*(y0 - a[1]);
		int distanceSquareB = (x0 - b[0])*(x0 - b[0]) +
		          (y0 - b[1])*(y0 - b[1]);
		
		if(direction==0) {
			if(distanceSquareB >= distanceSquareA) {
				return -1;
			}else {
				return 1;
			}
		}
		if(direction<0) {
			return -1;
		}else {
			return 1;
		}
		
	}
	
	public static int getDirection(int start[], int a[], int b[]) {
		int x0 = start[0];
		int y0 = start[1];
		int direction =(a[1] - y0) * (b[0] - a[0]) -
	              (a[0] - x0) * (b[1] - a[1]);
		
		return direction;
	}
}
