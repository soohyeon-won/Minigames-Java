package week13.p10;


//TicTacToe.java ------------------------------------------------


/**
A 3 x 3 tic-tac-toe board.
*/
public class TicTacToe
{
	private String[][] board;
	private static final int ROWS = 3;
	private static final int COLUMNS = 3;

	/**
Constructs an empty board.
	 */
	public TicTacToe()
	{
		board = new String[ROWS][COLUMNS];
		// Fill with spaces
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; j++)
				board[i][j] = " ";
	}

	/**
Sets a field in the board. The field must be unoccupied.
@param i the row index 
@param j the column index 
@param player the player ("x" or "o")
	 */
	public void set(int i, int j, String player)
	{
		if (board[i][j].equals(" "))
			board[i][j] = player;
	}

	/**
Creates a string representation of the board, such as
|x o|
| x |
| o |
@return the string representation
	 */
	public String toString()
	{
		String r = "";
		for (int i = 0; i < ROWS; i++)
		{
			r = r + "|";
			for (int j = 0; j < COLUMNS; j++) 
				r = r + board[i][j];
			r = r + "|\n";
		}
		return r;
	}
	public boolean isValid(int x, int y)
	{
		if( x >= ROWS || x < -1 || y >= COLUMNS || y < 0)	// ė²”ģ„ ė°–ģ¯øź°€?
			return false;
		else if(board[x][y].equals("X") || board[x][y].equals("O")) // ė¸ģ¯´ ģ˛ė” ģ˛ė¦¬ģ— ė ė†“ģ¼ė ¤ź³  ķ•ė”ź°€?
			return false;
		else 
			return true;
	}

	public String getWinner()
	{
		if(win("X"))
			return "X";
		else if(win("O"))
			return "O";
		else 
			return "";
	}
	private boolean win(String player)
	{
		if(checkVertical(player) || checkHorizontal(player) || checkDiagonal(player))
			return true;
		else 
			return false;
	}
	private boolean checkVertical(String player)
	{
		return ((board[0][0].equals(player) && board[0][1].equals(player) && board[0][2].equals(player))||
				(board[1][0].equals(player) && board[1][1].equals(player) && board[1][2].equals(player))||
				(board[2][0].equals(player) && board[2][1].equals(player) && board[2][2].equals(player)));
	}
	private boolean checkHorizontal(String player)
	{
		return ((board[0][0].equals(player) && board[1][0].equals(player) && board[2][0].equals(player))||
				(board[0][1].equals(player) && board[1][1].equals(player) && board[2][1].equals(player))||
				(board[0][2].equals(player) && board[1][2].equals(player) && board[2][2].equals(player)));
	}
	private boolean checkDiagonal(String player)
	{
		return ((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))||
				(board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)));
	}
}
