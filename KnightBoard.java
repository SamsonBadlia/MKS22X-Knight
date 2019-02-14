import java.util.ArrayList;

public class KnightBoard{


  public static void main(String[] args) {

    KnightBoard k = new KnightBoard(5,5);
    System.out.println(k.solve(0,0));
    System.out.println("Should be true");
    System.out.println(k.countSolutions(0,0));
    System.out.println("Should be 304");

  }

  int[][] board;

  /**
  @throws IllegalArgumentException when either parameter is negative.
  **/
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
  }

  public boolean addKnight(int r, int c, int step){
      if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return false;
      if (board[r][c] != 0) return false;
      board[r][c] = step;
      return true;
  }

  public boolean removeKnight(int r, int c){
      if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return false;
      if (board[r][c] == 0) return false;
      board[r][c] = 0;
      return true;
  }

  public String toString(){
    String s = "\n";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length;j++){
        if (board.length * board[0].length >= 10) s += " _" ;
        s += board[i][j] + " ";
        if (j == board[i].length - 1) s += "\n";
      }
    }
    return s;
  }

  private void clear(){
  for (int i = 0; i < board.length; i++){
    for (int j = 0; j < board[0].length; j++){
      board[i][j] = 0;
      }
    }
  }

  private boolean empty(){
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[0].length; j++){
        if (board[i][j] != 0) return false;
        }
      }
      return true;
    }

 /**
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative or out of bounds.
   **/
  public boolean solve(int startingRow, int startingCol){
    if (!empty()) throw new IllegalStateException();
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) throw new IllegalArgumentException();
    return solveR(0,0,1);
   }

   private boolean solveR(int r, int c, int move){
     // tracks coordinate of next square
    int x,y;
    if (move == board.length * board[0].length) return true;
    //all possible moves in x,y format
    int[] moves = { 2,1, 1,2, -1,2, -2,1, -2,-1, -1,-2, 1,-2, 2,-1};
    //incrementing by 2 because the array is a pair of values (x,y)
    for (int i = 0; i < moves.length; i += 2 ){
      //location of next spot
      x = r + moves[i];
      y = c + moves[i+1];
      //checks if knight can be moved there
      if (addKnight(x,y,move)){
        if (solveR(x,y,move + 1)) return true;
        else{
          removeKnight(x,y);
        }
      }
    }
    return false;
}

  /**
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative or out of bounds.
   **/
  public int countSolutions(int startingRow, int startingCol){
    clear();
    if (!empty()) throw new IllegalStateException();
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) throw new IllegalArgumentException();
    return countR(0,0,1);
  }

  private int countR(int r, int c, int move){
    // tracks coordinate of next square
   int x,y;
   int count = 0;
   if (move == board.length * board[0].length) return 1;
   //all possible moves in x,y format
   int[] moves = { 2,1, 1,2, -1,2, -2,1, -2,-1, -1,-2, 1,-2, 2,-1};
   //incrementing by 2 because the array is a pair of values (x,y)
   for (int i = 0; i < moves.length; i += 2 ){
     //location of next spot
     x = r + moves[i];
     y = c + moves[i+1];
     //checks if knight can be moved there
     if (addKnight(x,y,move)){
       count += countR(r,c,move+1);
       removeKnight(x,y);
     }
   }
   return count;
}


}
