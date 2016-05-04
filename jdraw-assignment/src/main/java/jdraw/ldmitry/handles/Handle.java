package jdraw.ldmitry.handles;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

/**
 *  Implements a context for a Handle.
 * @author Dmitry Logvinovich
 *
 */
public class Handle implements FigureHandle {
	
	/**
	 * Current state of a handle.
	 */
	private AbstractHandleState state;
	
	public Handle(AbstractHandleState state) {
		this.state = state;
	}
	
	public AbstractHandleState getState() {
		return state;
	}
	
	public void setState(AbstractHandleState state) {
		this.state = state;
	}

	@Override
	public Figure getOwner() {
		return state.getOwner();
	}

	@Override
	public Point getLocation() {
		return state.getLocation();
	}

	@Override
	public void draw(Graphics g) {
		state.draw(g);
	}

	@Override
	public Cursor getCursor() {
		return state.getCursor();
	}

	@Override
	public boolean contains(int x, int y) {
		return state.contains(x, y);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		state.startInteraction(x, y, e, v);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		state.dragInteraction(x, y, e, v);
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		state.stopInteraction(x, y, e, v);
	}
}
