package com.company.MathsLibrary;

public class ChineseReminderTheorem {
    public String getChineseReminderTheoremString(long x1,long mod1, long x2, long mod2, long mod3){
        EuclideanAlgorithm EuclideanAlgorithmControl = new EuclideanAlgorithm();
        EuclideanAlgorithm.EuclideanAlgorithmReturn Nod = null;
        long Difference = x1 - x2;
        String Answer = "Приводим выражение к необходимому виду\n";
        if(Difference > 0){
            Answer = Answer + "x=" + x1 + "+" + mod1 + "*t=";
            Answer = Answer + x2 + "+" + mod2 + "*s\n";
            Answer = Answer + "Переобразуем выражение для решения через алгоритм евклида\n";
            Answer = Answer + mod2 + "*t-" + mod1 + "*s="+Difference+"\n";
            if(mod2 < mod1) Nod = EuclideanAlgorithmControl.GetNodS(mod2,mod1,"");
            else  Nod = EuclideanAlgorithmControl.GetNodS(mod1,mod2,"");
            Answer = Answer + "НОД\n" + Nod.Answer;
            Answer = Answer + "Получаем значения для решения исходного уравнения\n" ;
            long s = Nod.x * Difference;
            Answer = Answer + "s=" + Nod.x + "*" + Difference + "=" + s + "\n";
            long x = x2 + mod2 * s;
            Answer = Answer + "x=" + x2 + "+" + mod2 + "*" + s + "=" + x + "\n";
        }
        return Answer;
    }

    public static void main(String[] args) {
        ChineseReminderTheorem Contrl = new ChineseReminderTheorem();
        System.out.println(Contrl.getChineseReminderTheoremString(11,16,5,13,208));
    }
}
