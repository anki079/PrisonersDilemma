import java.io.*;
import java.util.*;
import java.text.*;

public class PrisonersDilemmaGA extends FitnessFunction{
         /*
            We need the following objects but how are they passed to this class?
         */

      Strategy s;
      StrategyAlwaysDefect d;
      StrategyAlwaysCooperate c;
      StrategyRandom rnd;
      StrategyTitForTat tft;
      StrategyTitForTwoTats tf2t;
	  int numRounds;

      public PrisonersDilemmaGA(int numRounds)
      {
        name = "Iterated Prisoner's Dilemma";
		this.numRounds = numRounds;

      }

      public void doRawFitnessAlt(Chromo X)
      {
		// Strategies
        s = new GAStrategy(X.chromo);
        d = new StrategyAlwaysDefect();
        c = new StrategyAlwaysCooperate();
        //rnd = new StrategyRandom();
        tft = new StrategyTitForTat();
        tf2t = new StrategyTitForTwoTats();

		// Games
		IteratedPD vsDefect = new IteratedPD(s,d);
		IteratedPD vsCooperate = new IteratedPD(s,c);
		//IteratedPD vsRandom = new IteratedPD(s,rnd);
		IteratedPD vsTfT = new IteratedPD(s,tft);
		IteratedPD vsTf2t = new IteratedPD(s,tf2t);

		// Play Games
		vsDefect.runSteps(numRounds);
		vsCooperate.runSteps(numRounds);
		//vsRandom.runSteps(numRounds);
		vsTfT.runSteps(numRounds);
		vsTf2t.runSteps(numRounds);

		// Calculate Fitness
        X.rawFitness = vsDefect.player1Score()
        + vsCooperate.player1Score()
        //+ vsRandom.player1Score()
        + vsTfT.player1Score()
        + vsTf2t.player1Score();

      }

	public void doRawFitness(Chromo X)
	{
		ArrayList<Strategy> strategies = new ArrayList<Strategy>();
		Strategy s = new GAStrategy(X.chromo);
		int fitness = 0;
		
		// Create Strategies Based on other individuals in population and play those games
		for (int i = 0; i < Parameters.popSize; i++)
		{
			Strategy currStrategy = new GAStrategy(Search.member[i].chromo);
			IteratedPD game = new IteratedPD (s, currStrategy);
			game.runSteps(numRounds);
			fitness += game.player1Score();
		}

		X.rawFitness = fitness;
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
