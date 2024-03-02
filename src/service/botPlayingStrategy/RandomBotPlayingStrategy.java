package service.botPlayingStrategy;

import exception.GameOverException;
import model.*;

import java.util.List;
import java.util.Random;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board, Player player) {
        List<List<Cell>> matrix = board.getMatrix();

        Random rand = new Random();
        int min = 0;
        int max = board.getDimension()-1;
        int randRow = rand.nextInt((max - min)+1);
        int randCol = rand.nextInt((max - min)+1);

        while(matrix.get(randRow).get(randCol).getCellState().equals(CellState.FILLED)){
            randRow = rand.nextInt((max - min)+1);
            randCol = rand.nextInt((max - min)+1);
        }


        if(matrix.get(randRow).get(randCol).getCellState().equals(CellState.EMPTY)){
            matrix.get(randRow).get(randCol).setPlayer(player);
            matrix.get(randRow).get(randCol).setCellState(CellState.FILLED);
            return new Move(matrix.get(randRow).get(randCol), player);
        }
        throw new GameOverException("There are no empty cells in the board");
    }
}
