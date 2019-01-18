import java.io.*;
import java.util.ArrayList;

public class main{




    public static void delay(String in, String out, int delay, int fps) throws IOException,InvalidNumberException {
        
            ArrayList<Integer> starts = new ArrayList<Integer>();
            ArrayList<Integer> stops = new ArrayList<Integer>();
            ArrayList<String> subs = new ArrayList<String>();

            String line;
            FileInputStream fstream = new FileInputStream(in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));

            double seconddelay = delay / 1000;
            double framedelay = fps * seconddelay;

            int rounded = (int) framedelay;

            int linenumber = 0;

            while ((line = reader.readLine()) != null) {
                linenumber++;
                String[] characters = line.split("");
                String startstring = "";
                String stopstring = "";
                String sub = "";

                int iterator = 1;
                while (iterator < characters.length && !characters[iterator].equals("}")) {
                    startstring = startstring + characters[iterator];
                    iterator++;
                }
                iterator = iterator + 2;

                while (iterator < characters.length && !characters[iterator].equals("}")) {
                    stopstring = stopstring + characters[iterator];
                    iterator++;
                }

                iterator++;

                while (iterator < characters.length) {
                    sub = sub + characters[iterator];
                    iterator++;
                }

                if (!startstring.matches("[0-9]+") || !stopstring.matches("[0-9]+")) {
                    throw new InvalidNumberException("Starting and ending frames may only contain numbers from 0 to 9! InvalidNumberException at line " + linenumber + ": " + line);
                }

                if (Integer.parseInt(startstring) > Integer.parseInt(stopstring)) {
                    throw new InvalidNumberException("Starting frame can't have higher value than ending frame! InvalidNumberException at line " + linenumber + ": " + line);
                }


                int changedstart = Integer.parseInt(startstring) + rounded;
                int changedstop = Integer.parseInt(stopstring) + rounded;

                starts.add(changedstart);
                stops.add(changedstop);
                subs.add(sub);


                System.out.println(changedstart + " " + changedstop);


            }


    }


    public static void main(String[] args) throws IOException {
        try {
            delay("/C:/Users/Jan/Documents/gravity.txt", "/C:/Users/Jan/Documents/gravity2.txt", 1000, 60);
        }
        catch (InvalidNumberException e){
            System.out.println(e.getMessage());

        }




    }
}
