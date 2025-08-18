package com.nightbird.universalnumerology.NumerologyGrid;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.nightbird.universalnumerology.R;

public class BaseGrid extends View {

	private final float SCREEN_DENSITY = getContext().getResources().getDisplayMetrics().density;
	private final float dpiRounding = getContext().getResources().getDimension(R.dimen.edgerounding);
	private final float dpiMargin = getContext().getResources().getDimension(R.dimen.margin);
	private final int EDGE_ROUNDING = (int) (dpiRounding * SCREEN_DENSITY + .5f);
	private final int MARGIN = (int) (dpiMargin * SCREEN_DENSITY + .5f);

	private final Paint gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
	private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
	private final GridCell gridCells = new GridCell();

	private int[] matrix;

	public BaseGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BaseGrid(Context context) {
		super(context);
	}

	protected static String buildString(int number, int count) {

		if (count == 0) {
			return null;
		}

		StringBuilder string = new StringBuilder();

		if (count > 10) {
			for (int i = 0; i < 7; i++) {
				string.append(number);
			}

			string.append("...");
		} else {
			for (int i = 0; i < count; i++) {
				string.append(number);
			}
		}

		return string.toString();
	}

	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		drawGridFrame(canvas);
		drawGridCells(canvas);
		drawStatusText(canvas);
	}

	private void drawGridFrame(Canvas canvas) {

		gridPaint.setColor(Color.LTGRAY);
		gridPaint.setStrokeWidth(5 * SCREEN_DENSITY);
		gridPaint.setStyle(Paint.Style.FILL_AND_STROKE);

		final int margin = MARGIN;
		final int width = getWidth();
		final int height = getHeight();
		final RectF rect = new RectF();
		final int gridWidth = (width > height) ? height / 3 - (margin * 2) : width / 3 - (margin * 2);
		int left = (width > height) ? width / 2 - (3 * gridWidth) / 2 : 3 * margin;
		int top = height / 2 - (3 * gridWidth) / 2 - margin;

		rect.set(left - 2 * margin, top - margin, left + 3 * gridWidth + 2 * margin, top + 3 * gridWidth + 3 * margin);
		gridPaint.setShader(new LinearGradient(rect.left, rect.bottom, rect.right, rect.top, Color.LTGRAY, Color.WHITE, Shader.TileMode.CLAMP));
		canvas.drawRoundRect(rect, EDGE_ROUNDING, EDGE_ROUNDING, gridPaint);
	}

	private void drawGridCells(Canvas canvas) {

		gridPaint.setColor(Color.LTGRAY);
		gridPaint.setStrokeWidth(3 * SCREEN_DENSITY);
		gridPaint.setStyle(Paint.Style.FILL_AND_STROKE);

		final int margin = MARGIN;
		final RectF rect = new RectF();
		final int width = getWidth();
		final int height = getHeight();
		final int gridWidth = (width > height) ? height / 3 - (margin * 2) : width / 3 - (margin * 2);
		int left = (width > height) ? width / 2 - (3 * gridWidth) / 2 : 3 * margin;
		left = left - margin;
		int top = height / 2 - (3 * gridWidth) / 2 - margin;

		rect.set(left, top, left + gridWidth, top + gridWidth);
		gridPaint.setShader(new LinearGradient(rect.left, rect.bottom, rect.right, rect.top, Color.rgb(100, 140, 220), Color.rgb(80, 120, 210), Shader.TileMode.CLAMP));
		canvas.drawRoundRect(rect, EDGE_ROUNDING, EDGE_ROUNDING, gridPaint);
		gridCells.setCell(0, rect);

		for (int i = 1; i < 9; i++) {

			if (i % 3 == 0) {
				top = top + gridWidth + margin;
				left = (width > height) ? width / 2 - (3 * gridWidth) / 2 : 3 * margin;
				left = left - margin;
				rect.offsetTo(left, top);
			} else {
				left = left + gridWidth + margin;
				rect.offsetTo(left, top);
			}

			canvas.drawRoundRect(rect, EDGE_ROUNDING, EDGE_ROUNDING, gridPaint);
			gridCells.setCell(i, rect);
		}
	}


	protected void drawStatusText(Canvas canvas) {

		RectF bounds;
		Rect textRect = new Rect();

		textPaint.setTypeface(Typeface.DEFAULT);
		textPaint.setColor(Color.argb(150, 255, 255, 255));
		textPaint.setTextSize(15 * SCREEN_DENSITY);

		for (int i = 0; i < 9; i++) {
			bounds = gridCells.getCell(i, GridCell.CellType.StatusCell);

			String nominalString = buildStatusString(0);
			final int strLength = nominalString.length();


			float wt = textPaint.measureText(nominalString);

			while (wt < bounds.width() - bounds.width() / 4 - (int) (3 * SCREEN_DENSITY + .5f)) {
				textPaint.setTextSize(textPaint.getTextSize() + 10f);
				wt = textPaint.measureText(nominalString);
			}

			while (wt > bounds.width() - bounds.width() / 4 - (int) (3 * SCREEN_DENSITY + .5f)) {
				textPaint.setTextSize(textPaint.getTextSize() - 5f);
				wt = textPaint.measureText(nominalString);
			}


			textPaint.getTextBounds(nominalString, 0, strLength, textRect);
			String string = buildStatusString(i);
			canvas.drawText(string, bounds.left, bounds.top + Math.round(bounds.height() / 1.2), textPaint);
		}
	}


	private String buildStatusString(int number) {

		String[] numberNames = getResources().getStringArray(R.array.number_list);

		return numberNames[number] + " " + matrix[number + 1];

	}

	public interface OnTransformationRequestListener {
		public void onTransformationRequest(int numberIndex, int gridIndex);
	}

	public void setupMatrix(int[] matrix) {
		this.matrix = matrix.clone();
	}

	protected int[] getNumberMatrix() {
		return matrix;
	}

	protected GridCell getGridCells() {
		return gridCells;
	}
}

