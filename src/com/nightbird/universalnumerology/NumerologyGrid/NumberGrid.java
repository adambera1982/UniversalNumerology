package com.nightbird.universalnumerology.NumerologyGrid;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;

public class NumberGrid extends BaseGrid {

	private final float SCREEN_DENSITY = getContext().getResources().getDisplayMetrics().density;

	private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

	public NumberGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NumberGrid(Context context) {
		super(context);
	}

	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		drawNumberText(canvas);
	}

	private void drawNumberText(Canvas canvas) {

		textPaint.setTypeface(Typeface.DEFAULT_BOLD);
		textPaint.setColor(Color.argb(225, 235, 235, 235));

		RectF bounds;
		Rect textRect = new Rect();

		for (int i = 0; i < 9; i++) {

			textPaint.setTextSize(20 * SCREEN_DENSITY);

			final String string = buildString(i + 1, getNumberMatrix()[i + 1]);

			if (string == null) {
				continue;
			}

			final int strLength = string.length();

			// Ha a szöveghossz kisebb mint 3, akkor az 1-esr?l mintavételezz 3-at, mert az a legnagyobb
			// ne az adott szövegr?l, mert nagyon látszik hogy különböz? lenne a méret. Legyen egyforma
			if (strLength < 3) {

				bounds = getGridCells().getCell(i, GridCell.CellType.NumberCell);

				final String nominalString = buildString(1 /*i+ 1*/, 3);

				float wt = textPaint.measureText(nominalString);

				while (wt < bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() + 20f);
					wt = textPaint.measureText(nominalString);
				}

				while (wt > bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() - .5f);
					wt = textPaint.measureText(nominalString);
				}

				textPaint.getTextBounds(nominalString, 0, strLength, textRect);
				wt = textPaint.measureText(string);

				final float posY = bounds.top + textRect.height() / 2 + bounds.height() / 2;
				final float posX = bounds.left + bounds.width() / 2 - wt / 2;

				canvas.drawText(string, posX, posY, textPaint);

			} else if (strLength <= 5 && strLength >= 3) {

				bounds = getGridCells().getCell(i, GridCell.CellType.NumberCell);


				float wt = textPaint.measureText(string);

				while (wt < bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() + 20f);
					wt = textPaint.measureText(string);
				}

				while (wt > bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() - .5f);
					wt = textPaint.measureText(string);
				}

				textPaint.getTextBounds(string, 0, strLength, textRect);
				wt = textPaint.measureText(string);

				final float posY = bounds.top + textRect.height() / 2 + bounds.height() / 2;
				final float posX = bounds.left - wt / 2 + bounds.width() / 2;

				canvas.drawText(string, posX, posY, textPaint);

			} else if (strLength > 5 /* && strLength <= 10*/) {

				bounds = getGridCells().getCell(i, GridCell.CellType.NumberCell);

				String line1 = string.substring(0, 5);
				String line2 = string.substring(5, strLength);

				float wt = textPaint.measureText(line1);

				while (wt < bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() + 20f);
					wt = textPaint.measureText(line1);
				}

				while (wt > bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() - .5f);
					wt = textPaint.measureText(line1);
				}

				textPaint.getTextBounds(line1, 0, line1.length(), textRect);
				wt = textPaint.measureText(line1);

				final float posY1 = bounds.top + textRect.height() + bounds.height() / 4 - (int) (5 * SCREEN_DENSITY + .5f);
				final float posX1 = bounds.left - wt / 2 + bounds.width() / 2;
				canvas.drawText(line1, posX1, posY1, textPaint);

				textPaint.getTextBounds(line2, 0, line2.length(), textRect);
				wt = textPaint.measureText(line2);

				final float posY2 = bounds.top + textRect.height() + bounds.height() / 2 + textRect.height() / 4;
				final float posX2 = bounds.left - wt / 2 + bounds.width() / 2;
				canvas.drawText(line2, posX2, posY2, textPaint);
			}

		}
	}


	public void setupMatrix(int[] matrix) {
		super.setupMatrix(matrix);
	}
}
