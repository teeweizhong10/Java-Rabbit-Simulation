import java.util.*;
import java.io.*;
import java.lang.Math;
/**
* This program simulates the growth of a bunny population starting with a fixed amount of does and bucks.
* It inherits the properties of a rabbit from the class Rabbit.
* @author Wei Zhong Tee
* @since 16 February 2020
*/
public class RabbitSimulation {
  private int count;
  private int[] does;
  private int[] bucks;

  /**
  * This following is a get and set method used for getting the amount of numbers that are passed in from the textfile.
  * It is important so the program knows how many times to run.
  * @param a the number passed in is the size of the array that holds all the elements.
  * @return int This returns the count that was set based on the size of the array.
  */
  public void setCount(int a) {
    count = a;
  }

  public int getCount() {
    return count;
  }

  /**
  * This method checks if the rabbit is a male or female.
  * It is not used for differenciating the genders but for identifying genders later in the program.
  * @param x is the number that indicates the rabbit's sex.
  * @return int This returns a number that helps other parts of the program determine if it is dealing with a buck or doe.
  */
  public int determineGender(int x) {
    int flag = 0;
    if (x == 0) {
      flag = 0;
    }

    else if (x == 1) {
      flag = 1;
    }

    else if (x%2 == 0) {
      flag = 0;
    }

    else if (x%2 != 0){
      flag = 1;
    }

    return flag;
  }

  /**
  * This method reads the text file and returns an array that holds the contents of the text file.
  * It uses a try and catch incase the file cannot be read or an invalid file name is passed in. The program ends if a text file cannot be properly read..
  * @param file The string that is the parameter takes in the name of the textfile from the command line.
  * @return int This returns the array of the content that is in the textfile.
  */
  public int[] readFile(String file) {
    try {
      Scanner s = new Scanner(new File(file));
      int ctr = 0;

      while(s.hasNextInt()) {
        ctr ++;
        s.nextInt();
      }
      setCount(ctr);
      int[] arr = new int[ctr];
      Scanner s1 = new Scanner(new File(file));
      for (int x = 0; x < arr.length; x++) {
        arr[x] = s1.nextInt();
      }

      return arr;
    } catch(Exception e) {
      System.out.print("Error reading file. Program is ending.");
      System.exit(0);
      return null;
    }
  }

