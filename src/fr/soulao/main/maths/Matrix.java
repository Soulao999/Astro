package fr.soulao.main.maths;

public class Matrix {

	public static float[][] passMatrix(float r, float t, float l) {
		float cosr = (float) Math.cos(Math.toRadians(r));
		float sinr = (float) Math.sin(Math.toRadians(r));
		float cost = (float) Math.cos(Math.toRadians(t));
		float sint = (float) Math.sin(Math.toRadians(t));
		float cosl = (float) Math.cos(Math.toRadians(l));
		float sinl = (float) Math.sin(Math.toRadians(l));
		float[][] m = { { cosr * cosl - sinr * cost * sinl, -cosr * sinl - sinr * cost * cosl, sinr * sint },
				{ sinr * cosl + cosr * cost * sinl, -sinr * sinl + cosr * cost * cosl, -cosr * sint },
				{ sint * sinl, sint * cosl, cost } };
		return m;
	}

	public static float[] produitMatriceVecteur(float[][] M, float[] C) {
		float[] R = new float[C.length];
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				R[i] = R[i] + M[i][j] * C[j];
			}
		}
		return R;
	}

	public static float[][] multiplier(float[][] MA, float[][] MB) throws Exception {

		float[][] MC;

		int l, c;

		if (MA == null || MB == null) {
			throw new Exception("L'une des deux matrices est à null");
		}

		if (MA[0].length != MB.length) {
			throw new Exception(
					"La multiplication de deux matrices n'est possible que si le nombre de colonne du premier est égal au nombre de ligne du second!!");
		}

		if (MA.length * MA[0].length < MB.length * MB[0].length) {
			l = MB.length;
			c = MB[0].length;
		} else {
			l = MA.length;
			c = MA[0].length;
		}

		MC = new float[l][c];

		l = 0;
		for (int i = 0; i < MA.length; i++) { /// Ligne de MA
			c = 0;
			for (int n = 0; n < MB[0].length; n++) { /// colonne de MB

				int calcul = 0;
				for (int m = 0; m < MB.length; m++) { /// colone de MA et ligne
														/// de MB

					System.out.printf("%4d    * %4d", MA[i][m], MB[m][n]);

					calcul += MA[i][m] * MB[m][n];
					if (m == 0)
						System.out.printf("    + ");
				}

				System.out.printf(" = %4d   ", calcul);
				MC[l][c] = calcul;
				c++;
			}
			System.out.printf("n");
			l++;
		}

		return MC;
	}
}
