import java.util.*;

// ---------------- STRATEGY ----------------
interface WinningStrategy {
    boolean checkWinner(Player player, int row, int col);
}

class Player {
    int id;
    String name;
    char symbol;

    public Player(int id, String name, char symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }
}

class Cell {
    int row, col;
    char value;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = '-';
    }

    public boolean isEmpty() {
        return value == '-';
    }
}

class Board {
    int size;
    Cell[][] grid;

    Board(int size) {
        this.size = size;
        grid = new Cell[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                grid[i][j] = new Cell(i, j);
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

class SimpleWinningStrategy implements WinningStrategy {
    Map<Integer, int[]> rowMap;
    Map<Integer, int[]> colMap;
    Map<Integer, Integer> diagMap;
    Map<Integer, Integer> antiDiagMap;

    int size;

    public SimpleWinningStrategy(int size, List<Player> players) {
        this.size = size;

        rowMap = new HashMap<>();
        colMap = new HashMap<>();
        diagMap = new HashMap<>();
        antiDiagMap = new HashMap<>();

        for (Player p : players) {
            rowMap.put(p.id, new int[size]);
            colMap.put(p.id, new int[size]);
            diagMap.put(p.id, 0);
            antiDiagMap.put(p.id, 0);
        }
    }

    @Override
     public boolean checkWinner(Player player, int row, int col) {
            int id = player.id;
            int[] rows = rowMap.get(id);
            int[] cols = colMap.get(id);

            rows[row]++;    
            cols[col]++;

            if (row == col) {
                diagMap.put(id, diagMap.get(id) + 1);
            }

            if (row + col == size - 1) {
                antiDiagMap.put(id, antiDiagMap.get(id) + 1);
            }

            // Check win
            if (rows[row] == size ||
                cols[col] == size ||
                diagMap.get(id) == size ||
                antiDiagMap.get(id) == size) {
                return true;
            }

            return false;
    }
}

class Game {
    Board board;
    Queue<Player> players;
    WinningStrategy winningStrategy;

    public Game(int n) {
        board = new Board(n);
        players = new LinkedList<>();

        Player p1 = new Player(1, "Player1", 'X');
        Player p2 = new Player(2, "Player2", 'O');

        players.add(p1);
        players.add(p2);

        List<Player> playerList = Arrays.asList(p1, p2);
        winningStrategy = new SimpleWinningStrategy(n, playerList);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int moves = 0;
        int size = board.getBoardSize();

        while(true) {
            board.printBoard();

            Player current = players.poll();
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

            if (winningStrategy.checkWinner(current, row, col)) {
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

public class TicTacToeUsingStrategy {
    public static void main(String[] args) {
        Game game = new Game(3);
        game.start();
    }
}


