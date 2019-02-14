import java.util.ArrayList;

public class KnightBoard{


  public static void main(String[] args) {

    //Testing toString
    KnightBoard k = new KnightBoard(2,2);
    System.out.println(k);

    KnightBoard l = new KnightBoard(2,5);
    System.out.println(l);

    KnightBoard m = new KnightBoard(5,2);
    System.out.println(m);

    KnightBoard q = new KnightBoard(10,10);
    System.out.println(q);

    //Testing addKnight
    System.out.println(k.addKnight(0,0));
    System.out.println("Should be true");
    System.out.println("Grid:");
    System.out.println(k);
    System.out.println(k.addKnight(0,0));
    System.out.println("Should be false");
    System.out.println("Grid:");
    System.out.println(k);
    System.out.println(k.addKnight(3,0));
    System.out.println("Should be Bad value false;");
    System.out.println("Grid:");
    System.out.println(k);
    System.out.println(k.addKnight(1,1));
    System.out.println("Should be true");
    System.out.println("Grid:");
    System.out.println(k);
    System.out.println(k.addKnight(10,12));
    System.out.println("Should be Bad value false");
    System.out.println("Grid:");
    System.out.println(k);

    //Testing removeKnight
    System.out.println(k.removeKnight(1,1));
    System.out.println("Should be true");
    System.out.println("Grid:");
    System.out.println(k);
    System.out.println(k.addKnight(12,1));
    System.out.println("Should be false Bad Value");
    System.out.println("Grid:");
    System.out.println(k);

  }

  int[][] board;
  int step;

  /**
  @throws IllegalArgumentException when either parameter is negative.
  **/
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    step = 1;
  }

  public boolean addKnight(int r, int c){
    try{
      if (board[r][c] != 0) return false;
      board[r][c] = step;
      step++;
      return true;
    }
  catch(IndexOutOfBoundsException e){
      System.out.println("Bad value");
    }
    return false;
  }

  public boolean removeKnight(int r, int c){
    try{
      if (board[r][c] == 0) return false;
      board[r][c] = 0;
      step--;
      return true;
    }
  catch(IndexOutOfBoundsException e){
      System.out.println("Bad value");
    }
    return false;
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

  private boolean safe(int r, int c){
    if (r < board.length || c < board[0].length || r >= 0 || c >= 0) return true;
    return false;
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
    for (int i = 0; i < board.length; i+= 2 ){
      //location of next spot
      x = r + moves[i];
      y = c + moves[i+1];
      if (safe(x,y)){
        board[x][y] = move;
        if (solveR(x,y,move + 1)) return true;
        
      }
    }
    return false;
}

  /**
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative or out of bounds.
   **/
  public int countSolutions(int startingRow, int startingCol){
    return 0;
  }

  private boolean solveH(int row ,int col, int level){
    return true;
  }


}
