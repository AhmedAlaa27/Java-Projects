import java.util.*;
public class main {
    static char[][] board = new char[3][3];
    static char PLAYER = 'X';
    static char COM = 'O';
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {

        char winner = ' ';
        resetboard();
        while (winner == ' ' && checkfreespaces() != 0){
            printboard();
            playermove();
            winner = checkwinenr();
            if (winner != ' '|| checkfreespaces() == 0){
                break;
            }
            commove();
            winner = checkwinenr();
            if (winner != ' '|| checkfreespaces() == 0){
                break;
            }
        }
        printboard();
        printwinner(winner);

    }
    static void resetboard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                char c = ' ';
                board[i][j] = ' ';
            }
        }
    }
    static void printboard(){
        System.out.printf(" %c | %c | %c ", board[0][0], board[0][1], board[0][2]);
        System.out.println("\n---|---|---");
        System.out.printf(" %c | %c | %c ", board[1][0], board[1][1], board[1][2]);
        System.out.println("\n---|---|---");
        System.out.printf(" %c | %c | %c ", board[2][0], board[2][1], board[2][2]);
        System.out.println();
    }
    static int checkfreespaces(){
        int freespaces = 9;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] != ' '){
                    freespaces--;
                }
            }
        }
        return freespaces;
    }
    static void playermove(){
        int x; int y;
        do {
            System.out.print("Enter Row (1-3): ");
            x = scanner.nextInt();
            x--;
            System.out.print("Enter Column (1-3): ");
            y = scanner.nextInt();
            y--;
            if (board[x][y] != ' '){
                System.out.println("Invalid Move");
            }else {
                board[x][y] = PLAYER;
                break;
            }
        } while (board[x][y] != ' ');

    }
    static void commove(){
        int x; int y;
        if (checkfreespaces() > 0){
            do {
                x = random.nextInt(3);
                y = random.nextInt(3);
            }while (board[x][y] != ' ');
            board[x][y] = COM;
        }else {
            printwinner(' ');
        }
    }
    static char checkwinenr(){
        //Check Rows
        for (int i = 0; i < 3; i++){
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                return board[i][0];
            }
        }
        //Check Columns
        for (int i = 0; i < 3; i++){
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]){
                return board[0][i];
            }
        }
        //Check diagonals
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]){
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]){
            return board[0][0];
        }
        return ' ';
    }
    static void printwinner(char winner){
        if (winner == PLAYER){
            System.out.println("YOU WIN!");
        }else if (winner == COM){
            System.out.println("COMPUTER WIN!");
        }else {
            System.out.println("IT'S A TIE!");
        }
    }
}