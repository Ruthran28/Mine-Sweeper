import java.util.Scanner;

public class Main extends Board {

	
public static void main(String[] args) 
{
		start();
		Scanner io=new Scanner(System.in);
		boolean loop =true;
		int j=0;

		while(loop) 
		{
			System.out.println("1.enter position to reveal board \n2 2.enter the flag position\n3.exit game");
			int choice=io.nextInt();
			switch(choice) 
			{
			case 1:
			{
				System.out.println("enter x position");
				int xpos=io.nextInt();
				System.out.println("enter Y position");
				int ypos=io.nextInt();
				if(j==0) 
				{
					insertmine(xpos, ypos);
					setminecount();
					j++;
				}
				reveal(xpos, ypos);
				if(checkgame(xpos, ypos)) 
				{
					gameover();
					printboard();
					System.out.println("!!!!! GAME OVER !!!!");
					loop=false;
					break;
				}
				if(wingame())
				 {
					Board.gameover();
					System.out.println("!!!!! Won game !!!!");
					loop=false;
					break;
				}
				Board.printboard();
				break;
			}
			case 2:
			{
				System.out.println("enter x position");
				int xpos=io.nextInt();
				System.out.println("enter Y position");
				int ypos=io.nextInt();
				System.out.println(setFlag(xpos, ypos));
				Board.printboard();
				break;
			}
			case 3:
			{
				loop=false;
				System.out.print("thank you");
				break;
			}
				
			}
		}
		
	}
	
}

