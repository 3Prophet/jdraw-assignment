package jdraw.ldmitry.figuretools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

/**
 * This Class implements common behavior of different Figure Tools.
 * 
 * Subclasses have to provide factory method to instantiate concrete FigureTool.
 * In this factory method one has to call setName(name) method on a 
 * newly created FigureTool instance. When giving a name to a tool beware
 * of the following convention:
 * -Name should start from the capital letter (say "Rectangle");
 * -Icon has to be provided in ".png" format;
 * -Icon filename is obtained by setting name to lower case (say "Rectangle"->"rectangle.png")
 *
 * @see jdraw.framework.Figure
 *
 * @author  Dmitry Logvinovich
 */
public abstract class AbstractFigureTool implements DrawTool {
	/** 
	 * the image resource path. 
	 */
	private static final String IMAGES = "/images/";
	
	/**
	 * Name of the figure the tool will be creating.
	 */
	private String figName = null;

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
	 * Temporary variable. During figure creation (during a
	 * mouse down - mouse drag - mouse up cycle) this variable refers
	 * to the new figure that is inserted.
	 */
	private Figure newFig = null;

	/**
	 * Temporary variable.
	 * During figure creation this variable refers to the point the
	 * mouse was first pressed.
	 */
	private Point anchor = null;

	/**
	 * Create a new AbstractFigureTool for the given context.
	 * @param context a context to use this tool in.
	 */
	public AbstractFigureTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}
	
	/**
	 * Deactivates the current mode by resetting the cursor
	 * and clearing the status bar.
	 * @see jdraw.framework.DrawTool#deactivate()
	 */
	public void deactivate() {
		this.context.showStatusText("");
	}
	/**
	 * Activates the Rectangle Mode. There will be a
	 * specific menu added to the menu bar that provides settings for
	 * Rectangle attributes
	 */
	public void activate() {
		this.context.showStatusText(getName() + " Mode");
	}

	/**
	 * Initializes a new Figure object by setting an anchor
	 * point where the mouse was pressed. A new Rectangle is then
	 * added to the model.
	 * @param x x-coordinate of mouse
	 * @param y y-coordinate of mouse
	 * @param e event containing additional information about which keys were pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
	 */
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newFig != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newFig = getNewFigure();
		view.getModel().addFigure(newFig);
	}

	/**
	 * During a mouse drag, the RFigure will be resized according to the mouse
	 * position. The status bar shows the current size.
	 * 
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseDrag(int, int, MouseEvent)
	 */
	public void mouseDrag(int x, int y, MouseEvent e) {
		newFig.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newFig.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	/**
	 * When the user releases the mouse, the Figure object is updated
	 * according to the color and fill status settings.
	 * 
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
	 */
	public void mouseUp(int x, int y, MouseEvent e) {
		newFig = null;
		anchor = null;
		this.context.showStatusText(getName() +" Mode");
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}
	
	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + figName.toLowerCase() +".png"));
	}
	
	/**
	 * Returns figure name that the corresponding FigureTool creates.
	 * @return figure name
	 */
	@Override
	public String getName() {
		return figName;
	}
	
	/**
	 * Returns anchor, which can be accessed by subclasses.
	 * @return anchor
	 */
	protected Point getAnchor() {
		return anchor;
	}
	
	/**
	 * Sets name of the Figure the FigureTool will be creating
	 * @param figName
	 */
	public void setName(String figName) {
		this.figName = figName;
	}

	/**
	 * Returns new Figure instance when the mouse down.
	 * @return New Figure instance
	 */
	public abstract Figure getNewFigure();
	
}
