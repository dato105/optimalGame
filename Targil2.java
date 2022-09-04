
package Targil2;

import java.util.Random;
import java.util.Scanner;

public class Targil2 {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {

		Random r = new Random();
		int [] arr = new int[7];
		System.out.println("The game array is:");
		for (int i = 0; i < 7; i++) {
			arr[i] = r.nextInt(3)+1;
			System.out.print(arr[i] + " ");
		}

		System.out.println();

		optimalGame(arr, arr.length);




	}

	public static void optimalGame(int[] arr, int n) {

		int table[][] = new int[n][n];
		int j ,i , a, f1,f2,f3;

		//Fills the table
		for (a = 0; a < n; a++) {
			for (i = 0, j = a; j < n; i++, j++) {

				if ((i + 2) <= j) {
					f1 = table[i+2][j];
					f2 = table[i+1][j-1];	
					f3 = table[i][j-2];	
				}
				else {
					f1=0;
					f2=0;
					f3=0;
				}
				int moveI =  arr[i] + Math.min(f1, f2);
				int moveJ =  arr[j] + Math.min(f2, f3);


				table[i][j] = Math.max( moveI,moveJ);
				if(table[i][j] == moveI) {
					table[j][i] = i;
				}
				else table[j][i] = j;
			}
		}


		i = 0;
		j= n-1;
		int computer = 0, player = 0;

		//the game
		/*I built a code that would also fit an array 
		of different sizes and also that the player would play second
		*/
		while(j-i > 0) {

			System.out.println("Your best choise is:" + table[j][i]);
			System.out.print("Please insert your choise(" + i + " or "+ j + "):");
			if(sc.nextInt() == i) {
				player += arr[i];
				i++ ;
			}
			else {
				player += arr[j];
				j-- ;
			}

			/*I have taken into account a situation where the array size is 
			not odd and the computer can also run last
			 */
			if(j-i > 0) {
				
				System.out.println("The game array is:");
				for(int temp = i ; temp<=j ; temp++) {
					System.out.print(arr[temp] + " ");
				}
				System.out.println();


				System.out.println("The computer choose: " + table[j][i]);
				if(table[j][i] == i) {
					computer += arr[i];
					i++ ;
				}
				else {
					computer += arr[j];
					j-- ;
				}
				if(j-i > 0) {

					System.out.println("The game array is:");
					for(int temp = i ; temp <=j ; temp++) {
						System.out.print(arr[temp] + " ");
					}
					System.out.println();
				}
				else {
					System.out.println("Your best choise is selected automaticaly (only one number left in the array)");
					player += arr[i];
				}
			}
			else computer += arr[i];

		}
		if(player > computer) {
			System.out.println("YOU WIN :) , The score is - "+player+": "+computer+", You win with: "+(player-computer));
		}
		else if(player < computer)
			System.out.println("YOU LOOSE :( , The score is - "+player+": "+computer+", You loose with: "+(computer - player));
		else System.out.println("It's a tie:) , The score is - "+player+": "+computer);




	}

}
