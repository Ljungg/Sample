import java.awt.event.*;
import java.util.ArrayList;

import Pieces.*;

public class MoveListener implements MouseMotionListener, MouseListener{

    private Board board;
    private Pieces dragPiece;
    private int dragOffsetX;
    private int dragOffsetY;

    public MoveListener(Board board){
        this.board = board;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getPoint().x;
        int y = e.getPoint().y;

        for(int i = 0; i < this.board.getInstanceBoard().size(); i++){
            for(Pieces piece:this.board.getInstanceBoard().get(i)){
                if(mouseOverPiece(piece,x,y)){
                    this.dragOffsetX = x - piece.getX() * board.getDimTile();
                    this.dragOffsetY = y - piece.getY() * board.getDimTile();
                    this.dragPiece = piece;
                    this.board.remove(this.dragPiece);
                    break;
                }


            }

        }
        if(this.dragPiece != null){

            this.board.add(this.dragPiece);
        }

    }

    private boolean mouseOverPiece(Pieces piece, int x, int y){
        if(piece == null) return false;
        return piece.getX() * board.getDimTile() <= x
                && piece.getX() * board.getDimTile() + this.board.getDimTile() >= x
                && piece.getY() * board.getDimTile() <= y
                && piece.getY() * board.getDimTile() + this.board.getDimTile() >= y;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.dragPiece = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.dragPiece != null){
            this.dragPiece.setX(e.getPoint().x - this.dragOffsetX);
            this.dragPiece.setY(e.getPoint().y - this.dragOffsetY);
            this.board.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
