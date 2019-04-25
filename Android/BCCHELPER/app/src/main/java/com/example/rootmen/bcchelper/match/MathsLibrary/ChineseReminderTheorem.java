package com.example.rootmen.bcchelper.match.MathsLibrary;

public class ChineseReminderTheorem {
    private String GetBufferStringChineseReminderTheorem(long x1,long mod1, long x2, long mod2, long mod3, long Difference){
        EuclideanAlgorithm EuclideanAlgorithmControl = new EuclideanAlgorithm();
        String Answer = "Приводим выражение к необходимому виду\n";
        Answer = Answer + "x=" + x2 + "+" + mod2 + "*t=";
        Answer = Answer + x1 + "+" + mod1 + "*s\n";
        Answer = Answer + "Переобразуем выражение для решения через алгоритм евклида\n";
        Answer = Answer + mod1 + "*t-" + mod2 + "*s=" + Difference + "\n";
        EuclideanAlgorithm.EuclideanAlgorithmReturn Nod;
        if (mod2 > mod1) Nod = EuclideanAlgorithmControl.GetNodS(mod1, mod2, "");
        else Nod = EuclideanAlgorithmControl.GetNodS(mod2, mod1, "");
        Answer = Answer + "НОД\n" + Nod.Answer;
        Answer = Answer + "Получаем значения для решения исходного уравнения\n";
        long s = Nod.x * Difference;
        Answer = Answer + "s=" + Nod.x + "*" + Difference + "=" + s + "\n";
        long x = x2 + mod2 * s;
        Answer = Answer + "x=" + x2 + "+" + mod2 + "*" + s + "=" + x;
        while (x > mod3){
            x = x - mod3;
        }
        Answer = Answer + "=" + x;
        return Answer;
    }

    public String GetChineseReminderTheoremString(long x1,long mod1, long x2, long mod2, long mod3){
        EuclideanAlgorithm EuclideanAlgorithmControl = new EuclideanAlgorithm();
        EuclideanAlgorithm.EuclideanAlgorithmReturn Nod = null;
        String Answer = "Приводим выражение к необходимому виду\n";
        long Difference = x1 - x2;
        if(Difference > 0) {
            return GetBufferStringChineseReminderTheorem(x1,mod1,x2,mod2,mod3,Difference);
        } else {
            Difference = x2 - x1;
            return GetBufferStringChineseReminderTheorem(x2,mod2,x1,mod1,mod3,Difference);
        }
    }

    private long GetBufferChineseReminderTheorem(long x1,long mod1, long x2, long mod2, long mod3, long Difference){
        EuclideanAlgorithm EuclideanAlgorithmControl = new EuclideanAlgorithm();
        EuclideanAlgorithm.EuclideanAlgorithmReturn Nod;
        if (mod2 > mod1) Nod = EuclideanAlgorithmControl.GetNodS(mod1, mod2, "");
        else Nod = EuclideanAlgorithmControl.GetNodS(mod2, mod1, "");
        long s = Nod.x * Difference;
        long x = x2 + mod2 * s;
        while (x > mod3){
            x = x - mod3;
        }
        return x;
    }

    public long GetChineseReminderTheorem(long x1,long mod1, long x2, long mod2, long mod3){
        EuclideanAlgorithm EuclideanAlgorithmControl = new EuclideanAlgorithm();
        EuclideanAlgorithm.EuclideanAlgorithmReturn Nod = null;
        long Difference = x1 - x2;
        if(Difference > 0) {
            return GetBufferChineseReminderTheorem(x1,mod1,x2,mod2,mod3,Difference);
        } else {
            Difference = x2 - x1;
            return GetBufferChineseReminderTheorem(x2,mod2,x1,mod1,mod3,Difference);
        }
    }
}
