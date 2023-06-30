package chess;

/* Data Structures Topics:
* - List
*/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.enums.Color;
import chess.pieces.King;
import chess.pieces.Rook;
import gameboard.Board;
import gameboard.Piece;
import gameboard.Position;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	private List <Piece> piecesOnTheBoard = new ArrayList<>();
	private List <Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {// it is this class that knows the size of the board
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE; //the player with the white pieces makes the first move
		initialSetup();
	}

	public ChessMatch(int turn, Color currentPlayer, Boolean check, Boolean checkMate, ChessPiece enPassantVulnerable,
			ChessPiece promoted) {
		this.turn = turn;
		this.currentPlayer = currentPlayer; 
		this.check = check;
		this.checkMate = checkMate;
		this.enPassantVulnerable = enPassantVulnerable;
		this.promoted = promoted;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toMatrixPosition());
		piecesOnTheBoard.add(piece);
	}
	
	public ChessPiece performeChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//convert chess positions to matrix positions
		Position source = sourcePosition.toMatrixPosition();
		Position target = targetPosition.toMatrixPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You cannot put yourself in a check position!");
		}
		
		//check opponent check after currentPlayer successful move
		//and sign this in the check attribute of the ChessMatch
		check = testCheck(opponent(currentPlayer)) ? true: false;
		
		nextTurn();
		
		//down cast from Piece(matrix) to ChessPiece
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece piece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(piece, target);
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
			
		return capturedPiece;
	} 
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece piece = board.removePiece(target);
		board.placePiece(piece, source);
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);	
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			
		}
	} 
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position!");
		}
		
		//player tries to move a piece that is not his
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece isn't yours!");
		}
		
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move for the chosen piece!");
		}
	} 
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position!");
		}
	} 
	
	
	public boolean [][] possibleMoves(ChessPosition sourcePosition) {
		//1st convert a Chess Position to a matrix Position
		Position position = sourcePosition.toMatrixPosition();
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
	}
	
	
	private void nextTurn() {
		turn ++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(p -> ((ChessPiece)p).getColor() == color).collect(Collectors.toList());
		
		for(Piece p: list) {
			if(p instanceof King){
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");	
	}

	// validate that for all the opponent's pieces,
	// if there is a possible move that will reach the king's position
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toMatrixPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(p -> ((ChessPiece) p).getColor() == opponent(color)).collect(Collectors.toList());

		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true; // the king is in check position
			}
		}

		return false;
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new King(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		
		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new King(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));		
	}

	public Board getBoard() {
		return board;
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public Boolean getCheck() {
		return check;
	}

	public Boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public void setEnPassantVulnerable(ChessPiece enPassantVulnerable) {
		this.enPassantVulnerable = enPassantVulnerable;
	}

	public ChessPiece getPromoted() {
		return promoted;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// downcast to ChessPiece
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		
		return mat; //returns the array of pieces from my chess match
	}


	public ChessPiece replacePromotedPiece(String type) {
		return null;
	}

}
