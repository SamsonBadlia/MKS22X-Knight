import java.util.ArrayList;

public class KnightBoard{


  public static void main(String[] args) {
      KnightBoard kb = new KnightBoard(5, 5);
      long timeStart = System.currentTimeMillis();
      int solutionsFound = 0;
      for(int i = 0; i < 5; i++){
          for(int j = 0; j < 5; j++){
              solutionsFound += kb.countSolutions(i, j);
          }
      }
    }

  int[][] board;
  int[][] moves = new int[][]{
    {2,1} , {1,2} , {-1,2} , {-2,1}, {-2,-1}, {-1,-2}, {1,-2}, {2,-1}
                              };

  /**
  @throws IllegalArgumentException when either parameter is negative.
  **/
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
  }

  public boolean addKnight(int r, int c, int step){
      if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return false;
      if (board[r][c] != 0) return false;
      else{
        board[r][c] = step;
        return true;
      }
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
      return board[0][0] != 0 ;
    }

 /**
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative or out of bounds.
   **/
  public boolean solve(int startingRow, int startingCol){
    clear();
    if (empty()) throw new IllegalStateException();
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) throw new IllegalArgumentException();
    return solveR(startingRow,startingCol,1);
   }

   private boolean solveR(int r, int c, int move){
     // tracks coordinate of next square
    int x,y;
    if (move == board.length * board[0].length) return true;
    //incrementing by 2 because the array is a pair of values (x,y)
    for (int i = 0; i < moves.length - 1; i++ ){
      //checks if knight can be moved there
      x = r + moves[i][0];
      y = c + moves[i + 1][0];
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
    if (empty()) throw new IllegalStateException();
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) throw new IllegalArgumentException();
    addKnight(startingRow,startingCol,1);
    return countR(startingRow,startingCol,2);
  }

  private int countR(int r, int c, int move){
   int count = 0;
   if (move >= board.length * board[0].length) return 1;
   //incrementing by 2 because the array is a pair of values (x,y)
   for (int i = 0; i < moves.length - 1  ; i++){
     //checks if knight can be moved there
     int x = r + moves[i][0];
     int y = c + moves[i + 1][0];
     if (addKnight(x,y, move)){
       count += countR(x,y , move+1);
       removeKnight(x,y);
     }
   }
   return count;
}


}
