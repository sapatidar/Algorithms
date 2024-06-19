/*
   Student Name: Saurabh Patidar(B01039148)
   Date: Nov 19, 2023
   Command to compile(Java): javac MaxFlow.java
   Program name after compilation: MaxFlow
*/


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MaxFlow{
	static int nodes =0;
	static int edges =0;
	static ArrayList<HashMap<Integer,Integer>> connects = null;
	public static void main(String[]args) {
		//taking input
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(args[0])));
			String line[] = reader.readLine().split(" ");
			nodes = Integer.parseInt(line[0]);
			edges = Integer.parseInt(line[1]);
			connects = new ArrayList<HashMap<Integer,Integer>>();
			for(int i=0; i<nodes; i++) {
				connects.add(i, new HashMap<>());
			}
			for(int i=0; i<edges; i++) {
			  String linee[] = reader.readLine().split(" ");
			  int x = Integer.parseInt(linee[0]);
			  int y = Integer.parseInt(linee[1]);
			  int z = Integer.parseInt(linee[2]);
			  HashMap<Integer,Integer> map = connects.get(x);
			  map.put(y, z);
			  connects.set(x, map);
			  map = connects.get(y);
			  map.put(x, 0);
			  connects.set(y, map);
			}
		} catch (IOException e) {
			System.out.print("Invalid input file or data");
			return;
		}
		
		System.out.print(calculateMaxFlow());
	}
	
	static int min =0;
	public static int calculateMaxFlow() {
		//calculate max flow
		int parent[] = new int[nodes];
		int result =0;
		while(pathPresent(parent)) {
			updateConnectionsAndMin(parent);
			result = result + min;
			min = Integer.MAX_VALUE;
		}
		return result;
	}
	public static void updateConnectionsAndMin(int[] parent) {
		//finding min and updating residue values edges and reverse edges
		int par = nodes-1;
		while(par!=0) {	
			int t = parent[par];			
			min = Math.min(min, connects.get(t).get(par));
			par = parent[par];

		}
		par = nodes-1;
		while(par!=0) {	
			int t = parent[par];			
			HashMap<Integer, Integer> h = connects.get(t);
			h.put(par, h.get(par)-min);
			connects.set(t, h);
			h = connects.get(par);
			h.put(t, h.get(t)+min);
			connects.set(par, h);
			par = parent[par];

		}
		
	}

		static boolean pathPresent(int[] parent)
	    {
			//bfs search return true after updating parents array 
			//if path to last node found
	        int visited[] = new int[nodes];
	        for (int i = 0; i < nodes; ++i) {
	        	visited[i]=0;
	        }

	        LinkedList<Integer> list = new LinkedList<Integer>();
	        list.add(0);
	        visited[0] = 1;
	        parent[0] = -1;
	 
	        while (list.size() != 0) {
	            int a = list.poll();
	
	            for (int b = 0; b < nodes; b++) {
		            int temp = 0;
		            try {temp = connects.get(a).get(b);}catch(Exception e) {temp=0;}
		            
	                if (visited[b] == 0
	                    && (temp > 0)) {
	                    
	                    if (b == (nodes-1)) {
	                        parent[b] = a;
	                        return true;
	                    }
	                    list.add(b);
	                    parent[b] = a;
	                    visited[b] = 1;
	                }
	            }
	        }
	        return false;
	}
}
