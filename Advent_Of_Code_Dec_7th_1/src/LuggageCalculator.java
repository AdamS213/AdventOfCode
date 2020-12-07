import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;



public class LuggageCalculator {
    public LuggageCalculator() {
        try {
            File myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_7th_1\\src\\Luggage.txt");
            Scanner myReader = new Scanner(myObj);
            HashMap<String,Bag<String>> luggage = new HashMap<String,Bag<String>>();
            List<Bag<String>> bags = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String parentColour = data.substring(0,data.indexOf("bags")).trim();
                Bag<String> bag = new Bag<String>(parentColour);
                luggage.put(parentColour,bag);
                bags.add(bag);
            }
            myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_7th_1\\src\\Luggage.txt");
            myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String parentColour = data.substring(0,data.indexOf("bags")).trim();

                String tempChildren = data.substring(data.indexOf("bags"), data.length() - 1);

                if (!tempChildren.contains("no")) {
                    tempChildren = tempChildren.substring(tempChildren.indexOf("contain") + 8);
                    if (tempChildren.contains(",")) {
                        String[] tc = tempChildren.split(",");
                        for (String s : tc) {
                            luggage.get(parentColour).addChild(luggage.get(s.substring(2,s.indexOf("bag")).trim()));
                        }
                    }
                    else {
                        luggage.get(parentColour).addChild(luggage.get(tempChildren.substring(2,tempChildren.indexOf("bag")).trim()));
                    }
                }
            }
            findBags(bags);

        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
    public void findBags(List<Bag<String>> b) {
        int count = 0;
        for (Bag<String> bb : b ) {
            System.out.println(bb.getData());
            if(bb.containsData("shiny gold")) {
                count++;
            }
        }
        System.out.println(count);
    }


}

