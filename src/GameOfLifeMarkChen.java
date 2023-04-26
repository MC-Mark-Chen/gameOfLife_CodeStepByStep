import java.util.Arrays;

public class GameOfLifeMarkChen {
    public static int corner(int corner, int element1, int element2, int element3)
    {
        int livingCount = 0;

        if(element1 == 1)
        {
            livingCount++;
        }
        if(element2 == 1)
        {
            livingCount++;
        }
        if(element3 == 1)
        {
            livingCount++;
        }

        if(corner == 1 && (livingCount < 2 || livingCount > 3))
        {
            corner = 0;
        }
        else if(corner == 1 && (livingCount == 2 || livingCount == 3))
        {
            corner = 1;
        }
        else if(corner == 0 && livingCount == 3)
        {
            corner = 1;
        }

        return corner;
    }

    //position = true when upper or bottom, position = false when left or right
    public static int[] edge(int[][] board, boolean position)
    {
        if(position)
        {
            int[] output = new int[board[0].length - 2];

            for(int i = 1; i < output.length + 1; i++)
            {
                int livingCount = 0;

                if(board[0][i + 1] == 1)
                {
                    livingCount++;
                }
                if(board[0][i - 1] == 1)
                {
                    livingCount++;
                }
                if(board[1][i + 1] == 1)
                {
                    livingCount++;
                }
                if(board[1][i] == 1)
                {
                    livingCount++;
                }
                if(board[1][i - 1] == 1)
                {
                    livingCount++;
                }

                if(board[0][i] == 1 && (livingCount < 2 || livingCount > 3))
                {
                    output[i - 1] = 0;
                }
                else if(board[0][i] == 1 && (livingCount == 2 || livingCount == 3))
                {
                    output[i - 1] = 1;
                }
                else if(board[0][i] == 0 && livingCount == 3)
                {
                    output[i - 1] = 1;
                }
            }

            return output;
        }
        else
        {
            int[] output = new int[board.length - 2];

            for(int i = 1; i < output.length + 1; i++)
            {
                int livingCount = 0;

                if(board[i + 1][0] == 1)
                {
                    livingCount++;
                }
                if(board[i - 1][0] == 1)
                {
                    livingCount++;
                }
                if(board[i + 1][1] == 1)
                {
                    livingCount++;
                }
                if(board[i][1] == 1)
                {
                    livingCount++;
                }
                if(board[i - 1][1] == 1)
                {
                    livingCount++;
                }

                if(board[i][0] == 1 && (livingCount < 2 || livingCount > 3))
                {
                    output[i - 1] = 0;
                }
                else if(board[i][0] == 1 && (livingCount == 2 || livingCount == 3))
                {
                    output[i - 1] = 1;
                }
                else if(board[i][0] == 0 && livingCount == 3)
                {
                    output[i - 1] = 1;
                }
            }

            return output;
        }
    }

    public static int[][] gameOfLife(int[][] board)
    {

        if(board.length < 2 && board[0].length < 2)
        {
            board[0][0] = 0;
            return board;
        }

        final int LAST_COL_INDEX = board[0].length - 1;
        final int LAST_ROW_INDEX = board.length - 1;

        int[][] outputBoard = new int[board.length][board[0].length];

        //upper left corner initialization
        int cornerUL;
        //upper right corner initialization
        int cornerUR;
        //bottom left corner initialization
        int cornerBL;
        //bottom right corner initialization
        int cornerBR;

        //temporary upper edge initialization
        int[][] edgeTempU = new int[2][board[0].length];
        //temporary bottom edge initialization
        int[][] edgeTempB = new int[2][board[0].length];
        //temporary left edge initialization
        int[][] edgeTempL = new int[board.length][2];
        //temporary right edge initialization
        int[][] edgeTempR = new int[board.length][2];

        //upper edge initialization
        int[] edgeU = new int[board[0].length - 2];
        //bottom edge initialization
        int[] edgeB = new int[board[0].length - 2];
        //left edge initialization
        int[] edgeL = new int[board[0].length - 2];
        //right edge initialization
        int[] edgeR = new int[board[0].length - 2];

        //upper left corner assignment
        cornerUL = corner(board[0][0], board[0][1], board[1][0], board[1][1]);
        //upper right corner assignment
        cornerUR = corner(board[0][LAST_COL_INDEX], board[0][LAST_COL_INDEX - 1], board[1][LAST_COL_INDEX], board[1][LAST_COL_INDEX - 1]);
        //bottom left corner assignment
        cornerBL = corner(board[LAST_ROW_INDEX][0], board[LAST_ROW_INDEX][1], board[LAST_ROW_INDEX - 1][0], board[LAST_ROW_INDEX - 1][1]);
        //bottom right corner assignment
        cornerBR = corner(board[LAST_ROW_INDEX][LAST_COL_INDEX], board[LAST_ROW_INDEX][LAST_COL_INDEX - 1], board[LAST_ROW_INDEX - 1][LAST_COL_INDEX], board[LAST_ROW_INDEX - 1][LAST_COL_INDEX - 1]);

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                edgeTempU[i][j] = board[i][j];
                edgeTempB[i][j] = board[LAST_ROW_INDEX - i][j];
            }
        }

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                edgeTempL[i][j] = board[i][j];
                edgeTempR[i][j] = board[i][LAST_COL_INDEX - j];
            }
        }

        //upper edge assignment
        edgeU = edge(edgeTempU, true);
        //bottom edge assignment
        edgeB = edge(edgeTempB, true);
        //left edge assignment
        edgeL = edge(edgeTempL, false);
        //right edge assignment
        edgeR = edge(edgeTempR, false);

        //center assignment
        for(int i = 1; i < LAST_ROW_INDEX; i++)
        {
            for(int j = 1; j < LAST_COL_INDEX; j++)
            {
                int livingCount = 0;

                if(board[i - 1][j - 1] == 1)
                {
                    livingCount++;
                }
                if(board[i - 1][j] == 1)
                {
                    livingCount++;
                }
                if(board[i - 1][j + 1] == 1)
                {
                    livingCount++;
                }
                if(board[i][j - 1] == 1)
                {
                    livingCount++;
                }
                if(board[i][j + 1] == 1)
                {
                    livingCount++;
                }
                if(board[i + 1][j - 1] == 1)
                {
                    livingCount++;
                }
                if(board[i + 1][j] == 1)
                {
                    livingCount++;
                }
                if(board[i + 1][j + 1] == 1)
                {
                    livingCount++;
                }

                if(board[i][j] == 1 && (livingCount < 2 || livingCount > 3))
                {
                    outputBoard[i][j] = 0;
                }
                else if(board[i][j] == 1 && (livingCount == 2 || livingCount == 3))
                {
                    outputBoard[i][j] = 1;
                }
                else if(board[i][j] == 0 && livingCount == 3)
                {
                    outputBoard[i][j] = 1;
                }
            }
        }

        outputBoard[0][0] = cornerUL;
        outputBoard[0][LAST_COL_INDEX] = cornerUR;
        outputBoard[LAST_ROW_INDEX][0] = cornerBL;
        outputBoard[LAST_ROW_INDEX][LAST_COL_INDEX] = cornerBR;

        for(int i = 0; i < edgeU.length; i++)
        {
            outputBoard[0][i + 1] = edgeU[i];
            outputBoard[LAST_ROW_INDEX][i + 1] = edgeB[i];
        }

        for(int i = 0; i < edgeL.length; i++)
        {
            outputBoard[i + 1][0] = edgeL[i];
            outputBoard[i + 1][LAST_COL_INDEX] = edgeR[i];
        }

        return outputBoard;
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