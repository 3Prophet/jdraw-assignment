package jdraw.ldmitry.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.ldmitry.figures.AbstractFigure;


public class NWHandle extends AbstractHandleState {

	public NWHandle(Figure owner) {
		super(owner);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		
		Rectangle r = getOwner().getBounds();
		
		getOwner().setBounds(new Point(x, y), 
				new Point(r.x + r.width, r.y + r.height));
		
		if (x > r.x + r.width) {
			 ((AbstractFigure) getOwner()).swapHorizontal();
		} else if (y > r.y + r.height) {
			 ((AbstractFigure) getOwner()).swapVertical();
		}
		
		
	}
	@Override
	public  void figureChanged(FigureEvent e) {
	}

	@Override
	public Point getLocation() {
		Rectangle r = getOwner().getBounds();
		int x = r.x;
		int y = r.y;
		return new Point(x, y);
	}
}
