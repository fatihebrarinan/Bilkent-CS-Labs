package Lab7;

public class Lab07_Q2
{
    boolean queueFull = false;

    public static void main(String[] args)
    {
        int[] queue = new int[20];

        System.out.println("---- Testing joinQueue ----");
        joinQueue(3, queue);
        joinQueue(2, queue);
        joinQueue(4, queue);
        joinQueue(1, queue);
        joinQueue(2, queue);
        joinQueue(2, queue);
        joinQueue(1, queue);
        joinQueue(1, queue);

        System.out.println("\n---- Testing full queue ----");
        for (int i = 0; i < 12; i++)
        {
            joinQueue(2, queue);
        }
        joinQueue(1, queue);

        System.out.println("\n---- Testing Bake First ----");
        bake(8, queue);
        System.out.println("\n---- Testing Bake Second ----");
        queue = new int[20];
        bake(8, queue);

    }

    public static void joinQueue(int orderedLoafs, int[] queue)
    {

        // if the queue is full print a message.
        if (queue[19] != 0)
        {
            System.out.println("\nQueue is full. Order rejected.");
            return;
        }

        // else if the ordered loafs is 1, put it in array accordingly.
        else if (orderedLoafs == 1)
        {
            // Add orderedLoafs to the array accordingly.

            int index = 1;
            boolean orderPlaced = false;
            while (index < queue.length && !orderPlaced)
            {
                if (queue[index - 1] == 0)
                {
                    queue[index - 1] = orderedLoafs;
                    orderPlaced = true;
                }

                else if (queue[index] != 1 && queue[index - 1] != 1)
                {
                    for (int i = queue.length - 1; i > index; i--)
                    {
                        queue[i] = queue[i - 1];
                    }
                    queue[index] = orderedLoafs;
                    orderPlaced = true;
                } else if (index == 19 && queue[index] == 0)
                {
                    queue[index] = orderedLoafs;
                    orderPlaced = true;
                }
                index++;
            }

            // Print queue.
            System.out.print("\nCurrent Queue: ");
            for (int i = 0; i < queue.length; i++)
            {
                if (queue[i] != 0)
                    System.out.print(queue[i] + " ");
            }
        }

        // else put it in array.
        else
        {
            // Add orderedLoafs to the array accordingly.
            int index = 1;
            boolean orderPlaced = false;
            while (index < queue.length && !orderPlaced)
            {
                if (queue[index - 1] == 0)
                {
                    queue[index - 1] = orderedLoafs;
                    orderPlaced = true;
                }

                else if (queue[index] == 1 && queue[index - 1] == 1)
                {
                    queue[index] = orderedLoafs;
                    orderPlaced = true;
                } else if (index == 19 && queue[index] == 0)
                {
                    queue[index] = orderedLoafs;
                    orderPlaced = true;
                }
                index++;
            }

            // Print queue.
            System.out.print("\nCurrent Queue: ");

            for (int i = 0; i < queue.length; i++)
            {
                if (queue[i] != 0)
                    System.out.print(queue[i] + " ");
            }
        }

    }

    public static void bake(int expectedLoaves, int[] queue)
    {
        // Check the total wanted amount of bread.
        int wantedBread = 0;
        for (int i = 0; i < queue.length; i++)
        {
            wantedBread += queue[i];
        }

        // If the queue is empty, print Current queue: Empty
        if (wantedBread == 0)
        {
            System.out.println("Current Queue: Empty");
        }
        // else print queue.
        else
        {
            // Dismiss people.
            int index = 19;
            if (wantedBread > expectedLoaves)
                System.out.print("Not enough bread. Dismissing customers.");
            while (wantedBread > expectedLoaves && index >= 0)
            {
                wantedBread = 0;
                if (queue[index] != 0)
                {
                    System.out.printf("\nCustomer with order %d loaves dismissed.", queue[index]);
                }
                queue[index] = 0;
                for (int i = 0; i < queue.length; i++)
                {
                    wantedBread += queue[i];
                }
                index--;

            }
            // print queue and message.
            System.out.print("\nCurrent queue: ");

            for (int i = 0; i < queue.length; i++)
            {
                if (queue[i] != 0)
                    System.out.print(queue[i] + " ");
            }
            System.out.println("\nAll customers will receive their bread.");
        }

    }
}
