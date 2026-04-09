import java.util.*;

class Player1 {
    String name;
    char symbol;

    public Player1(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}

class Cell1 {
    int row, col;
    char value;

    public Cell1(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = '-';
    }

    public boolean isEmpty() {
        return value == '-';
    }
}

class Board1 {
    int size;
    Cell1[][] grid;

    Board1(int size) {
        this.size = size;
        grid = new Cell1[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                grid[i][j] = new Cell1(i, j);
            }
        }
    }

    public int getBoardSize() {
        return size;
    }

     public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((char)grid[i][j].value + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(int row, int col, char symbol) {
        if(grid[row][col].value != '-')
            return false;
        grid[row][col].value = symbol;
        return true;
    }
}

class Game1 {
    Board board;
    Queue<Player1> players;

    Map<Player1, int[]> rowMap;
    Map<Player1, int[]> colMap;
    Map<Player1, Integer> diagMap;
    Map<Player1, Integer> antiDiagMap;

    public Game1(int n) {
        board = new Board(n);
        players = new LinkedList<>();

        Player1 p1 = new Player1("Player1", 'X');
        Player1 p2 = new Player1("Player2", 'O');

        players.add(p1);
        players.add(p2);

        rowMap = new HashMap<>();
        colMap = new HashMap<>();
        diagMap = new HashMap<>();
        antiDiagMap = new HashMap<>();

        rowMap.put(p1, new int[n]);
        rowMap.put(p2, new int[n]);

        colMap.put(p1, new int[n]);
        colMap.put(p2, new int[n]);

        diagMap.put(p1, 0);
        antiDiagMap.put(p1, 0);
        diagMap.put(p2, 0);
        antiDiagMap.put(p2, 0);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int moves = 0;
        int size = board.getBoardSize();

        while(true) {
            board.printBoard();

            Player1 current = players.poll();
            System.out.println(current.name + "'s turn");

            System.out.println("Enter row");
            int row = sc.nextInt();

            System.out.println("Enter column");
            int col = sc.nextInt();

            if(!board.makeMove(row, col, current.symbol)) {
                System.out.println("Invalid move, try again");
                players.add(current);
                continue;
            }

            moves++;

            int[] rows = rowMap.get(current);
            int[] cols = colMap.get(current);

            rows[row]++;    
            cols[col]++;

            if (row == col) {
                diagMap.put(current, diagMap.get(current) + 1);
            }

            if (row + col == size - 1) {
                antiDiagMap.put(current, antiDiagMap.get(current) + 1);
            }

            // Check win
            if (rows[row] == size ||
                cols[col] == size ||
                diagMap.get(current) == size ||
                antiDiagMap.get(current) == size) {

                board.printBoard();
                System.out.println(current.name + " wins!");
                return;
            }

            if(moves == (size * size)) {
                board.printBoard();
                System.out.println("Game Draw!");
                break;
            }

            players.add(current);
        }
    }
}

public class TicTacToe {
    public static void main(String[] args) {
        Game game = new Game(3);
        game.start();
    }
}