  /**
  * This is the main method. All the logic and code for the simulation process and repeated trials is found here.
  * The first few loops are used to ensure the each set of numbers are ran in a trial 10 times.
  * A loop is ran 365 to simulate a year, where before ageing a set of conditions check is a doe can give birth, and if so, random numbers will be used to determine the litter size and the genders of the litters.
  * To ensure that new litters start from age 0, the incrementing of ages takes the rabbit's current age to increment it.
  * Rabbit property functions are all called from the Rabbit.java file.
  */
  public static void main(String[] args) {
    RabbitSimulation trial = new RabbitSimulation();
    trial.readFile(args[0]);
    int[] num = trial.readFile(args[0]);
    int[] does = new int[num.length/2];
    int[] bucks = new int[num.length/2];
    int counter_x = 0;
    int counter_y = 0;
    for (int x = 0; x < num.length; x++) {
      if (trial.determineGender(x) == 0) {
        does[counter_x] = num[x];
        counter_x++;
      }

      else if (trial.determineGender(x) == 1) {
        bucks[counter_y] = num[x];
        counter_y++;
      }
    }


    ArrayList <Rabbit> population = new ArrayList <Rabbit> ();
    ArrayList <Integer> age = new ArrayList<Integer>();
    ArrayList <Integer> pregnancy_days = new ArrayList<Integer>();
    int a = 0;
    int[][] arr = new int[10][3];
    Rabbit run = new Rabbit();
    int trial_sets = 0;
    int running_sim = 1;
    int doe_population = 0;
    int buck_population= 0;
    int run_once = 1;
    int baby_count = 0;
    String baby_sex;
    int doe_count = 0;
    int buck_count = 0;
    int preg_days = 0;
    int tentimecount = 0;
    int size = 0;
    int counter = 0;

    while (running_sim == 1) {
      tentimecount = 0;
      counter = 0;
      while (size < does.length) {
        System.out.println("Stating with " + does[size] + " doe(s) and " + bucks[size] + " buck(s):");
        break;
      }
      size++;
          while (tentimecount < 1000) {
            for (int x = 0; x < does.length; x++) {
              if (x == trial_sets) {
                for (int d = 0; d < does[x]; d++) {
                  preg_days = run.getGestationPeriod();
                  population.add(new Rabbit("F"));
                  pregnancy_days.add(preg_days);
                  doe_count++;
                }
                for (int b = 0; b < bucks[x]; b++) {
                  population.add(new Rabbit("M"));
                  buck_count++;
                }
              }

              for (int ageing = 0; ageing < population.size(); ageing++) { //ageing process
                age.add(0); // add age "0" to ArrayList
              }


            for (int year = 0; year < 365; year++) {
              //System.out.println("*** YEAR: " + year + " ***");
              for (int ageing = 0; ageing < population.size(); ageing++) { //Litters
                //System.out.println(age.get(ageing)); // print to test age
                if (age.get(ageing) >= 100) { // age check
                  if (population.get(ageing) == (population.get(0))) { // gender check
                    for (int preg = 0; preg < pregnancy_days.size(); preg++) {
                      if (pregnancy_days.get(preg) == 0) {
                        /**
                        * 3 items are checked to ensure a doe can give birth: that it is a doe, it has zero pregnancy days remaining and that it is over a hundred days old.
                        */
                        preg_days = run.getGestationPeriod();
                        pregnancy_days.set(preg, (preg_days + 7));
                        baby_count = run.getLitterCount();
                        //System.out.println("NO OF BABIES: " + baby_count);
                        for (int babies = 0; babies < baby_count; babies++) {
                          baby_sex = run.getSex();
                          if (baby_sex == "F") {
                            preg_days = run.getGestationPeriod();
                            population.add(new Rabbit("F"));
                            pregnancy_days.add(100 + preg_days);
                            doe_count++;
                            age.add(0);
                          }
                          if (baby_sex == "M") {
                            population.add(new Rabbit("M"));
                            buck_count++;
                            age.add(0);
                          }

                                /**
                                * For everyone new born rabbit, the constructor of class rabbit is called an its gender is randomly assigned
                                */
                      }
                    }

                      else {
                        pregnancy_days.set(preg, (pregnancy_days.get(preg) - 1));
                        //System.out.println("Days until give birth: " + pregnancy_days.get(preg));
                      }
                    }
                  }
                }
              }
              for (int ageing = 0; ageing < population.size(); ageing++) { //ageing process
                while (run_once == 1) {
                  run.addAge(age.get(ageing));
                  run_once++;
                }
                age.set(ageing, run.getAge()); // add age to ArrayList

                run_once = 1;
              }
            }
            while (counter < 10) {
              if (population.size()!=0) {
                System.out.println("Trial " + counter + ": " + population.size() + " was the final population of rabbits; " + doe_count + " does, " + buck_count + " bucks.");
                while (a < 10) {
                  arr[a][0] = population.size();
                  arr[a][1] = doe_count;
                  arr[a][2] = buck_count;
                  break;
                }
                a++;
                counter ++;
              }
              break;
            }

            tentimecount++;

              /**
              * All arrays and data stored in methods are cleared for a new trial to begin.
              */
            population.clear();
            age.clear();
            pregnancy_days.clear();
            run.resetAll();
            doe_count = 0;
            buck_count = 0;
          }
        }
        double average = 0;
        double std_dev = 0;
        double femAverage = 0;
        double femStdDev = 0;
        double malAverage = 0;
        double malStdDev = 0;
        for (int x = 0; x < 10; x++) {
          average += Double.valueOf(arr[x][0]);
        }
        average = average/10;
        for (int x = 0; x < 10; x++) {
          std_dev += ((Double.valueOf(arr[x][0]))*(Double.valueOf(arr[x][0]) - average))/ 10;
        }
        std_dev = Math.sqrt(std_dev);
        for (int x = 0; x < 10; x++) {
          femAverage += Double.valueOf(arr[x][1]);
        }
        femAverage = femAverage/10;
        for (int x = 0; x < 10; x++) {
          femStdDev += ((Double.valueOf(arr[x][1]))*(Double.valueOf(arr[x][1]) - femAverage))/ 10;
        }
        femStdDev = Math.sqrt(femStdDev);
        for (int x = 0; x < 10; x++) {
          malAverage += Double.valueOf(arr[x][2]);
        }
        malAverage = malAverage/10;
        for (int x = 0; x < 10; x++) {
          malStdDev += ((Double.valueOf(arr[x][2]))*(Double.valueOf(arr[x][2]) - malAverage))/ 10;
        }
        malStdDev = Math.sqrt(malStdDev);

        String strAverage = String.format("%.3f", average);
        String strStdDev = String.format("%.3f", std_dev);
        String strFemAverage = String.format("%.3f", femAverage);
        String strFemStd = String.format("%.3f", femStdDev);
        String strMalAverage = String.format("%.3f", malAverage);
        String strMalStd = String.format("%.3f", malStdDev);

        System.out.println("Average number of rabbits: " + strAverage + " with standard deviation of " + strStdDev);
        System.out.println("Average number of female rabbits: " + strFemAverage + " with standard deviation of " + strFemStd);
        System.out.println("Average number of male rabbits: " + strMalAverage + " with standard deviation of " + strMalStd);
        System.out.println("");

      /**
      * A 3D array is used like a spreadsheet to temporarily store the data of the 10 trials.
      * Loops are then used to get the average and standard deviations for each trial.
      */

        a = 0;
        trial_sets ++;
        if (trial_sets == does.length) {
          running_sim = 0;
        }
    }
  }
}
