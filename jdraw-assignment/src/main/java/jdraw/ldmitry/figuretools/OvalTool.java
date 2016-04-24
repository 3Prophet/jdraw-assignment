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
import jdraw.ldmitry.figures.Oval;

public class OvalTool extends AbstractFigureTool {

	/**
	 * Create a new oval tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public OvalTool(DrawContext context) {
		super(context);
	}

	@Override
	public Figure getNewFigure() {
		return new Oval((float) getAnchor().getX(), (float) getAnchor().getY(), 0, 0);
	}
	
	/**
	 * Factory method for the OvalTool.
	 * @param context context that registers an OvalTool
	 * @return
	 */
	public static DrawTool getDrawTool(DrawContext context) {
		OvalTool otool = new OvalTool(context);
		otool.setName("Oval");
		return otool;
	}	
}
