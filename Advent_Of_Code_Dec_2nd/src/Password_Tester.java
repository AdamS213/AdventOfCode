import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Password_Tester {
    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_2nd\\src\\Passwords.txt");
            Scanner myReader = new Scanner(myObj);
            int validPasswords = 0;
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                int s = data.indexOf(":");
                String policy = data.substring(0,s);
                String password = data.substring(s+2,data.length());

                char policyChar = policy.charAt(policy.length()-1);
                boolean firsthalf = true;
                String min = "";
                String max = "";

                for(int x = 0; x < policy.length()-1;x++) {
                    if(firsthalf && (policy.charAt(x)!= '-') ) {
                        min = min + policy.charAt(x);
                    }
                    else if(policy.charAt(x)== '-') {
                        firsthalf = false;
                    }
                    else if(policy.charAt(x) != ' ') {
                        max = max + policy.charAt(x);
                    }
                }

                int iMax = Integer.parseInt(max);
                int iMin = Integer.parseInt(min);



                if((password.charAt(iMin-1)== policyChar) ^ (password.charAt(iMax-1 )== policyChar)) {
                    validPasswords++;
                }

            }
            myReader.close();
            System.out.println(validPasswords);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
