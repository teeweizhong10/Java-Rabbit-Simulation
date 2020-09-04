# Java-Rabbit-Simulation

Java Rabbit Reproduction Simulation

Author: Wei Zhong Tee 
Last Updated: 17 February 2020 
Project type: Object Oriented Programming with Java

This is a basic object oriented program that simulates how many male and female rabbits will exist after a year given a start amount of male and female rabbits at the beginning for the year (passed in through the text file, where the text file is formatted as <number of females>[tab]<number of males> and multiple lines can be added for multiple simulations to run). The simulation is ran 10 times for each set of numbers in the text file and the average number of rabbits, female rabbits, male rabbits and their standard deviations are shown.

Specifications for the simulation:
- Only female rabbits can give birth
- A female rabbit can have between 3-8 rabbits per litter 
- A female rabbit has a gestation period of 28-32 days
- Female rabbits who have given birth to the litter can breed again after a week
- Rabbits can only breed after a age of 100 days
- The probability of a baby rabbit's sex is even

Rabbit.java, RabbitSimulation.java and the text file that is going to be passed in through command line must be placed in the same folder. 

The text file is passed in after compiling and running Rabbit Simulation.
Eg: > means command line input
> javac RabbitSimulation.java
> java RabbitSimulation <NameOfYourTextFile>.txt

Decisions I made:
1. Other than the gender that is an ArrayList of type <rabbit>, age and pregnancy days use ArrayLists of type <integer>
2. All the logic of the code runs in the main method
3. Many loops use a “flagging” concept, where loops run infinitely under an event takes place or a certain condition is met
4. Because of the many loops and flagging concepts I used, there are many local variables in the main method

Issues I faced:
1. I had issues accessing the gender data in the ArrayList of type <rabbit>
2. When there was more than one line in the text file that was passed in, certain trials would return population size as 0

Solutions:
1. I used a toString() function
2. I had the trial run 100 times and took the first 10 trials that returned a population size of more than 0; definitely not a good solution for big and power/memory consuming programs but it works for simple arithmetic programs such as this.


