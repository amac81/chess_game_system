package chess;

import gameboard.Position;

/* OOP Topics:
 * 
 * - Exceptions
 * - Encapsulation 
 * - Constructors
 * - Overriding 
 * - Static Members
 * - Layers Pattern
 */

/*
 * 8 - - - - - - - - 
   7 - - - - - - - - 
   6 - - - - - - - - 
   5 - - - - - - - - 
   4 - - - - - - - - 
   3 - - - - - - - - 
   2 - - - - - - - - 
   1 - - - - - - - - 
     a b c d e f g h
 * */

public class ChessPosition {
	private char column;
	private int row;

	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valide values are from a1 to h8!");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	
	/* to find the ROW at matrix position: matrix_row = 8 - chess_row
	* to find the COLUMN at matrix position:
	* 'a' - 'a' = 0
	* 'b' - 'a' = 1
	* 'c' - 'a' = 2
	* 
	* matrix_column = chess_column - 'a'
	* 
	*/
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}

	//convert from matrix position to Chess Position
	protected static ChessPosition  fromPosition(Position position) {
		return new ChessPosition((char)('a'- position.getColumn()), 8 - position.getRow() ) ;
	}

	@Override
	public String toString() {
		return "" + column + row;
	}

	
	
}
