package jdraw.ldmitry.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public abstract class AbstractFigure implements Figure {

	private LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	
	@Override
	public abstract void draw(Graphics g);

	@Override
	public abstract void move(int dx, int dy);

	@Override
	public abstract boolean contains(int x, int y);

	@Override
	public abstract void setBounds(Point origin, Point corner);

	@Override
	public abstract Rectangle getBounds();

	@Override
	public abstract List<FigureHandle> getHandles();
	
	/**
	 * Implements horizontal swapping of figure handles.
	 */
	public abstract void swapHorizontal();
	
	/**
	 * Implements vertical swapping of figure handles.
	 */
	public abstract void swapVertical();

	@Override
	public void addFigureListener(FigureListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}

	@Override
	public abstract Figure clone();
	
	public void notifyFigureListeners() {
		for (FigureListener fl: listeners) {
			fl.figureChanged(new FigureEvent(this));
		}
	}
}
