// Implementazione di Bubble Sort in Java

import java.io.*;
import java.util.Arrays;

class BubbleSort3 {
    void bubbleSort(int a[]) {
        int n = a.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                // Se a[j] > a[j+1] allora scambia
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
    }

    /* Stampa array */
    void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void print() {
        System.out.println("Argomenti possibili: <Nome file> o nulla");
    }

    /* Driver per provare la classe */
    public static void main(String args[]) throws Exception {
        if (args.length == 0) {
            BubbleSort3 ob = new BubbleSort3();
            int arr[] = {64, 34, 25, 12, 22, 11, 90};
            System.out.println("Array creato");
            ob.printArray(arr);
            ob.bubbleSort(arr);
            System.out.println("Array ordinato");
            ob.printArray(arr);
        }
        else if (args.length == 1) {
            String FilePath = args[0];
            BufferedReader br = new BufferedReader(new FileReader(FilePath));
            String numbers = "";
            String s = br.readLine();
            while (s != null) {
                numbers += " " + s.trim();
                s = br.readLine();
            }
            String[] strArray = numbers.trim().split(" ");
            int array[] = new int[strArray.length];
            for (int i = 0; i < array.length; i++)
                array[i] = Integer.parseInt(strArray[i]);

            BubbleSort3 ob = new BubbleSort3();
            System.out.println("Array creato");
            ob.printArray(array);
            ob.bubbleSort(array);
            System.out.println("Array ordinato");
            ob.printArray(array);
        }
        else {
            print();
        }
    }
}
/* This code is contributed by Rajat Mishra */
