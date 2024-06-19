/*
   Student Name: Saurabh Patidar
   Date: sept 25, 2023
   Command to compile(Java): javac Dijkshtra.java
   Program name after compilation: Dijkshtra
*/

import java.io.*;

public class Dijkshtra{
	public static void main(String []args) {

		if(args.length != 3) {
			System.out.print("Invalid number of arguments");
		}else {
			String fileName =args[0];
			int vertices = 0;
			int edges = 0;
			int graph[][] = null;
			try {   
                                //taking input line by line
				BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
				String arr[]= reader.readLine().split(" ");
				vertices = Integer.parseInt(arr[0]);
				edges = Integer.parseInt(arr[1]);
				graph = new int[vertices][vertices];
				for(int i=0; i<vertices; i++) {
					for(int j=0; j<vertices; j++) {
						graph[i][j] = 0;
					}
				}
				
				for(int i=0; i<edges; i++) {
					String arr2[]= reader.readLine().split(" ");
					int x = Integer.parseInt(arr2[0]);
					int y = Integer.parseInt(arr2[1]);
					int distance = Integer.parseInt(arr2[2]);
					graph[x][y] = distance;
					graph[y][x] = distance;
				}
			}catch(IOException e) {
				System.out.print("Invalid input file data");
			}
			if(graph != null) {
				calculateShortestDistance(args[1], args[2], vertices, edges, graph);
			}
		}
	}

	private static void calculateShortestDistance(String start, String end, int vertices, int edges,
			int[][] graph) {
		int distanceFromStart[][] = new int[vertices][2];
		for(int i=0; i<vertices; i++) {
			distanceFromStart[i][0] = Integer.MAX_VALUE;
			distanceFromStart[i][1] = 0; //unvisited vertices are indicated with 0 value
		}
		int i =Integer.parseInt(start);
		distanceFromStart[i][0] = 0;
		
		while((i=nearestIndex(distanceFromStart, vertices)) != -1) {
			for(int k=0; k<vertices; k++) {
				
				if(graph[i][k] != 0
				   &&distanceFromStart[k][1]==0
				   &&(graph[i][k]+distanceFromStart[i][0])< distanceFromStart[k][0]){
					
					distanceFromStart[k][0]= graph[i][k]+distanceFromStart[i][0];
				}
			}
			distanceFromStart[i][1]=1;//means vertex is visited
		}
		
		int targetNode =Integer.parseInt(end);
		if(distanceFromStart[targetNode][0] != Integer.MAX_VALUE) {
			System.out.print(distanceFromStart[targetNode][0]);
		}else {
			System.out.print("Path does not exist between given start and end nodes");
		}
	}

	private static int nearestIndex(int[][] distanceFromStart, int vertices) {
		int nearestNode = -1;
		int distance = Integer.MAX_VALUE;
		for(int i=0; i<vertices; i++) {
			if(distanceFromStart[i][1]==0 && (distanceFromStart[i][0]< distance)) {
                                distance=distanceFromStart[i][0];
				nearestNode = i;
			}
		}
		return nearestNode;
	}
}