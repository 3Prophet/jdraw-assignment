package jdraw.ldmitry.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;

public class SWHandle extends AbstractHandle {

	public SWHandle(Figure owner) {
		super(owner);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
	}
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle r = getOwner().getBounds();
		
		getOwner().setBounds(new Point(x, r.y), 
				new Point(r.x + r.width, y));
	}
	@Override
	public  void figureChanged(FigureEvent e) {
	}

	@Override
	public Point getLocation() {
		Rectangle r = getOwner().getBounds();
		int x = r.x;
		int y = r.y + r.height;
		return new Point(x, y);
	}

}
