import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;

public class PrimeGenerator{

    private ArrayList<Integer> getInitSieve(){
        int initSieveLen = 100000 + 1;
        int[] initSieve = new int[initSieveLen];
        //Finding all primes less than or equal to sqrt(stop)
        ArrayList<Integer> initPrimes = new ArrayList<>();
        for(int i=2; i<initSieveLen; i++){
            if(initSieve[i] == 0){
                initPrimes.add(i);
                int mult = 2;
                while(i*mult < initSieveLen){
                    initSieve[i*mult] = 2;
                    mult += 1;
                }
            }
        }
        return initPrimes;
    }

    //Using a Prime Sieve
    private String sieve(ArrayList<Integer> initPrimes, int start, int stop){
        int finalSieveLen = stop - start + 1;
        int[] finalPrimes = new int[finalSieveLen];
        for(Integer prime: initPrimes){
            int mult = 2;
            while(mult*prime <= stop){
                if(mult*prime < start){}
                else finalPrimes[(mult*prime)-start] = 1;
                mult += 1;
            }
        }

        StringBuffer currResult = new StringBuffer();
        for(int i=0; i<finalSieveLen; i++){
            if(finalPrimes[i] == 0){
                if((i+start) == 1) continue;
                else currResult.append((i+start) + "\n");
            }
        }
        return currResult.toString();
    }

    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = scanner.nextInt();
        StringBuffer result = new StringBuffer();
        ArrayList<Integer> initSieve = new ArrayList<>();
        initSieve = primeGenerator.getInitSieve();
        while(t-- > 0){
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            result.append(primeGenerator.sieve(initSieve, start, stop) + "\n");
        }
        System.out.print(result);
        scanner.close();
    }
}