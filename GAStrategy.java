import java.util.Arrays;

public class GAStrategy extends Strategy
{

	String chromosome;
	int [] lastFiveMoves = { 1, 1, 1, 1, 1 };

	// Strategy takes in a chromosome and builds a strategy based on that
	public GAStrategy(String chromosome)
	{
		name = "GA Strategy";
		this.chromosome = chromosome;
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
