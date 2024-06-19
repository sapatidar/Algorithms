/* Student Name: Saurabh Patidar
   Date: sept 3, 2023
   Command to compile(Java): javac Euclid.java
   Program name after compilation: Euclid
*/
public class Euclid {

    public static void main(String []args) {
        int larger = Integer.parseInt(args[0]);
        int smaller = Integer.parseInt(args[1]);
        System.out.print(calGcd(larger, smaller));
    }
    
    //method returns Euclid's greatest common divisor for two integer arguments
    private static int calGcd(int larger, int smaller) {
        if(smaller==0) {
            return larger;
        }
        return calGcd(smaller, larger%smaller);
    }
}