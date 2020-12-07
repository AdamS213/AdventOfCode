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
            File myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_7th_2\\src\\Luggage.txt");
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
            myObj = new File("C:\\Users\\Adam\\Desktop\\Advent_Of_Code_Dec_7th_2\\src\\Luggage.txt");
            myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String parentColour = data.substring(0,data.indexOf("bags")).trim();

                String tempChildren = data.substring(data.indexOf("bags"), data.length() - 1).trim();

                if (!tempChildren.contains("no")) {
                    tempChildren = tempChildren.substring(tempChildren.indexOf("contain") + 8);
                    int bagAmount = 0;
                    String childColour;
                    if (tempChildren.contains(",")) {
                        String[] tc = tempChildren.split(",");
                        for (String s : tc) {
                            s=s.trim();
                            bagAmount = Integer.parseInt(s.substring(0,1));
                            childColour = s.substring(2,s.indexOf("bag")).trim();
                            luggage.get(parentColour).addChild(luggage.get(childColour));
                            luggage.get(parentColour).setMap(bagAmount,childColour);

                        }
                    }
                    else {
                        bagAmount = Integer.parseInt(tempChildren.substring(0,1));
                        childColour = tempChildren.substring(2,tempChildren.indexOf("bag")).trim();
                        luggage.get(parentColour).addChild(luggage.get(childColour));
                        luggage.get(parentColour).setMap(bagAmount,childColour);
                    }


                }
            }
            System.out.println(bagsContained(luggage.get("shiny gold")));


        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int bagsContained(Bag<String> b) {
        int count = 0;
        List<Bag<String>> kids = b.getChildren();
        if(!b.isLeaf()) {
            for (Bag<String> k : kids) {
                count += (b.getMap(k.getData()) *bagsContained(k)) + b.getMap(k.getData());
            }
        }

        return count;
    }


}

