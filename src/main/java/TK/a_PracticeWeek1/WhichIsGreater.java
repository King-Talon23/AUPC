package TK.a_PracticeWeek1;
import java.util.Scanner;
public class WhichIsGreater {
	public static int comparison(int a, int b){
		if (a>b) return 1;
		else return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		System.out.println(comparison(a,b));
	}

}
