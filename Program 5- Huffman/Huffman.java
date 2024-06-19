/*
   Student Name: Saurabh Patidar(B01039148)
   Date: oct 16, 2023
   Command to compile(Java): javac Huffman.java
   Program name after compilation: Huffman
*/

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman{
	public static void main(String[]args) {
		PriorityQueue<HeapElement> heap = new PriorityQueue<>(100, new FrequencySort());
		int letters = 0;
		BufferedReader br;
		//take input in priority queue
		try {
			br = new BufferedReader(new FileReader(new File(args[0])));
			letters = Integer.parseInt(br.readLine());
			for(int i=0; i<letters; i++) {
				HeapElement hp = new HeapElement();
				hp.key = i;
				hp.frequency = Integer.parseInt(br.readLine());
				heap.add(hp);
			}
		} catch (FileNotFoundException e) {
			System.out.print("File not found/invalid file name");
		} catch (NumberFormatException e) {
			System.out.print("Invaid data in file");
		} catch (IOException e) {
			System.out.print("Error while reading input from file");
		}
		
		int parent[][] = buildTree(heap, letters);
		System.out.print(calculateBits(parent, letters));
		
	}

	private static int calculateBits(int[][] parent, int letters) {
		//calculate bits based on frequencies of newly added elements of tree
		int bits = 0;
		for(int i=letters; i<parent.length; i++) {
			bits = bits + parent[i][2];
		}
		return bits;
	}

	private static int[][] buildTree(PriorityQueue<HeapElement> heap, int letters) {
		//parent array has parentindex ,left/right child indicator and frequencies
		int parent[][] = new int[2*letters-1][3];
		int newKey = letters;
		while(heap.size() > 1) {
			HeapElement h1 = heap.remove();
			HeapElement h2 = heap.remove();
			HeapElement h3 = new HeapElement();
			h3.key = newKey;
			newKey++;
			h3.frequency = h1.frequency + h2.frequency;
			heap.add(h3);
			parent[h3.key][2] = h3.frequency;
			if(h1.frequency < h2.frequency) {
				parent[h1.key][1] = 0;
				parent[h2.key][1] = 1;
			}else {
				parent[h1.key][1] = 1;
				parent[h2.key][1] = 0;
			}
			parent[h1.key][0] = h3.key;
			parent[h2.key][0] = h3.key;
		}
				
		return parent;
	}
}

class HeapElement{
	int key;
	int frequency;
}

class FrequencySort implements Comparator<HeapElement>{
	//comparator to sort based on frequencies
	public int compare(HeapElement h1, HeapElement h2) {
		return h1.frequency - h2.frequency;
	}
}