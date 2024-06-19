/*
   Student Name: Saurabh Patidar(B01039148)
   Date: oct 06, 2023
   Command to compile(Java): javac Kruskal.java
   Program name after compilation: Kruskal
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Kruskal{
	public static void main(String[]args) {
		int dots = 0;
		int e = 0;
		String edge[] = null;
		Integer edgesArray[][] = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
			String verticesEdges[] = br.readLine().split(" ");
			dots = Integer.parseInt(verticesEdges[0]);
			e = Integer.parseInt(verticesEdges[1]);

			//taking input for edges
			edgesArray = new Integer[e][3];
			for(int i=0; i<e; i++) {
				edge = br.readLine().split(" ");
				edgesArray[i][0]=Integer.parseInt(edge[0]);
				edgesArray[i][1]=Integer.parseInt(edge[1]);
				edgesArray[i][2]=Integer.parseInt(edge[2]);
				
			}
                        //sorting edges
			Arrays.sort(edgesArray, (length1,length2)-> 
			                         compareLength(length1[2],length2[2]));
			
		} catch (FileNotFoundException ex) {
			System.out.print("File not found");
		} catch (IOException e1) {
			System.out.print("Invalid file data");
		}
		
		ArrayList<HashSet<Integer>> setList = initializeList(dots);
		System.out.print(calculateCost(e,edgesArray,setList));
	}
	
	static int calculateCost(int e, Integer[][] edgesArray, ArrayList<HashSet<Integer>> setList) {
		int cost = 0;
		for(int i=0; i<e; i++) {
			int index1 = 0;
			int index2 = 0;
                        //finding sets for both vertices
			for(int j=0; j<setList.size(); j++) {
				if(setList.get(j).contains(edgesArray[i][0]))
					index1 = j;
				if(setList.get(j).contains(edgesArray[i][1]))
					index2 = j;
			}
			//comapring index and if unequal perform union
			if(index1!=index2) {
				setList.get(index1).addAll(setList.get(index2));
				setList.remove(index2);
				cost = cost + edgesArray[i][2];
			}
		}
		return cost;
	}

	static ArrayList<HashSet<Integer>> initializeList(int dots) {
                //initializing arraylist with hashsets equal to number of vertices and adding the same one vertex in each hashset
		ArrayList<HashSet<Integer>> setList = new ArrayList<HashSet<Integer>>();
		for(int i=0; i<dots; i++) {
			HashSet<Integer> h = new HashSet<>();
			h.add(i);
			setList.add(h);
		}
		return setList;
	}

	static int compareLength(Integer length1, Integer length2) {
		if(length1==length2) return 0;
        if(length1<length2) return -1;
        else return 1;
	}
}