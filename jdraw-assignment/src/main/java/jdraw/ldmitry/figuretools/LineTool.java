package jdraw.ldmitry.figuretools;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;
import jdraw.ldmitry.figures.Oval;

public class LineTool extends AbstractFigureTool {
	
	
	public LineTool(DrawContext context) {
		super(context);
	}

	@Override
	public Figure getNewFigure() {
		return new Oval((float) getAnchor().getX(), (float) getAnchor().getY(), 
				(float) getAnchor().getX(), (float) getAnchor().getY());
	}
	/**
	 * Factory method for the LineTool.
	 * @param context context that registers an LineTool
	 * @return
	 */
	public static DrawTool getDrawTool(DrawContext context) {
		LineTool ltool = new LineTool(context);
		ltool.setName("Line");
		return ltool;
	}	
}
