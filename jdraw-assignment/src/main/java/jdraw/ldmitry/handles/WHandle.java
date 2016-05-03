package jdraw.ldmitry.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;

public class WHandle extends AbstractHandle {

	public WHandle(Figure owner, Point location) {
		super(owner, location);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		java.awt.Rectangle bounds = owner.getBounds();
		int oldX = bounds.x;
		int newX = oldX - (anchor.x - x);
		int newY = bounds.y;
		int newWidth = bounds.width + (oldX - newX);
		owner.setBounds(new Point(newX, newY), 
				new Point(newX + newWidth, newY + bounds.height));	
	}
	
	public  void figureChanged(FigureEvent e) {
		java.awt.Rectangle bounds = e.getFigure().getBounds();
		int newXOwner = bounds.x;
		int newYOwner = bounds.y;
		int newWidth = bounds.width;
		int newHeight = bounds.height;
		int newXHandle = newXOwner;
		int newYHandle = newYOwner + (int) (newHeight/2);
		int delXHandle = newXHandle - location.x;
		int delYHandle = newYHandle - location.y;
		location = new Point(newXHandle, newYHandle);
		rectangle = new java.awt.Rectangle(rectangle.x + delXHandle, rectangle.y + delYHandle,
				rectangle.width, rectangle.height);
	}
}
