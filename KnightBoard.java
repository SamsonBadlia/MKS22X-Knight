import java.util.ArrayList;

public class KnightBoard{


  public static void main(String[] args) {
/*
    KnightBoard i = new KnightBoard(1,1);
    KnightBoard ii = new KnightBoard(2,2);
    KnightBoard iii = new KnightBoard(3,3);
    KnightBoard v = new KnightBoard(5,5);
    KnightBoard a = new KnightBoard(20,20);
    KnightBoard b = new KnightBoard(35,35);
    KnightBoard c = new KnightBoard(45,45);
    System.out.println(i.solve(0,0));
    System.out.println(a.solve(0,0));
    System.out.println(b.solve(0,0));
    System.out.println(c.solve(0,0));
    System.out.println(v.solve(1,1));
    System.out.println(iii.solve(0,0));
    System.out.println(v.countSolutions(0,0));
    System.out.println(v.countSolutions(2,2));

    System.out.println();
*/
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);
    runTest(0);


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
     addKnight(r,c,move);
    int x,y;
    if (move >= board.length * board[0].length) return true;
    //incrementing by 2 because the array is a pair of values (x,y)
    for (int i = 0; i < moves.length; i++ ){
      //checks if knight can be moved there
      x = r + moves[i][0];
      y = c + moves[i][1];
      if (addKnight(x,y,move)){
        if (solveR(x,y,move + 1)) return true;
          removeKnight(x,y);
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
    return countR(startingRow,startingCol,1);
  }

  private int countR(int r, int c, int move){
    int count = 0;
    addKnight(r,c,move);
   if (move == board.length * board[0].length) return 1;
   //incrementing by 2 because the array is a pair of values (x,y)
   for (int i = 0; i < moves.length ; i++){
     //checks if knight can be moved there
     int x = r + moves[i][0];
     int y = c + moves[i][1];
     if (addKnight(x,y, move)){
       count += countR(x,y , move+1);
       removeKnight(x,y);
     }
   }
   return count;
}

//testcase must be a valid index of your input/output array
public static void runTest(int i){

  KnightBoard b;
  int[]m =   {4,5,5,5,5};
  int[]n =   {4,5,4,5,5};
  int[]startx = {0,0,0,1,2};
  int[]starty = {0,0,0,1,2};
  int[]answers = {0,304,32,56,64};
  if(i >= 0 ){
    try{
      int correct = answers[i];
      b = new KnightBoard(m[i%m.length],n[i%m.length]);

      int ans  = b.countSolutions(startx[i],starty[i]);

      if(correct==ans){
        System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
      }
    }catch(Exception e){
      System.out.println("FAIL Exception case: "+i);

    }
  }
}


}
