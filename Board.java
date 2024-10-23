import java.util.Scanner;

public class Board {
    private static int size;
    private static char[][] hiddenboard; 
    private static boolean[][] reveledboard;
    private static boolean[][] flag;
    private static int unreavelBoardCount; 
    private static int bombcount;
    private static int flagcount;

    
    public static void start()
    {
        size=0;
        bombcount=0;
        System.out.println("1.Easy\n2.Medium\n3.Hard");
        Scanner io=new Scanner(System.in);
        int choice=io.nextInt();
        if(choice==1)
        {
            size=10;
            bombcount=10;
        }else if(choice==2)
        {
            size=18;
            bombcount=40;
        }else if(choice==3)
        {
            size=25;
            bombcount=99;
        }else
        {
            size=10;
            bombcount=10;
        }
        flagcount=bombcount;
        hiddenboard=new char[size][size];
        reveledboard=new boolean[size][size];
        minepos=new boolean[size][size];
        flag=new boolean[size][size];
        unreavelBoardCount=size*size;

        Board.createboard();
		Board.printboard();
	}

    
    public static void createboard()
    {
        for(int i=0;i<Board.hiddenboard.length;i++)
        {
            for(int j=0;j<Board.hiddenboard.length;j++)
            {
                Board.hiddenboard[i][j]='#';
            }
        }
    }
    public static void insertmine(int i,int j)
    {
        int minecount=1;
        while(minecount<=Board.bombcount)
        {
            int rows=(int)(Math.random()*Board.hiddenboard.length-1)+1;
            int column=(int)(Math.random()*Board.hiddenboard.length-1)+1;
            if(Board.hiddenboard[rows][column]=='#' && i!=rows && j!=column && i+1!=rows && j+1!=rows && i-1!=rows&& j-1!=rows)
            {
                Board.hiddenboard[rows][column]='*';
                Board.minepos[rows][column]=true;
                minecount++;
            }
            }
    }

    public static void printboard()
    {
        System.out.println("  Flag :"+Board.flagcount);
        for(int i=0;i<Board.hiddenboard.length;i++) 
        {
            System.out.print("\t"+i);
        }
        System.out.println();
        
        for(int i=0;i<Board.reveledboard.length;i++)
        {
            System.out.print(i+"\t");
            for(int j=0;j<Board.reveledboard.length;j++)
            {
                if(Board.reveledboard[i][j])
                {
                    System.out.print(Board.hiddenboard[i][j]+"\t");
                }
                else
                {
                    if(Board.flag[i][j])
                    {
                        System.out.print("$"+"\t");
                    }
                    else
                    {
                    System.out.print("#"+"\t");
                    }
                }
                
            }
            System.out.println();
        }
    }
    
    public static void setminecount()
    {
        for(int i=0;i<Board.hiddenboard.length;i++)
        {
            for(int j=0;j<Board.hiddenboard.length;j++)
            {
                int h=mark(i,j);
                if(Board.hiddenboard[i][j]!='*' && h>=1)
                {
                Board.hiddenboard[i][j]=(char)(h+48);
                }
                else if(Board.hiddenboard[i][j]!='*')
                {
                    Board.hiddenboard[i][j]='-';
                }
            }
            
        }
        
    }
    
    public static int mark(int row,int col)
    {
        int count=0;
        for(int i=row-1;i<=row+1;i++)
        {
            for(int j=col-1;j<=col+1;j++)
            {
                if(i>=0&&j>=0 && i<Board.hiddenboard.length && j<Board.hiddenboard.length)
                {
                    if(Board.hiddenboard[i][j]=='*')
                    {
                        count++;
                    }
                }
            }
        }
      return count;
        }
    
    public static void reveal(int row,int col)
    {
          if(row<0||col<0 || row>=Board.hiddenboard.length || col>=Board.hiddenboard.length)
            return;
          if(Board.reveledboard[row][col]) 
            return;
          if(Board.flag[row][col])
          {
              Board.flag[row][col]=false;
              Board.flagcount++;
          }
          Board.unreavelBoardCount--;
          Board.reveledboard[row][col]=true;
          if(Board.hiddenboard[row][col]>='1' && Board.hiddenboard[row][col]<='8')
            return;
            for(int i=row-1;i<=row+1;i++)
            {
                for(int j=col-1;j<=col+1;j++)
                {
                    if(i!=row || j!=col)
                    reveal(i, j);
                
             }
            }  
    
    }
    public static boolean checkgame(int row,int col)
    {
        if(Board.hiddenboard[row][col]=='*')
        {
            return true;
        }
        return false;
    }

    public static void gameover()
    {
        for(int i=0;i<Board.hiddenboard.length;i++)
        {
            for(int j=0;j<Board.hiddenboard.length;j++)
            {
                if(Board.hiddenboard[i][j]=='*')
                {
                    Board.reveledboard[i][j]=true;
                }
            }
        }
    }
    public static boolean wingame() 
    {
        if(Board.bombcount==Board.unreavelBoardCount)
        {
            return true;
        }
        return false;
    }


    public static String setFlag(int row,int col)
    {
    if(!Board.flag[row][col] && Board.reveledboard[row][col]!=true && Board.flagcount>0)
    {
        Board.flag[row][col]=true;
        Board.flagcount--;
        
        return "success fully set flag";
    }else if(Board.flag[row][col]) {
        Board.flagcount++;
        Board.flag[row][col]=false;
        return "success fully remove flag";
    }
        return "invalid position to set flag or flag limit";
    }


}
