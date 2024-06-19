/*
   Student Name: Saurabh Patidar(B01039148)
   Date: Nov 08, 2023
   Command to compile(Java): javac FerryLoading.java
   Program name after compilation: FerryLoading
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FerryLoading{
	public static void main(String[]args) {
		int cars =0;
		int ferry = 0;
		int length_of_car[]=null;
		//taking input
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
			String temp[] = br.readLine().split(" ");
			cars = Integer.parseInt(temp[0]);
			ferry = Integer.parseInt(temp[1]);
			length_of_car = new int[cars];
			for(int i=0; i<cars; i++) {
				length_of_car[i] = Integer.parseInt(br.readLine());
			}
		} catch (IOException e) {
			//if file not present or file data is invalid print error and return
			System.out.print("Input file invalid");
			return;
		}
		//if file is valid then proceed further
        int[] consumed = new int[cars+1];
        
        //creating an array that stores total space consumed till ith car
        for (int i = 1; i < cars+1; i++) {
            consumed[i] = consumed[i-1] + length_of_car[i-1];
        }
		
        //creating array that stores previous info
		int[][] arr = new int[cars+1][ferry+1];
        for (int i = 1; i < cars+1; i++) {
            for (int j = 0; j < ferry+1; j++) {
                if (length_of_car[i-1] <= j) {
                	int temp = arr[i-1][j-length_of_car[i-1]] + length_of_car[i-1];
                	if(temp > arr[i-1][j]) {
                    arr[i][j] = temp;
                    }else {
                    arr[i][j] = arr[i-1][j];
                    }
                } else {
                    arr[i][j] = arr[i-1][j];
                }
            }
        }
        
        
        int result =0;
        //checking last column of arr
        for (int i = cars; i >= 1; i--) {
            if (consumed[i] - arr[i][ferry] <= ferry) {
                result=i;
                break;
            }
        }
        
        //printing results
        System.out.println(result);
        
        int temp = ferry;
        for (int i = cars; i >= 1; i--) {
        	if(result==0)
        		break;
            if (arr[i][temp] != arr[i-1][temp]) {
                System.out.println(length_of_car[i-1] + "-Left");
                temp = temp - length_of_car[i-1];
                result--;
                continue;
            }
                System.out.println(length_of_car[i-1] + "-Right");
                result--;
        }
		
	}
}