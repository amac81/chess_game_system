package gameboard;

/* OOP Topics:
 * - Associations
 * - Encapsulation / Access modifiers
 * - Abstract method / class
 * - Exceptions 
 * 
 * 
 * Data Structures Topics:
 * - Matrix
 */

//this class represents a "piece" in the matrix
public abstract class Piece {
	protected Position position; // isn't a chess position, is matrix position
	private Board board;

	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	public Piece(Position position) {
		this.position = position;
	}

	// internal use of board "layer"
	protected Board getBoard() {
		return board;
	}

	// boolean matrix with possible moves, True on possible positions False otherwise
	public abstract boolean[][] possibleMoves();

	// hook method - "makes a hook" with subclass
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean[][] auxMat = possibleMoves();

		// assuming a square matrix
		int length = auxMat.length;

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (auxMat[i][j]) { //if matrix position is true
					return true;
				}
			}
		}
		return false;
	}

}
