package jdraw.ldmitry.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.ldmitry.figures.AbstractFigure;

public class NHandle extends AbstractHandleState {

	public NHandle(Figure owner) {
		super(owner);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
	}
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle r = getOwner().getBounds();
		
		getOwner().setBounds(new Point(x - (r.width/2), y), 
				new Point(r.x + r.width, r.y + r.height) );
		
		if (y > r.y + r.height) {
			 ((AbstractFigure) getOwner()).swapVertical();
		} 
	}
	@Override
	public  void figureChanged(FigureEvent e) {
	}

	@Override
	public Point getLocation() {
		Rectangle r = getOwner().getBounds();
		int x = r.x + (r.width/2);
		int y = r.y;
		return new Point(x, y);
	}
	
}
