package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.enums.Color;
import gameboard.Piece;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
		

		sc.close();
	}

}
