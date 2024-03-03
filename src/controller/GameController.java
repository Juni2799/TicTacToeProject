package controller;

import model.*;
import service.drawingStrategy.DrawingStrategyFactory;
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

    // Function to check is the game ended with a draw or not
    // Need to try and optimise further
    public boolean checkDraw(Board board){
        return DrawingStrategyFactory.getDrawingStrategy(board).isDraw(board);
    }

    // Currently, the project only undoes one move
    // Plan to add a feature later where you can undo the game till a given point
    public void undoMove(Game game, Move lastPlayedMove){
        List<Move> moves = game.getMoves();
        lastPlayedMove.getCell().setCellState(CellState.EMPTY);
        lastPlayedMove.getCell().setPlayer(null);
        moves.remove(moves.size() - 1);

        game.setMoves(moves);

        List<Board> boardStates = game.getBoardStates();
        boardStates.remove(boardStates.size() - 1);
        game.setBoardStates(boardStates);

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
