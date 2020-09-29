import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

//Chase Faine
//
//Fall 2020
public class Lab1 {
    public static void main(String[] args) {
        //create the original array of gems including a menu to ask the user how they want the number of gems to be entered.
        
        //this is the random number generator.
        Random random = new Random();
        int randomNumber = random.nextInt();
        //creating the original array of gems.
        int[] original = {1};
        //Ask the user what option they would like to insert gem info from.
        Scanner menu = new Scanner(System.in);
        System.out.println("Would you like to enter how many gems are in the bag (enter '1'), " +
            "or read the name of a file in the program (enter '2')?");
        int answer = menu.nextInt();
        //creating the size of the gem array.
        int size = 0;
        //if they choose to manually enter the amount of gems, execute this. this will recreate the original array with the new size that the user entered and insert it 
        //with random values between 50,000.
        if (answer == 1) {
            Scanner gemCount = new Scanner(System.in);
            System.out.println("How many gems are in the bag? Must be less than or equal to 50000");
            size = gemCount.nextInt();
            original = new int[size];
            if (size <= 50000) {
                //this stores random values between 50,000 into the original array.
                for (int i = 0; i < size; i++) {
                    randomNumber = random.nextInt(50001);
                    original[i] = randomNumber;    
                }
            }
            else {
                System.out.println("An error occured. Please try running the program again.");
                return;
            }
        }
        //if they choose to enter a file name with the amount of gems and range of values, execute this. this will recreate the original array by setting its size
        //to the first number listed in the file, and then updating the range of values to the second number listed in the file.
        else if (answer == 2) {
            Scanner fileName = new Scanner(System.in);
            System.out.println("What's the name of the file? The file must contain the number of gems in the bag followed by the range of gem values with a space between them.");
            String name = fileName.nextLine();
            try {
                File file = new File(name);
                Scanner reader = new Scanner(file);
                String info = reader.nextLine();
                System.out.println("\nFile: ");
                System.out.println(info);
                //this splits the numbers in the file (which is a string in this case) into 2 seperate pieces so that we can now convert them into 2 seperate integers.
                String[] data = info.split(" ");
                int[] numbers = new int[data.length];
                //this converts the file (which is a string in this case) into integers and puts it into an array in which we can access to update the values in the original array.
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = Integer.parseInt(data[i]);
                }
                size = numbers[0];
                int values = numbers[1];
                original = new int[size];
                //this stores the new random values into the original array.
                for (int i = 0; i < size; i++) {
                    randomNumber = random.nextInt(values + 1);
                    original[i] = randomNumber;
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occured. Please try running the program again.");
                return;
            }
        }
        //if instructions aren't followed correctly, end the program.
        else {
            System.out.println("An error occured. Please try running the program again.");
            return;
        }

        //print the original array of gems.
        System.out.println("\nGem Values:");
        System.out.println(Arrays.toString(original) + "\n");
        
        //divide the original array into nearly equal sums.
        ArrayList<Integer> firstGroup = new ArrayList<>();
        ArrayList<Integer> secondGroup = new ArrayList<>();
        int sum1 = 0;
        int sum2 = 0;
        //This just checks if one of the groups sums is larger than the other and divides them accordingly to be as equal as possible.
        for (int i = 0; i < size; i++) {
            if (sum1 < sum2) {
                firstGroup.add(original[i]);
                sum1 += original[i];
            }
            else {
                secondGroup.add(original[i]);
                sum2 += original[i];
            }
        }

        //print the first group of gems and its sum.
        System.out.println("Pile #1:");
        System.out.println(firstGroup.toString() + "\n" + "Sum: " + sum1 + "\n");
        
        //print the second group of gems and its sum.
        System.out.println("Pile #2:");
        System.out.println(secondGroup.toString() + "\n" + "Sum: " + sum2 + "\n");   
    }
}
