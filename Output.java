import java.io.*;
import java.util.*;
import java.text.*;

public class Output
{
	public ArrayList<List<Graph>> graphRuns;
	public ArrayList<Graph> graphGens;
	public int genSize;
	
	public Output(ArrayList<List<Graph>> graphRuns, ArrayList<Graph> graphGens, int genSize) throws java.io.IOException
	{
		this.graphRuns = graphRuns;
		this.graphGens = graphGens;
		this.genSize = genSize;
		
		int i, j = 0;
		double tmpBest = 0;
		double tmpAverage = 0;
		double tmpStdDev = 0;		
		
		String fileName = "output.txt";
		FileWriter fileOutput = new FileWriter(fileName);
		
		fileOutput.write("Best\n");
		for(i=0;i<genSize;i++)
		{
			for(j=0;j<graphRuns.size();j++)
			{
				tmpBest += graphRuns.get(j).get(i).best;
			}
			tmpBest = tmpBest / graphRuns.size();
			
			fileOutput.write(tmpBest + "\n");
			tmpBest = 0;
		}
		
		fileOutput.write("\nAverage\n");
		for(i=0;i<genSize;i++)
		{
			for(j=0;j<graphRuns.size();j++)
			{
				tmpAverage += graphRuns.get(j).get(i).average;
			}
			tmpAverage = tmpAverage / graphRuns.size();
			
			fileOutput.write(tmpAverage + "\n");
			tmpAverage = 0;
		}

		fileOutput.write("\nStd Dev\n");
		for(i=0;i<genSize;i++)
		{
			for(j=0;j<graphRuns.size();j++)
			{
				tmpStdDev += graphRuns.get(j).get(i).stdDev;
			}
			tmpStdDev = tmpStdDev / graphRuns.size();
			
			fileOutput.write(tmpStdDev + "\n");
			tmpStdDev = 0;
		}		
		
		fileOutput.close();
	}
}