package service.drawingStrategy;

import model.Board;
import model.CellState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OrderNSqDrawingStrategy implements DrawingStrategy{

    Board board;
    int dimension;
    int drawCounter = 0;

    public OrderNSqDrawingStrategy(Board board){
        this.board = board;
        dimension = board.getDimension();
    }

    @Override
    public boolean isDraw(Board board) {
        for(int row=0;row<dimension;row++){
            for(int col=0;col<dimension;col++){
                boolean isCellFilled = board.getMatrix().get(row).get(col).getCellState().equals(CellState.FILLED);
                if(isCellFilled){
                    drawCounter++;
                }
            }
        }

        return drawCounter == (dimension * dimension);
    }
}
