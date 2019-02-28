package com.company.MathsLibrary;

public class FastPow {
    public String GetStringFastPow(long BaseDegree, long Pow, long Module){
        StringBuilder Answer = new StringBuilder();
        String BinaryMassPow = Long.toBinaryString(Pow);
        long BinaryMassPowSize = BinaryMassPow.length();
        long Number = BaseDegree;
        Answer.append("1 | ").append(Number).append(" = ");
        Answer.append(Number);
        Number = Number % Module;
        Answer.append(" = ").append(Number).append(" mod ").append(Module).append("\n");
        for(int g = 1; g < BinaryMassPowSize; g++){
            if(BinaryMassPow.charAt(g)=='1'){
                Answer.append("1 | ").append(Number).append("^2 * ").append(BaseDegree).append(" = ");
                Number = Number * Number * BaseDegree;
                Answer.append(Number);
                Number = Number % Module;
                Answer.append(" = ").append(Number).append(" mod ").append(Module).append("\n");
            } else {
                Answer.append("0 | ").append(Number).append("^2 = ");
                Number = Number * Number;
                Answer.append(Number);
                Number = Number % Module;
                Answer.append(" = ").append(Number).append(" mod ").append(Module).append("\n");
            }
        }
        return Answer.toString();
    }

    public long GetFastPow(long BaseDegree, long Pow, long Module){
        String BinaryMassPow = Long.toBinaryString(Pow);
        long BinaryMassPowSize = BinaryMassPow.length();
        long Number = BaseDegree;
        Number = Number % Module;
        for(int g = 1; g < BinaryMassPowSize; g++){
            if(BinaryMassPow.charAt(g)=='1'){
                Number = (Number * Number * BaseDegree) % Module;
            } else {
                Number = (Number * Number) % Module;
            }
        }
        return Number;
    }
}
