package gameboard;

/* OOP Topics:
 * - Associations
 * - Encapsulation / Access modifiers
 * 
 * Data Structures Topics:
 * - Matrix
 */

//this class represents a "piece" in the matrix
public abstract class Piece {
	protected Position position; //isn't a chess position, is matrix position
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	public Piece(Position position) {
		this.position = position;
	}
	
	//internal use of board "layer"
	protected Board getBoard() {
		return board;
	}

	
	public Boolean[][] possibleMoves() {

		Boolean moves[][] = null;

		return moves;
	}


	public Boolean isThereAnyPossibleMove(Position position) {
		return true;
	}

}
