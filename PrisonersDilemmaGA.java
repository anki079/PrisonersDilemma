import java.io.*;
import java.util.*;
import java.text.*;

public class PrisonersDilemmaGA extends FitnessFunction{
         /*
            We need the following objects but how are they passed to this class?
         */

      IteratedPD ipd;
      GAStrategy s;
      StrategyAlwaysDefect d;
      StrategyAlwaysCooperate c;
      StrategyRandom rnd;
      StrategyTitForTat tft;
      StrategyTitForTwoTats tf2t;

      public PrisonersDilemmaGA()
      {
        name = "Iterated Prisoner's Dilemma";

      }

      public void doRawFitness(Chromo X)
      {
        x.rawFitness = ipd(s(X.chromo),d).player1Score() 
        + ipd(s(X.chromo),c).player1Score()
        + ipd(s(X.chromo),rnd).player1Score()
        + ipd(s(X.chromo),tft).player1Score()
        + ipd(s(X.chromo),tf2t).player1Score();
      }

  
    public void doPrintGenes(Chromo X, FileWriter output) throws java.io.IOException{

        for (int i=0; i<Parameters.numGenes; i++){
            Hwrite.right(X.getGeneAlpha(i),11,output);
        }
        output.write("   RawFitness");
        output.write("\n        ");
        for (int i=0; i<Parameters.numGenes; i++){
            Hwrite.right(X.getPosIntGeneValue(i),11,output);
        }
        Hwrite.right((int) X.rawFitness,13,output);
        output.write("\n\n");
        return;
    }
      
}