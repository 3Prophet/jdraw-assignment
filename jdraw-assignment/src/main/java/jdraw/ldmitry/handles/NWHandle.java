package jdraw.ldmitry.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;

public class NWHandle extends Handle {

	public NWHandle(Figure owner, Point location) {
		super(owner, location);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		java.awt.Rectangle bounds = owner.getBounds();
		// old location of owner
		int oldX = bounds.x;
		int oldY = bounds.y;
		// new location of owner
		int newX = oldX - (anchor.x - x);
		int newY = oldY - (anchor.y - y);
		// new size of owner
		int newWidth = bounds.width + (anchor.x - x);
		int newHeight = bounds.height + (anchor.y - y);
		owner.setBounds(new Point(newX, newY), 
				new Point(newX + newWidth, newY + newHeight));
	}
	@Override
	public  void figureChanged(FigureEvent e) {
		
		java.awt.Rectangle bounds = e.getFigure().getBounds();
		int newXOwner = bounds.x;
		int newYOwner = bounds.y;
		int newWidth = bounds.width;
		int newHeight = bounds.height;
		int newXHandle = newXOwner;
		int newYHandle = newYOwner;
		int delXHandle = newXHandle - location.x;
		int delYHandle = newYHandle - location.y;
		location = new Point(newXHandle, newYHandle);
		rectangle = new java.awt.Rectangle(rectangle.x + delXHandle, rectangle.y + delYHandle,
											rectangle.width, rectangle.height);
	}

}
