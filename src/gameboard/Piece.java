package gameboard;

public abstract class Piece {
	protected Position position;

	public Piece() {

	}

	public Piece(Position position) {
		this.position = position;
	}

	public Boolean[][] possibleMoves() {

		Boolean moves[][] = null;

		return moves;
	}

	public Boolean possibleMove(Position position) {
		return true;
	}

	public Boolean isThereAnyPossibleMove(Position position) {
		return true;
	}

}
