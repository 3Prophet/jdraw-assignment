package jdraw.ldmitry.handles;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public abstract class Handle implements FigureListener, FigureHandle {
	
	/**
	 * Instance of figure that owns a particular handle.
	 */
	protected Figure owner;
	
	/**
	 * Location of the central point of the current handle.
	 */
	protected Point location;
	
	
	protected Point anchor;
	
	/**
	 * Handle is implemented using java.awt.Rectnagle class
	 */
	
	protected java.awt.Rectangle rectangle;
	
	/**
	 * Constructor method of the Handle.
	 * @param owner specifies owner of the handle
	 * @param location specifies location of the handle
	 */
	public Handle(Figure owner, Point location) {
		this.owner = owner;
		this.location = location;
		double x = location.getX();
		double y = location.getY();
		// handle size in pixels
		int w = 5;
		int h = 5;
		// location of the origin of the handle's rectangle
		int newX = (int) x - (w/2);
		int newY = (int) y - (h/2);
		// handle delegate
		rectangle = new java.awt.Rectangle(newX, newY, w, h);
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
	 * Returns location of the particular handler.
	 * @return Point instance, which designates handler's location
	 */
	@Override
	public Point getLocation() {
		return location;
	}
	
	/**
	 * Makes handler to draw itself.
	 * @param g where the handler will draw itself
	 */
	@Override
	public void draw(Graphics g) {
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
		return rectangle.contains(x, y);
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