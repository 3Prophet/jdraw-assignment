package jdraw.ldmitry.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;

public class EHandle extends AbstractHandle {

	public EHandle(Figure owner) {
		super(owner);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle r = getOwner().getBounds();
		
		getOwner().setBounds(new Point(r.x, r.y), 
				new Point(x, r.y + r.height));	
	}
	@Override
	public  void figureChanged(FigureEvent e) {
	}

	@Override
	public Point getLocation() {
		Rectangle r = getOwner().getBounds();
		int x = r.x + r.width;
		int y = r.y + (r.height/2);
		return new Point(x, y);
	}
}
