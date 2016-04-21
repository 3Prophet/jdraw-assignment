package jdraw.ldmitry.figuretools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.ldmitry.figures.Line;

public class LineTool implements DrawTool {
	
	/** 
	 * The image resource path. 
	 */
	private static final String IMAGES = "/images/";
	/**
	 * The context we use for drawing.
	 */
	private DrawContext context;

	/**
	 * The context's view. This variable can be used as a shortcut, i.e.
	 * instead of calling context.getView().
	 */
	private DrawView view;

	/**
	 * Temporary variable. During line creation (during a
	 * mouse down - mouse drag - mouse up cycle) this variable refers
	 * to the new line that is inserted.
	 */
	private Line newLine = null;

	/**
	 * Temporary variable.
	 * During line creation this variable refers to the point the
	 * mouse was first pressed.
	 */
	private Point anchor = null;
	
	/**
	 * Create a new line tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public LineTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}

	/**
	 * Activates the Line Mode. There will be a
	 * specific menu added to the menu bar that provides settings for
	 * Line attributes
	 */
	@Override
	public void activate() {
		this.context.showStatusText("Line Mode");
	}
	
	/**
	 * Deactivates the current mode by resetting the cursor
	 * and clearing the status bar.
	 * @see jdraw.framework.DrawTool#deactivate()
	 */
	@Override
	public void deactivate() {
		this.context.showStatusText("");	
	}
	
	/**
	 * Initializes a new Line object by setting an anchor
	 * point where the mouse was pressed. A new Line is then
	 * added to the model.
	 * @param x x-coordinate of mouse
	 * @param y y-coordinate of mouse
	 * @param e event containing additional information about which keys were pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
	 */
	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newLine != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newLine = new Line((float) x, (float) y, (float) x, (float) y);
		view.getModel().addFigure(newLine);
		
	}
	
	/**
	 * During a mouse drag, the Line will be resized according to the mouse
	 * position. The status bar shows the current size.
	 * 
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseDrag(int, int, MouseEvent)
	 */
	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		newLine.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newLine.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
		
	}
	/**
	 * When the user releases the mouse, the Line object is updated
	 * according to the color settings.
	 * 
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
	 */
	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		newLine = null;
		anchor = null;
		this.context.showStatusText("Line Mode");	
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + "line.png"));
	}

	@Override
	public String getName() {
		return "Line";
	}

}
