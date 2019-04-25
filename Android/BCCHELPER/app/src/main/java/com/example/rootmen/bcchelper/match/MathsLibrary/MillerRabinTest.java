package com.example.rootmen.bcchelper.match.MathsLibrary;

public class MillerRabinTest {

    public static String getMillerRabinTestString(int a, int N) {
        StringBuilder Answer = new StringBuilder();
        EuclideanAlgorithm Nod = new EuclideanAlgorithm();
        Nod.GetNod(a, N);
        if (Nod.GetNod(a, N).g != 1) {
            return "Число хорошее т.к. числа не взаимно простые " + "\n";
        }
        int Pow_2 = -1;
        int SaveA = N - 1;
        while (SaveA % 2 == 0) {
            Pow_2++;
            SaveA = SaveA / 2;
        }
        int temp = (int) FastPow.GetFastPow(a, SaveA, N);
        if (temp == 1 || temp == -1) {
            return "Число плохое т.к. " + a + "^" + SaveA + " = " + temp + " = +(-)1 mod " + N + "\n";
        }
        Answer.append(a).append("^").append(SaveA).append(" = ").append(temp).append(" =/= +(-)1 mod ").append(N).append("\n");
        while (Pow_2 > 0) {
            SaveA = SaveA * 2;
            temp = (int) FastPow.GetFastPow(a, SaveA, N);
            if (temp == -1) {
                return Answer + "Число плохое т.к. " + a + "^" + SaveA + " = " + temp + " = -1 mod " + N + "\n";
            }
            Answer.append(a).append("^").append(SaveA).append(" = ").append(temp).append(" =/= -1 mod ").append(N).append("\n");
            Pow_2--;
        }
        return Answer + "Число хорошее\n";
    }
}
