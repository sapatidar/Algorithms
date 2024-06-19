/*
   Student Name: Saurabh Patidar
   Date: sept 12, 2023
   Command to compile(Java): javac MergeSort.java
   Program name after compilation: MergeSort
*/
import java.io.*;

public class MergeSort {


    public static void main(String []args) {
        //creating an int array arr from the input file
        
            String inputFile = args[0];
            int arrSize=0;
            int arr[]=null;
         try{
            BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)));
            arrSize = Integer.parseInt(br.readLine());
            arr = new int[arrSize];
            for(int i=0; i<arrSize; i++) {
                arr[i]=Integer.parseInt(br.readLine());
            }
        } catch(IOException e) {
            System.out.println("Error while taking input from file");
        }

        //sorting the array
        mergeSort(arr, 0, arrSize-1);

        //printing result based on presence of second argument
        if(args.length==1) {
            System.out.println(arrSize);
            for(int i=0; i<arrSize; i++) {
                System.out.println(arr[i]);
            }
        } else if(args.length==2) {
            int n = Integer.parseInt(args[1]);
            try{
            System.out.println(arr[n]);
               }catch(Exception e){
            System.out.println("array index not present");
            }
        }

    }

    //method to sort array using merge sort
    static void mergeSort(int arr[], int beginIndex, int endIndex) {

        if((endIndex-beginIndex)>=1) {
            int m = (int)(( beginIndex + endIndex )/2);
            mergeSort(arr, beginIndex, m);
            mergeSort(arr, m+1, endIndex);
            combine(arr, beginIndex, m, endIndex);
        }
    }

    //method combines and sorts the divided arrays
    static void combine(int arr[], int beginIndex, int m, int endIndex) {
        int temp1[] = new int[m-beginIndex+1];
        int temp2[] = new int[endIndex-m];

        //copying the two arrays to temporary arrays
        for(int i=beginIndex; i<=m; i++) {
            temp1[i-beginIndex] = arr[i];
        }

        for(int i=m+1; i<=endIndex; i++) {
            temp2[i-m-1] = arr[i];
        }

        //comparing the elements of two temp arrays and placing in original arr
        int l1=temp1.length;
        int l2=temp2.length;
        int top1=0;
        int top2=0;
        int arrp=beginIndex;
        while(top1<l1 && top2<l2) {
            if(temp1[top1]<=temp2[top2]) {
                arr[arrp]=temp1[top1];
                top1++;
            } else {
                arr[arrp]=temp2[top2];
                top2++;
            }
            arrp++;
        }

        //placing the remaining elements for both temp arrays
        while(top1<l1) {
            arr[arrp]=temp1[top1];
            top1++;
            arrp++;
        }

        while(top2<l2) {
            arr[arrp]=temp2[top2];
            top2++;
            arrp++;
        }
    }
}