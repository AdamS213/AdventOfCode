import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MapReader {
    public static void main(String[] args) {
        try {

            int trees1 = 0;
            int trees2 = 0;
            int trees3 = 0;
            int trees4 = 0;
            int trees5 = 0;


            for(int slope = 1; slope <= 5;slope++) {
                File myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_3rd\\src\\Map.txt");
                Scanner myReader = new Scanner(myObj);
                int position = 0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.charAt(position) == '#') {
                        switch (slope) {
                            case 1:
                                trees1++;
                                break;
                            case 2:
                                trees2++;
                                break;
                            case 3:
                                trees3++;
                                break;
                            case 4:
                                trees4++;
                                break;
                            case 5:
                                trees5++;
                                break;
                        }
                    }
                    switch (slope) {
                        case 1:
                            position++;
                            break;
                        case 2:
                            position += 3;
                            break;
                        case 3:
                            position += 5;
                            break;
                        case 4:
                            position += 7;
                            break;
                        case 5:
                            position++;
                            if(myReader.hasNextLine()) {
                                myReader.nextLine();
                            }
                            break;
                    }

                    if (position >= data.length()) {
                        position -= data.length();
                    }
                }
                myReader.reset();
            }
            int trees = trees1*trees2*trees3*trees4*trees5;
            System.out.println(trees);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
