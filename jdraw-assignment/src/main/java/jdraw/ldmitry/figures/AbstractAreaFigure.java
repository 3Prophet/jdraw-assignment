package jdraw.ldmitry.figures;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;

public abstract class AbstractAreaFigure extends AbstractFigure {
	protected RectangularShape shape;
	
	@Override
	public boolean contains(int x, int y) {
		return shape.contains((double) x, (double) y);
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		shape.setFrameFromDiagonal(origin, corner);
		notifyFigureListeners();
	}
	
	@Override
	public Rectangle getBounds() {
		return shape.getBounds();
	}
}
