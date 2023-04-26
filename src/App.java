import java.util.Arrays;

public class App {
public static int neighborCount(int[][] board, int i, int j)
{
    int livingCount = 0;
    boolean isEdgeU = i == 0;
    boolean isEdgeB = i == board.length - 1;
    boolean isEdgeL = j == 0;
    boolean isEdgeR = j == board[0].length - 1;
  
    if(!isEdgeU && !isEdgeL && board[i - 1][j - 1] == 1)    livingCount++;
    if(!isEdgeU && board[i - 1][j] == 1)    livingCount++;
    if(!isEdgeU && !isEdgeR && board[i - 1][j + 1] == 1)    livingCount++;
    if(!isEdgeL && board[i][j - 1] == 1)    livingCount++;
    if(!isEdgeR && board[i][j + 1] == 1)    livingCount++;
    if(!isEdgeB && !isEdgeL && board[i + 1][j - 1] == 1)    livingCount++;
    if(!isEdgeB && board[i + 1][j] == 1)    livingCount++;
    if(!isEdgeB && !isEdgeR && board[i + 1][j + 1] == 1)    livingCount++;

    return livingCount;
}

public static int[][] gameOfLife(int[][] board)
{
    int[][] boardCopy = new int[board.length][board[0].length];
    
    for(int i = 0; i < board.length; i++)
    {
        for(int j= 0; j < board[0].length; j++)
        {
            boardCopy[i][j] = board[i][j];
        }
    }

    for(int i = 0; i < boardCopy.length; i++)
    {
        for(int j = 0; j < boardCopy[0].length; j++)
        {
            int neighbor = neighborCount(boardCopy, i, j);

            if(board[i][j] == 1 && (neighbor < 2 || neighbor > 3))    board[i][j] = 0;
            else if(board[i][j] == 0 && neighbor == 3)    board[i][j] = 1;
        }
    }

    return board;
}

public static void main(String[] args)
    {
        int[][] board1 = {{0, 0, 1, 0, 1}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 0, 0}};
        int[][] board2 = {{0, 1, 1, 1, 1}, {0, 0, 1, 1, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 1, 1}};
        int[][] board3 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int[][] board4 = {{0, 1, 1, 0, 0}, {0, 1, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 1, 1, 0}};
        int[][] board5 = {{1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}};
        int[][] board6 = {{1}};

        int[][] boardFinal1 = gameOfLife(board1);
        int[][] boardFinal2 = gameOfLife(board2);
        int[][] boardFinal3 = gameOfLife(board3);
        int[][] boardFinal4 = gameOfLife(board4);
        int[][] boardFinal5 = gameOfLife(board5);
        int[][] boardFinal6 = gameOfLife(board6);

        int[][] boardExpected1 = {{0, 0, 0, 1, 0}, {0, 1, 1, 0, 0}, {0, 1, 1, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 0, 0}};
        int[][] boardExpected2 = {{0, 1, 0, 0, 1}, {0, 0, 0, 0, 1}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 1}, {0, 0, 1, 1, 1}};
        int[][] boardExpected3 = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}};
        int[][] boardExpected4 = {{0, 1, 1, 0, 0}, {0, 1, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 1, 1, 0}};
        int[][] boardExpected5 = {{1, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 1}};
        int[][] boardExpected6 = {{0}};

        boolean same = true;

        System.out.println();
        for(int i = 0; i < boardFinal1.length; i++)
        {
            System.out.println(Arrays.toString(boardFinal1[i]));
        }
        System.out.println("Expected: ");
        for(int i = 0; i < boardExpected1.length; i++)
        {
            System.out.println(Arrays.toString(boardExpected1[i]));
        }
        for(int i = 0; i < boardFinal1.length; i++)
        {
            for(int j = 0; j < boardFinal1[0].length; j++)
            {
                if(boardFinal1[i][j] != boardExpected1[i][j])
                {
                    same = false;
                    break;
                }
            }
        }
        System.out.println("result: " + same);

        same = true;
        System.out.println();
        for(int i = 0; i < boardFinal2.length; i++)
        {
            System.out.println(Arrays.toString(boardFinal2[i]));
        }
        System.out.println("Expected: ");
        for(int i = 0; i < boardExpected2.length; i++)
        {
            System.out.println(Arrays.toString(boardExpected2[i]));
        }
        for(int i = 0; i < boardFinal2.length; i++)
        {
            for(int j = 0; j < boardFinal2[0].length; j++)
            {
                if(boardFinal2[i][j] != boardExpected2[i][j])
                {
                    same = false;
                    break;
                }
            }
        }
        System.out.println("result: " + same);

        same = true;
        System.out.println();
        for(int i = 0; i < boardFinal3.length; i++)
        {
            System.out.println(Arrays.toString(boardFinal3[i]));
        }
        System.out.println("Expected: ");
        for(int i = 0; i < boardExpected3.length; i++)
        {
            System.out.println(Arrays.toString(boardExpected3[i]));
        }
        for(int i = 0; i < boardFinal3.length; i++)
        {
            for(int j = 0; j < boardFinal3[0].length; j++)
            {
                if(boardFinal3[i][j] != boardExpected3[i][j])
                {
                    same = false;
                    break;
                }
            }
        }
        System.out.println("result: " + same);

        same = true;
        System.out.println();
        for(int i = 0; i < boardFinal4.length; i++)
        {
            System.out.println(Arrays.toString(boardFinal4[i]));
        }
        System.out.println("Expected: ");
        for(int i = 0; i < boardExpected4.length; i++)
        {
            System.out.println(Arrays.toString(boardExpected4[i]));
        }
        for(int i = 0; i < boardFinal4.length; i++)
        {
            for(int j = 0; j < boardFinal4[0].length; j++)
            {
                if(boardFinal4[i][j] != boardExpected4[i][j])
                {
                    same = false;
                    break;
                }
            }
        }
        System.out.println("result: " + same);

        same = true;
        System.out.println();
        for(int i = 0; i < boardFinal5.length; i++)
        {
            System.out.println(Arrays.toString(boardFinal5[i]));
        }
        System.out.println("Expected: ");
        for(int i = 0; i < boardExpected5.length; i++)
        {
            System.out.println(Arrays.toString(boardExpected5[i]));
        }
        for(int i = 0; i < boardFinal5.length; i++)
        {
            for(int j = 0; j < boardFinal5[0].length; j++)
            {
                if(boardFinal5[i][j] != boardExpected5[i][j])
                {
                    same = false;
                    break;
                }
            }
        }
        System.out.println("result: " + same);

        same = true;
        System.out.println();
        for(int i = 0; i < boardFinal6.length; i++)
        {
            System.out.println(Arrays.toString(boardFinal6[i]));
        }
        System.out.println("Expected: ");
        for(int i = 0; i < boardExpected6.length; i++)
        {
            System.out.println(Arrays.toString(boardExpected6[i]));
        }
        for(int i = 0; i < boardFinal6.length; i++)
        {
            for(int j = 0; j < boardFinal6[0].length; j++)
            {
                if(boardFinal6[i][j] != boardExpected6[i][j])
                {
                    same = false;
                    break;
                }
            }
        }
        System.out.println("result: " + same);
    }
}
