import controller.GameController;
import model.*;
import service.winningStrategy.WinningStrategyName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        int id = 1;
        List<Player> players = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to TicTacToe Game");
        System.out.println("Please enter the dimension for the board");
        int dimension = sc.nextInt();
        System.out.println("Do you want a bot in the game ? Y or N");
        String botAns = sc.next();
        if(botAns.equalsIgnoreCase("Y")){
            Player bot = new Bot(id++, '$', BotDifficultyLevel.HARD);
            players.add(bot);
        }
        while(id < dimension){
            System.out.println("Please enter the player name:");
            String playerName = sc.next();
            System.out.println("Please enter the player symbol:");
            char symbol = sc.next().charAt(0);
            Player newPlayer = new Player(id++, playerName, symbol, PlayerType.HUMAN);
            players.add(newPlayer);
        }
        Collections.shuffle(players); //randomise the player list
        Game game = gameController.createGame(dimension, players, WinningStrategyName.ORDERONEWINNINGSTRATEGY);

        boolean replayGame;
        do{
            int playerIndex = -1;
            while(game.getGameStatus().equals(GameStatus.IN_PROGRESS)){
                System.out.println("Current board status");
                gameController.displayBoard(game);

                playerIndex++;
                playerIndex = playerIndex % players.size();
                Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
                Player winner = gameController.checkWinner(game, movePlayed);
                if(winner != null){
                    System.out.println("WINNER IS : " + winner.getName());
                    game.setGameStatus(GameStatus.WINNER);
                    break;
                }

                gameController.updateMovesList(game, movePlayed);
                gameController.updateBoardStatesList(game);
            }
            System.out.println("Final Board Status");
            gameController.displayBoard(game);
            System.out.println("Do you want to replay? Y or N");
            String replayAnswer = sc.next();
            if(replayAnswer.equalsIgnoreCase("Y")){
                game = gameController.replayGame(game);
                replayGame = true;
            }else{
                replayGame = false;
            }
        }while(replayGame);

    }
}