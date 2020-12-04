import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class PassportValidator {
    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_4th\\src\\Passports.txt");
            Scanner myReader = new Scanner(myObj);
            int validPassports = 0;
            int count = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals("")) {
                    if (count == 7) {
                        validPassports++;
                    }
                    count = 0;
                    continue;
                }
                String[] tokens = data.split(":|\\ ");
                for(int x = 0; x < tokens.length; x+=2) {
                    String value = tokens[x+1];
                    int iValue = 0;
                    try {
                        iValue = Integer.parseInt(value);
                    }
                    catch (NumberFormatException e) {
                        iValue = 0;
                    }
                    switch (tokens[x]) {
                        case"byr" :
                            if (iValue >= 1920 && iValue <= 2002) {
                                count++;
                            }
                            break;
                        case"iyr" :
                            if (iValue >= 2010 && iValue <= 2020) {
                                count++;
                            }
                            break;
                        case"eyr" :
                            if (iValue >= 2020 && iValue <= 2030) {
                                count++;
                            }
                            break;
                        case"hgt" :
                            if(value.length() <= 2) {
                                break;
                            }
                            int m1 = value.length() - 2;
                            String h = value.substring(0,m1);
                            int height = Integer.parseInt(h);
                            if ((value.charAt(m1) == 'c' && value.charAt(value.length() - 1) == 'm') && (height >= 150 && height <= 193)) {
                                count++;
                                break;
                            }
                            else if (value.charAt(m1) == 'i' && value.charAt(value.length() - 1) == 'n'&& (height >= 59 && height <= 76)) {
                                count++;
                                break;
                            }
                            break;
                        case"hcl" :
                            char[] l = {'a','b', 'c' ,'d' ,'e' ,'f','0','1', '2' ,'3' ,'4' ,'5' ,'6','7','8','9'};
                            List<Character> legalChar = new ArrayList<>();
                            for (char ch: l) {
                                legalChar.add(ch);
                            }
                            if(value.charAt(0) == '#' && value.length() == 7) {
                                for(int y = 1; y < value.length();y++) {
                                    if (!legalChar.contains(value.charAt(y))) {
                                        break;
                                    }
                                }
                                count++;
                                break;
                            }

                        case"ecl" :
                            String[] c = {"amb","blu", "brn" ,"gry" ,"grn" ,"hzl" ,"oth"};
                            List<String> colours = Arrays.asList(c);
                            if (colours.contains(value)) {
                                count++;
                            }
                            break;
                        case"pid" :
                            if (value.length() == 9) {
                                count++;
                            }
                            break;
                    }
                }
            }
            myReader.close();
            if (count == 7) {
                validPassports++;
            }
            System.out.println(validPassports);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
