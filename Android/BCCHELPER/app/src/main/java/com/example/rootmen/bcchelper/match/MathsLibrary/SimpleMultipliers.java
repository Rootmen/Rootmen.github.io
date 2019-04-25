package com.example.rootmen.bcchelper.match.MathsLibrary;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class SimpleMultipliers {
    public static List<Integer> getSimpleMultipliers(int NotSimples){
        ArrayList<Integer> SimpleMultipliersMass = new ArrayList<>();
        BitSet SimpleMass = AtkinSieve.getPrimesUpTo(NotSimples);
        if(SimpleMass.get(NotSimples)) {
            SimpleMultipliersMass.add(NotSimples);
            return SimpleMultipliersMass;
        }
        while (true){
            if (NotSimples == 1){
                return SimpleMultipliersMass;
            }
            else if(NotSimples % 2 == 0){
                SimpleMultipliersMass.add(2);
                NotSimples = NotSimples / 2;
            }
            else if(NotSimples % 3 == 0){
                SimpleMultipliersMass.add(3);
                NotSimples = NotSimples / 3;
            }
            else if(SimpleMass.get(NotSimples)){
                SimpleMultipliersMass.add(NotSimples);
                return SimpleMultipliersMass;
            }
            else {
                EnumerateWhichChars EnumerateSimpleMass = new EnumerateWhichChars(SimpleMass);
                while (EnumerateSimpleMass.hasMoreElements()) {
                    int NextElement = EnumerateSimpleMass.nextElement();
                    if (NotSimples % NextElement == 0) {
                        NotSimples = NotSimples / NextElement;
                        SimpleMultipliersMass.add(NextElement);
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Integer> MassDimple = getSimpleMultipliers(2012312312);
        System.out.print(2012312312 + " = " + MassDimple.get(0));
        for(int g = 1; g < MassDimple.size(); g++){
            System.out.print(" * " + MassDimple.get(g));
        }
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("\nПрограмма выполнялась " + timeSpent + " миллисекунд");
    }
}
