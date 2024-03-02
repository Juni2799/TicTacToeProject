package model;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(){
    }

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeMove(Board board){
        Scanner sc = new Scanner(System.in);
        System.out.println(this.name + " -> Enter the row for the target cell");
        int row = sc.nextInt();
        System.out.println(this.name + " -> Enter the col for the target cell");
        int col = sc.nextInt();

        //TODO: validate the row and col entered by the player | ex : inbound checks, filled or not filled etc
        while(invalidInput(row, col, board) || invalidCell(row, col, board)){
            System.out.println(this.name + " Please enter row and col for a empty cell and within board dimension:");
            System.out.println("Row:");
            row = sc.nextInt();
            System.out.println("Column:");
            col = sc.nextInt();
        }

        Cell playedMoveCell = board.getMatrix().get(row).get(col);
        playedMoveCell.setCellState(CellState.FILLED);
        playedMoveCell.setPlayer(this);


        return new Move(playedMoveCell, this);
    }

    private boolean invalidInput(int row, int col, Board board){
        return (row < 0 || row >= board.getDimension()) || (col < 0 || col >= board.getDimension());
    }

    private boolean invalidCell(int row, int col, Board board){
        return (board.getMatrix().get(row).get(col).getCellState().equals(CellState.FILLED));
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
