package com;

import java.util.Iterator;
import java.util.Scanner;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.LinguisticTerm;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class projeto {
    public static void main(String[] args) {
        FIS fis = FIS.load("papeis.fcl", true);
        if(fis == null) {
            System.out.println("Error carregando o arquivo...");
            return;
        }
        
        FunctionBlock fb = fis.getFunctionBlock(null);
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Valor de espessura");
        int espessura = scanner.nextInt();
        System.out.println("Valor de definicao");
        int definicao = scanner.nextInt();    
        
        fb.setVariable("espessura", espessura);
        fb.setVariable("definicao", definicao);

        fb.evaluate();
        Variable classificacao = fb.getVariable("classificacao");
        
        System.out.printf("Valor de classifição: %.2f %n", classificacao.getValue());
                
        System.out.println("-----PERTINENCIAS-----");
        for (LinguisticTerm term : classificacao) {
            String termName = term.getTermName();
            System.out.printf("%s = %.2f %n", termName,
                    classificacao.getMembership(termName));
        }
    }    
}
