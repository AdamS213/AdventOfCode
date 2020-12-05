import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BoardPassFinder {
    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_5th\\src\\Boardpass.txt");
            Scanner myReader = new Scanner(myObj);
            int validPassports = 0;
            int maxID = 0;
            int rowUp = 127;
            int rowDown = 0;
            int colUp = 7;
            int colDown = 0;
            int noOfIDs = (((rowUp+1)*(colUp+1))+colUp);
            boolean[] foundIDs= new boolean[noOfIDs];
            for(int y = 0;y < noOfIDs;y++) {
                foundIDs[y] = false;
            }
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                rowUp = 127;
                rowDown = 0;
                colUp = 7;
                colDown = 0;
                for (int x = 0; x < data.length(); x++) {
                    if(x < data.length() - 3) {
                        if(data.charAt(x) == 'F') {
                            rowUp -= ((rowUp-rowDown)/2 +1);
                        }
                        else if(data.charAt(x) == 'B'){
                            rowDown += ((rowUp-rowDown)/2 +1);
                        }
                    }
                    else {
                        if(data.charAt(x) == 'L') {
                            colUp -= ((colUp-colDown)/2 +1);
                        }
                        else if(data.charAt(x) == 'R'){
                            colDown += ((colUp-colDown)/2 +1);
                        }
                    }
                }
                //by this point the downs and ups are equal and thus either value can be used
                int ID = rowDown*8+colUp;
                if(ID>maxID){
                    maxID = ID;
                }
                foundIDs[ID]=true;

            }
            for(int y = 0;y < noOfIDs;y++) {
                if(!foundIDs[y]) {
                    System.out.println(y);
                }
            }
            System.out.println(maxID);
            myReader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
