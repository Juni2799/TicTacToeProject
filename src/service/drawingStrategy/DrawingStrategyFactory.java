package service.drawingStrategy;

import model.Board;

public class DrawingStrategyFactory {
    public static DrawingStrategy getDrawingStrategy(Board board){
        return new OrderNSqDrawingStrategy(board);
    }
}
