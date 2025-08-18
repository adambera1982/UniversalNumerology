package com.nightbird.universalnumerology.Calculation.Support;

public class SupplementalCalculator {

	public static float calcsimilarity(int[] matrix1, int[] matrix2) {

		int[] addition = new int[10];
		int[] subtraction = new int[10];
		float[] similarity = new float[10];
		float sum = 0f;

		for (int i = 0; i < 10; i++) {
			addition[i] = matrix1[i] + matrix2[i];
		}

		for (int i = 0; i < 10; i++) {
			subtraction[i] = Math.abs(matrix1[i] - matrix2[i]);
		}

		for (int i = 0; i < 10; i++) {
			if (addition[i] == 0) {
				similarity[i] = 1;
			} else {
				similarity[i] = 1 - ((float) subtraction[i] / (float) addition[i]);
			}
		}

		for (int i = 0; i < 10; i++) {
			if (i != 0) {
				sum = sum + similarity[i];
			}
		}

		return 100 * sum / 9;
	}

	public static float calcApproachability(int[] matrix1, int[] matrix2) {

		int s147 = matrix1[1] + matrix1[4] + matrix1[7] + matrix2[1] + matrix2[4] + matrix2[7];
		int d147 = matrix1[1] + matrix1[4] + matrix1[7] - matrix2[1] - matrix2[4] - matrix2[7];

		float k147 = s147 != 0 ? 1 - ((float) Math.abs(d147) / (float) Math.abs(s147)) : 1;

		int s258 = matrix1[2] + matrix1[5] + matrix1[8] + matrix1[2] + matrix1[5] + matrix1[8];
		int d258 = matrix1[2] + matrix1[5] + matrix1[8] - matrix2[2] - matrix2[5] - matrix2[8];

		float k258 = s258 != 0 ? 1 - ((float) Math.abs(d258) / (float) Math.abs(s258)) : 1;

		int s369 = matrix1[3] + matrix1[6] + matrix1[9] + matrix2[3] + matrix2[6] + matrix2[9];
		int d369 = matrix1[3] + matrix1[6] + matrix1[9] - matrix2[3] - matrix2[6] - matrix2[9];

		float k369 = s369 != 0 ? 1 - ((float) Math.abs(d369) / (float) Math.abs(s369)) : 1;

		//

		int s123 = matrix1[1] + matrix1[2] + matrix1[3] + matrix2[1] + matrix2[2] + matrix2[3];
		int d123 = matrix1[1] + matrix1[2] + matrix1[3] - matrix2[1] - matrix2[2] - matrix2[3];

		float k123 = s123 != 0 ? 1 - ((float) Math.abs(d123) / (float) Math.abs(s123)) : 1;

		int s456 = matrix1[4] + matrix1[5] + matrix1[6] + matrix2[4] + matrix2[5] + matrix2[6];
		int d456 = matrix1[4] + matrix1[5] + matrix1[6] - matrix2[4] - matrix2[5] - matrix2[6];

		float k456 = s456 != 0 ? 1 - ((float) Math.abs(d456) / (float) Math.abs(s456)) : 1;

		int s789 = matrix1[7] + matrix1[8] + matrix1[9] + matrix2[7] + matrix2[8] + matrix2[9];
		int d789 = matrix1[7] + matrix1[8] + matrix1[9] - matrix2[7] - matrix2[8] - matrix2[9];

		float k789 = s789 != 0 ? 1 - ((float) Math.abs(d789) / (float) Math.abs(s789)) : 1;

		//

		int s159 = matrix1[1] + matrix1[5] + matrix1[9] + matrix2[1] + matrix2[5] + matrix2[9];
		int d159 = matrix1[1] + matrix1[5] + matrix1[9] - matrix2[1] - matrix2[5] - matrix2[9];

		float k159 = s159 != 0 ? 1 - ((float) Math.abs(d159) / (float) Math.abs(s159)) : 1;

		int s357 = matrix1[3] + matrix1[5] + matrix1[7] + matrix2[3] + matrix2[5] + matrix2[7];
		int d357 = matrix1[3] + matrix1[5] + matrix1[7] - matrix2[3] - matrix2[5] - matrix2[7];

		float k357 = s357 != 0 ? 1 - ((float) Math.abs(d357) / (float) Math.abs(s357)) : 1;

		int sum1 = 0;
		int sum2 = 0;

		for (int i = 0; i < 10; i++) {
			if (i != 0) {
				sum1 = sum1 + matrix1[i];
				sum2 = sum2 + matrix2[i];
			}
		}

		int sumTotal = sum1 + sum2;

		int subTotal = sum1 - sum2;

		float rawApproach = 1 - subTotal / (float) (sumTotal - 16);

		return 100 * (k147 + k258 + k369 + k123 + k456 + k789 + k159 + k357 + rawApproach) / 9;
	}

	public static float calcSpiritualStability(int stability1, int stability2) {
		int multi = stability1 * stability2;
		return ((float) multi / (float) 365);
	}

	public static float calcPhysicalStability(int stability1, int stability2) {
		int multi = stability1 * stability2;
		return ((float) multi / (float) 365);
	}
}
