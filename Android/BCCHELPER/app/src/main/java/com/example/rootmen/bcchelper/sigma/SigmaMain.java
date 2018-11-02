package com.example.rootmen.bcchelper.sigma;

public class SigmaMain {
    String Sigma_NonDeployInput;
    boolean[] Sigma_Znak;
    int[] Sigma_Cof;
    int[] Sigma_Pow;

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
    }

    void Sigma_DeployInput(){
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
}
