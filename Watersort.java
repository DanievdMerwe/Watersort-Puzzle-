import java.util.Random;
import java.util.Scanner;


public class Watersort {
    Character top = null;

    static String[][] bottles;
    // create constants for colors
    static Character red = new Character('r');
    static Character blue = new Character('b');
    static Character yellow = new Character('g');
    //showAll method
    public static void showAll(StackAsMyArrayList bottles[])
    {
        for (int i = 0; i <= 4; i++) {
            System.out.println("Bottle " + i + ": " + bottles[i]);
        }
    }

    public static boolean solved(StackAsMyArrayList bottles[])
    {
        boolean game = false;

        for(int i = 0; i <= 4; i++) {

            if (bottles[i].getStackSize() == 0 || bottles[i].getStackSize() == 4)
            {
                if (bottles[i].checkStackUniform())
                {
                    game = true;
                }
                else
                {
                    game = false;
                    break;
                }
            }
            else
            {
               game = false;
                break;
            }

        }
        return game;
    }

    static void userInput(StackAsMyArrayList bottles[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nSelect the bottle index \"From\" which you want to pour a color and bottle index \"To\" that needs to receive the color (-1 to Exit)");
        System.out.println("Bottle pouring: ");
        int from = input.nextInt();
        if (from == -1) {
            System.exit(0);
        }
        System.out.println("Bottle receiving: ");
        int to = input.nextInt();

        if(from< bottles.length - 1 || to <bottles.length -1){
            if(bottles[to].getStackSize() < 4)
            {
                if(bottles[from].peek() == bottles[to].peek() || bottles[to].peek() == null )
                {
                    bottles[to].push(bottles[from].pop());
                    while(bottles[to].peek() == bottles[from].peek())
                    {
                        if(bottles[to].getStackSize() >= 4)
                        {
                            throw new IndexOutOfBoundsException("This is an invalid move");
                        }
                    }
                }
            }
        }

    }

    public static void main(String args[]) {
        int moves = 0;// number of moves to mix the water
        int source = 0; // number of bottle to pour FROM
        int target = 0; // number of bottle to pour TO
        int max = 4; // total number of items allowed per bottle
        Random randomNum = new Random();
        // Bottles declaration
        StackAsMyArrayList bottles[] = new StackAsMyArrayList[5];
        //You can do this with a for also
        bottles[0] = new StackAsMyArrayList<Character>();
        bottles[1] = new StackAsMyArrayList<Character>();
        bottles[2] = new StackAsMyArrayList<Character>();
        bottles[3] = new StackAsMyArrayList<Character>();
        bottles[4] = new StackAsMyArrayList<Character>();

        //////STRATEGY #1
        while (moves < 4) // 4 moves per 3 colors = 12 moves required
        {
            // get source bottle
            target = randomNum.nextInt(max + 1);
            while (bottles[target].getStackSize() == 4)// target is full
            {
                target = randomNum.nextInt(max);
            }
            bottles[target].push(blue);
            target = randomNum.nextInt(max + 1);
            while (bottles[target].getStackSize() == 4)// target is full
            {
                target = randomNum.nextInt(max);
            }
            bottles[target].push(red);
            target = randomNum.nextInt(max + 1);
            while (bottles[target].getStackSize() == 4)// target is full
            {
                target = randomNum.nextInt(max);
            }
            bottles[target].push(yellow);
            // showAll(bottles);
            // increment valid moves
            moves++;
        }
        System.out.println("Level 22: Difficulty = Medium\n");
        showAll(bottles);

        while(!solved(bottles))
        {
            userInput(bottles);
            showAll(bottles);
        }


        System.out.println("\nYou have completed the level! Goodluck with the next challenge ;)");

    }




}



