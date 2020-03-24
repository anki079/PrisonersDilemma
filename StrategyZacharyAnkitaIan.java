//1100 1010 0000 1100 0001 0010 0000 0000

import java.util.Arrays;

public class StrategyZacharyAnkitaIan extends Strategy
{

	String chromosome;
	int [] lastFiveMoves = { 1, 1, 1, 1, 1 };

	// Strategy takes in a chromosome and builds a strategy based on that
	public GAStrategy()
	{
		name = "GA Strategy";
		this.chromosome = "11001010000011000001001000000000";
	}

	public int nextMove()
	{
		//update last five moves of oponnent
		lastFiveMoves[0] = lastFiveMoves [1];
		lastFiveMoves[1] = lastFiveMoves [2];
		lastFiveMoves[2] = lastFiveMoves [3];
		lastFiveMoves[3] = lastFiveMoves [4];
		lastFiveMoves[4] = opponentLastMove;

		// Convert last 5 moves to binary number to use as index
		StringBuilder builder = new StringBuilder();
		for (int num : lastFiveMoves)	builder.append(num);
		String indexString = builder.toString();
		int index = Integer.parseInt(indexString, 2);

		// Search chromosome to determine next move
		int nextMove = Character.getNumericValue(chromosome.charAt(index));

		return nextMove;
	}
}
