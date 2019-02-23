package com.company.MathsLibrary;

public class EuclideanAlgorithm {
    public class EuclideanAlgorithmReturn{
        long g, x, y;
        String Answer = "";
        EuclideanAlgorithmReturn(long g,long x,long y){
            this.g = g;  this.x = x;  this.y = y;
        }
        EuclideanAlgorithmReturn(long g,long x,long y, String Answer){
            this.g = g;  this.x = x;  this.y = y; this.Answer = Answer;
        }
        @Override
        public String toString() {
            return Answer.equals("") ? g + " " + x + " " + y : g + " " + x + " " + y + "\n" + Answer ;
        }
    }


    public EuclideanAlgorithmReturn GetNod(long a, long b){
        if (a == 0) {
           return new EuclideanAlgorithmReturn(b,0,1);
        }
        EuclideanAlgorithmReturn  rec = GetNod (b % a, a);
        return new EuclideanAlgorithmReturn(rec.g, rec.y - (b / a) * rec.x, rec.x);
    }


    public EuclideanAlgorithmReturn GetNodS(long a, long b, String Answer){
        if (a == 0) {
            return new EuclideanAlgorithmReturn(b,0,1, Answer);
        }
        String AnswerThisFirst = "";
        if(b % a != 0) AnswerThisFirst = b + "=" + (b / a) + "*" + a + "+" + (b % a) + "\n";
        else  AnswerThisFirst = "Обратный ход НОДа\n";
        EuclideanAlgorithmReturn  rec = GetNodS(b % a, a,Answer + AnswerThisFirst);
        long ReturnX = rec.y - (b / a) * rec.x;
        String AnswerThisLast = "";
        if(b % a != 0)  AnswerThisLast  =  (b % a) + "=(" + ReturnX + ")*" + a + "+(" + rec.x + ")*" + b + "\n";
        return new EuclideanAlgorithmReturn(rec.g, ReturnX, rec.x, rec.Answer + AnswerThisLast);
    }
}
