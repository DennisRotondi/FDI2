import java.util.*;

public class Prime {
    /* Primality Test: simple methods */

    // Naive Primality Test
    // Costo: O(n)
    private static boolean naivePrimalityTest(int n) {
        if (n <= 1)
            return false;

        // check if there is a divisor of n in [2, n-1]
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
  
        return true;
    }

    // Simple Primality Test
    // Costo: O(sqrt(n))
    private static boolean simplePrimalityTest(int n) {
        if (n <= 1)
            return false;

        // check if there is a divisor of n in [2, sqrt(n)]
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;
  
        return true;
    }

    // Optimized Primality Test
    // Costo: O(sqrt(n))
    private static boolean optimizedSimplePrimalityTest(int n) {
        if (n <= 1) 
            return false;
        if (n <= 3) 
            return true;
        
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        
        // check if there is a divisor of n in [2, sqrt(n)]
        // consider only the numbers of the form 6k ± 1
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
         
        return true;
    }

    /* Primality Test: probabilistic methods */

    // Fermat Primality Test
    // Costo: O(k * log(n))
    private static boolean fermatPrimalityTest(int n) {
        if (n <= 1) 
            return false;
        if (n <= 3) 
            return true;

        Random ran = new Random();
        int minValue = 2;
        int maxValue = n-2;

        // if n is a prime number, then for every a in [2, n-2], a^(n-1) % n = 1
        // pick k numbers in [2, n-2] and check whether any of them violate the previous property
        int k = 1 + n/10;
        while (k > 0) {
            int a = minValue + ran.nextInt(maxValue - minValue + 1);
            
            if (power(a, n-1, n) != 1)
                return false;
            
            k--;
        }

        return true;
    }

    // Auxiliary function to compute a ^ n % p in O(log(n))
    private static int power(int a, int n, int p) {
        int res = 1;
        
        a = a % p;

        while (n > 0) {
            if ((n & 1) == 1)
                res = (res * a) % p;
            
            n = n >> 1; // n = n/2
            a = (a * a) % p;
        }

        return res;
    }

    /* Generating new prime numbers */
    
    // Find the smallest prime number greater than n
    static int nextPrime(int n) {
        if (n <= 1)
            return 2;

        int prime = n;
        boolean found = false;

        while (!found) {
            prime++;

            if (optimizedSimplePrimalityTest(prime))
                found = true;
        }

        return prime;
    }
}