import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class GroupAnswerCounter {
    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_6th\\src\\Answers.txt");
            Scanner myReader = new Scanner(myObj);
            int[] answers = new int[26];
            for(int y = 0;y < 26;y++) {
                answers[y] = 0;
            }
            int count = 0;
            int personCount = 0;
            int finalCount = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals("")) {
                    for(int y = 0;y < 26;y++) {
                        if(answers[y] == personCount) {
                            count++;
                        }
                    }
                    finalCount += count;
                    for(int y = 0;y < 26;y++) {
                        answers[y] = 0;
                    }
                    personCount = 0;
                    count = 0;
                }
                else {
                    personCount++;
                    for(int i = 0; i < data.length();i++) {
                        int letter = ((int) data.charAt(i)) - 97;
                        answers[letter]++;
                    }
                }
            }
            for(int y = 0;y < 26;y++) {
                if(answers[y] == personCount) {
                    count++;
                }
            }
            finalCount += count;
            System.out.println(finalCount);
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
