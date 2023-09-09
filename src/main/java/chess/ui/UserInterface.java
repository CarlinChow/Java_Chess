package chess.ui;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import chess.domain.*;
import chess.logic.Game;
import static chess.logic.types.GameStatus.*;
import static chess.types.Color.*;
import chess.logic.exceptions.*;

public class UserInterface {
    private Scanner scanner;
    private Game game;

    public UserInterface(Scanner scanner){
        this.scanner = scanner;
    }

    public void start(){
        this.mainMenu();
    }

    private void mainMenu(){
        // options:
        // (1) Start New Game
        // (2) Quit
        while(true){
            System.out.println("Welcome to my Chess game!!!\n(1)Start new game\n(2)Quit");
            System.out.print("Enter input here --> ");
            String input = this.scanner.nextLine();
            if(input.equals("1")){
                System.out.println();
                this.playGame();
            }else if(input.equals("2")){
                System.out.println("Exiting program...");
                break;
            }else{
                System.out.println("Invalid input, please try again...");
            }
        }
    }

    private void playGame(){
        this.game = new Game();
        Board board = game.getBoard();
        Pattern moveInputPattern = Pattern.compile("^[a-h][1-8]\s[a-h][1-8]$", Pattern.CASE_INSENSITIVE);
        Pattern getMovesInputPattern = Pattern.compile("^GETMOVES\s[a-h][1-8]$", Pattern.CASE_INSENSITIVE);
        Pattern validPawnPromotionInput = Pattern.compile("^[qbrk]$", Pattern.CASE_INSENSITIVE);

        while(this.game.getGameStatus() == ACTIVE){
            System.out.println("PLAYER " + this.game.getCurrentTurn() + "'S TURN");
            System.out.println("1. To make a move, input should be in the format \"<<position of piece>> <<desired position>>\"");
            System.out.println("2. To resign from the game, type \"resign\"");
            System.out.println("3. To get possible moves of a piece, type \"getmoves <<position of piece>>\"");
            System.out.println();
            this.printOrientatedBoard();
            if(this.game.getInCheck() == this.game.getCurrentTurn()){
                System.out.println("Your king is currently in check");
            }
            System.out.print("Enter input here --> ");
            String input = this.scanner.nextLine();
            if(this.validateInput(input, moveInputPattern)){ // normal move input
                Spot startSpot = board.getSpotAt(input.split(" ")[0]);
                if(!startSpot.isEmpty()){
                    try{
                        game.makeMove(startSpot.getPiece(), input.split(" ")[1]);
                        this.printNonOrientatedBoard();
                        System.out.print("Enter any key to continue...");
                        this.scanner.nextLine();
                    }
                    catch(PawnPromotionException e){
                        this.printOrientatedBoard();
                        while(true){
                            System.out.println("One of your pawns can be promoted, choose what piece type it will be promote to");
                            System.out.println("(Q) Queen\n(B) Bishop\n(R) Rook\n(K) Knight");
                            System.out.print("Enter input here -- >");
                            input = scanner.nextLine();
                            if(this.validateInput(input, validPawnPromotionInput)){
                                this.game.promotePawn(input);
                                System.out.print("Enter any key to continue...");
                                this.scanner.nextLine();
                                break;
                            }else{
                                System.out.println("Invalid input, Please try again");
                            }
                        }
                        this.printNonOrientatedBoard();
                    }
                    catch(Exception e){
                        System.out.println("ERROR: " + e.getMessage().toUpperCase());
                        System.out.print("Enter any key to continue...");
                        this.scanner.nextLine();
                    }
                }else{
                    System.out.println("Invalid input, there is no piece on the spot " + startSpot);
                }
            }else if(this.validateInput(input, getMovesInputPattern)){ // getMove command
                Spot spot = board.getSpotAt(input.split(" ")[1]);
                if(!spot.isEmpty()){
                    Set<Spot> moves = spot.getPiece().getMoves(board);
                    if(moves.size() == 0){
                        System.out.println("This piece has no possible moves");
                    }else{
                        System.out.print("This " + spot.getPiece().getClass().getSimpleName() +  " can move to [ ");
                        for(Spot moveSpot : moves){
                            System.out.print(moveSpot + " ");
                        }
                        System.out.println("]");
                    }
                    System.out.print("Enter any key to continue...");
                    this.scanner.nextLine();
                }else{
                    System.out.println("Invalid input, there is no piece on the spot " + spot);
                }
            }else if(input.equalsIgnoreCase("resign")){ // resign command
                game.resignGame();
            }else{
                System.out.println("Invalid input, please try again");
            }
            System.out.println("--------------------------\n\n\n");
        }
        System.out.println("GAME HAS CONCLUDED, " + this.game.getGameStatus() + "\n");
    }

    private boolean validateInput(String input, Pattern pattern){
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private void printOrientatedBoard(){
        Board board = game.getBoard();
        if(this.game.getCurrentTurn() == WHITE){
            board.print();
        }else{
            board.printInverse();
        }
    }

    private void printNonOrientatedBoard(){
        Board board = game.getBoard();
        if(this.game.getCurrentTurn() == WHITE){
            board.printInverse();
        }else{
            board.print();
        }
    }
}
