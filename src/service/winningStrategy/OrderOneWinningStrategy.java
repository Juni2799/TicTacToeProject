package service.winningStrategy;

import model.Board;
import model.Move;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{
    int dimension;
    List<HashMap<Character, Integer>> rowHashMapList;
    List<HashMap<Character, Integer>> colHashMapList;
    HashMap<Character, Integer> leftDiagonal;
    HashMap<Character, Integer> rightDiagonal;
    HashMap<Character, Integer> cornerHashMap;

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        this.leftDiagonal = new HashMap<>();
        this.rightDiagonal = new HashMap<>();
        this.cornerHashMap = new HashMap<>();

        rowHashMapList = new ArrayList<>();
        colHashMapList = new ArrayList<>();

        for(int i=0;i<dimension;i++){
            rowHashMapList.add(new HashMap<>());
            colHashMapList.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        boolean winnerResult = (checkCornerMovePlayed(row, col) && checkAndUpdateForCorners(symbol))
                || (checkAndUpdateRowHashMapList(row, symbol))
                || (checkAndUpdateColHashMapList(col, symbol))
                || (checkLeftDiagonal(row, col) && checkAndUpdateLeftDiagonalHashMap(symbol))
                || (checkRightDiagonal(row, col) && checkAndUpdateRightDiagonalHashMap(symbol));

        return (winnerResult) ? player : null;
    }

    private boolean checkCornerMovePlayed(int row, int col){
        return row == 0 && col == 0 || row == 0 && col == dimension - 1 || row == dimension - 1 && col == 0 || row == dimension - 1 && col == dimension - 1;
    }

    private boolean checkAndUpdateForCorners(char symbol) {
        if(cornerHashMap.containsKey(symbol)){
            cornerHashMap.put(symbol, cornerHashMap.get(symbol)+1);
            return cornerHashMap.get(symbol) == 4;
        } else{
            cornerHashMap.put(symbol, 1);
        }
        return false;
    }

    private boolean checkAndUpdateRowHashMapList(int row, char symbol){
        HashMap<Character, Integer> rowHashMap = rowHashMapList.get(row);
        if(rowHashMap.containsKey(symbol)){
            rowHashMap.put(symbol, rowHashMap.get(symbol) + 1);
            return rowHashMap.get(symbol) == dimension;
        }else{
            rowHashMap.put(symbol, 1);
        }
        return false;
    }

    private boolean checkAndUpdateColHashMapList(int col, char symbol){
        HashMap<Character, Integer> colHashMap = colHashMapList.get(col);
        if(colHashMap.containsKey(symbol)){
            colHashMap.put(symbol, colHashMap.get(symbol) + 1);
            return colHashMap.get(symbol) == dimension;
        }else{
            colHashMap.put(symbol, 1);
        }
        return false;
    }

    private boolean checkLeftDiagonal(int row, int col){
        return row == col;
    }

    private boolean checkAndUpdateLeftDiagonalHashMap(char symbol){
        if(leftDiagonal.containsKey(symbol)){
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);
            return leftDiagonal.get(symbol) == dimension;
        } else {
            leftDiagonal.put(symbol, 1);
        }

        return false;
    }

    private boolean checkRightDiagonal(int row, int col){
        return (row + col) == (this.dimension - 1);
    }

    private boolean checkAndUpdateRightDiagonalHashMap(char symbol){
        if(rightDiagonal.containsKey(symbol)){
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);
            return rightDiagonal.get(symbol) == dimension;
        } else {
            rightDiagonal.put(symbol, 1);
        }

        return false;
    }

}
