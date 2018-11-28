package com.example.rootmen.bcchelper.sigma;

public class SigmaMain {
    private String Sigma_NonDeployInput;
    private boolean[] Sigma_Znak;
    private int[] Sigma_Cof;
    private int[] Sigma_Pow;
    private int[][] Sigma_Cof_f;
    private int deg_f;

    SigmaMain(String Input){
        StringBuilder _Input = new StringBuilder();
        int _InputSize = Input.length();
        for(int g=0; g<_InputSize; g++) {
            char _g_char = Input.charAt(g);
            if (_g_char == '+' || _g_char == '-')
                _Input.append(" ").append(_g_char);
            else
                _Input.append(_g_char);
        }
        this.Sigma_NonDeployInput = _Input.toString();
        this.Sigma_DeployInput();
    }

    private void Sigma_DeployInput(){
        if(Sigma_NonDeployInput == null) return;
        int Cof_g;
        String[] _Mass = Sigma_NonDeployInput.split(" ");
        int _InputSize = _Mass.length;
        Sigma_Znak = new boolean[_InputSize];

        for(int g = 0; g<_InputSize; g++){
            String _g_Mass = _Mass[g];

            if(_g_Mass.charAt(0)=='+')
                Sigma_Znak[g]=true;
            else
                Sigma_Znak[g]=false;

            StringBuilder _Cof = new StringBuilder();
            for(Cof_g=1; _g_Mass.charAt(Cof_g)!='^'; Cof_g++)
                _Cof.append(_g_Mass.charAt(Cof_g));

            Sigma_Cof[g] = Integer.parseInt(_Cof.toString());
            _Cof=null;

            StringBuilder _Pow = new StringBuilder();
            for(Cof_g=Cof_g; _g_Mass.charAt(Cof_g)!='^'; Cof_g++)
                _Pow.append(_g_Mass.charAt(Cof_g));

            Sigma_Pow[g] = Integer.parseInt(_Pow.toString());
            _Pow=null;
        }
    }

    private void Sigma_Sigma_Cof_f(){
        int Max = Sigma_Pow[0];
        for(int g=0; g<Sigma_Pow.length; g++)
            if(Max<Sigma_Pow[g])
                Max=Sigma_Pow[g];
        deg_f=Max;




    }
}
