package controller;

import model.*;
import service.winningStrategy.WinningStrategy;
import service.winningStrategy.WinningStrategyFactory;
import service.winningStrategy.WinningStrategyName;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players, WinningStrategyName name){
        return Game.builder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategy(WinningStrategyFactory.getWinningStrategy(name, dimension))
                .build();
    }

    public void displayBoard(Game game){
        game.getCurrentBoard().displayBoard();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public Move executeMove(Game game, Player player){
        return player.makeMove(game.getCurrentBoard());
    }

    public Player checkWinner(Game game, Move lastMovePlayed){
        return game.getWinningStrategy().checkWinner(game.getCurrentBoard(), lastMovePlayed);
    }

    public Board undoMove(Game game, Move lastPlayedMove){
        List<Move> moves = game.getMoves();
        int cellRow = lastPlayedMove.getCell().getRow();
        int cellCol = lastPlayedMove.getCell().getCol();
        game.getCurrentBoard().getMatrix().get(cellRow).get(cellCol).setCellState(CellState.EMPTY);
        lastPlayedMove.getCell().setCellState(CellState.EMPTY);
        moves.remove(moves.size() - 1);

        game.setMoves(moves);

        List<Board> boardStates = game.getBoardStates();
        boardStates.remove(boardStates.size() - 1);
        game.setBoardStates(boardStates);

        return boardStates.get(boardStates.size() - 1);
    }

    public Game replayGame(Game game){
        int dimension = game.getCurrentBoard().getDimension();
        List<Player> players = game.getPlayers();
        WinningStrategyName name = WinningStrategyName.ORDERONEWINNINGSTRATEGY;

        return createGame(dimension, players, name);
    }

    public void updateMovesList(Game game, Move move){
        game.updateMovesList(move);
    }

    public void updateBoardStatesList(Game game){
        game.updateBoardStatesList(game.getCurrentBoard());
    }

}
