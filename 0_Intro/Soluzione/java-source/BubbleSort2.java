// Implementazione di Bubble Sort in Java

import java.io.*;
import java.util.Arrays;
import java.util.Random;

class BubbleSort2 {
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
        System.out.println("Lanciare con argomento \"rnd\" seguito da intero oppure con sequenza di interi");
    }

    /* Driver per provare la classe */
    public static void main(String args[]) throws Exception {
        int array[];

        // Dobbiamo avere almeno 2 argomenti
        if (args.length < 2) {
            print();
            return;
        }

        // Primo caso: array di interi casuali di dimensione casuale
        else if (args[0].equals("rnd")) {
            Random rnd = new Random();
            int size = Integer.parseInt(args[1]);
            array = new int[size];
            for (int i = 0; i < size; i++)
                array[i] = rnd.nextInt();
        }

        // Secondo caso: array di interi dati a riga di comando
        else {
            array = new int[args.length];
            for (int i = 0; i < array.length; i++)
                array[i] = Integer.parseInt(args[i]);
        }

        // Ora eseguiamo l'ordinamento
        BubbleSort2 ob = new BubbleSort2();
        System.out.println("Array creato");
        ob.printArray(array);

        long t = System.currentTimeMillis();
        ob.bubbleSort(array);

        System.out.println("Array ordinato");
        ob.printArray(array);

        System.out.println("Tempo richiesto: " + (System.currentTimeMillis() - t) + " millisecondi");
    }
}
