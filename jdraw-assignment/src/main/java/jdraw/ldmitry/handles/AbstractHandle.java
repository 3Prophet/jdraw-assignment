package jdraw.ldmitry.handles;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public abstract class AbstractHandle implements FigureListener, FigureHandle {
	/**
	 * Handle size in pixels.
	 */
	protected static final int HANDLE_SIZE = 5;
	
	/**
	 * Instance of figure (can't be changed) that owns a particular handle.
	 */
	private final Figure owner;
	
	protected Point anchor;
	
	/**
	 * Constructor method of the Handle.
	 * @param owner specifies owner of the handle
	 * @param location specifies location of the handle
	 */
	public AbstractHandle(Figure owner) {
		this.owner = owner;
	}
	
	/**
	 * Returns owner (Figure instance) of the particular handler.
	 * @return owner of the handler
	 */
	@Override
	public Figure getOwner() {
		//TODO implement returning read-only copy
		return owner;
	}
	
	/**
	 * Returns location of the particular handle.
	 */
	public abstract Point getLocation();
	
	/**
	 * Returns Handle's rectangle.
	 */
	protected Rectangle getRectangle() {
		Point origin = getLocation();
		int x = origin.x - (HANDLE_SIZE/2);
		int y = origin.y - (HANDLE_SIZE/2);
		Rectangle rectangle = new Rectangle(x, y, 
													HANDLE_SIZE, HANDLE_SIZE);
		return rectangle;
	}
	
	/**
	 * Drawing the handle.
	 * @param g where the handler will draw itself
	 */
	@Override
	public void draw(Graphics g) {
		
		Rectangle rectangle = getRectangle();
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, 
							 rectangle.width, rectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(rectangle.x, rectangle.y, 
							 rectangle.width, rectangle.height);	
	}
	
	/**
	 * Returns Cursor of the current handler. This has to be specified in the subclass.
	 * @return Handler specific cursor
	 */
	@Override
	public abstract Cursor getCursor();

	@Override
	public boolean contains(int x, int y) {
		return getRectangle().contains(x, y);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		if (anchor != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
	}

	@Override
	public abstract void dragInteraction(int x, int y, MouseEvent e, DrawView v);

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		anchor = null;		
	}
	
	@Override
	public  abstract void figureChanged(FigureEvent e);
}