
public class Cholesky {
	
	public Cholesky() {
		
	}
	public Matrix loese(Matrix A, Matrix b) {
		/* A * x = b	Zu lösende Gleichung, x gesucht
		 * A = Ct * C	A zerlegen mit Cholesky in C und C-transponiert
		 * Ct * y = b	Kann man einfach lösen nach y
		 * C * x = y	Kann man nun nach x lösen
		 */
		if ( !testSymmetrie(A) ) {
			throw new RuntimeException("Cholesky-Zerlegung nicht moeglich, da Matrix A nicht Symmetrisch ist");
		}
		if (A.hoehe() != A.breite()) {
			throw new RuntimeException("Cholesky-Zerlegung nicht moeglich, da die Matrix A nicht quadratisch ist.");
		}
		if (b.breite() != 1) {
			throw new RuntimeException("Vektor b muss die Breite 1 haben.");
		}
		if (A.hoehe() != b.hoehe()) {
			throw new RuntimeException("Gleichungssystem nicht loesbar. Hoehen der Matrix A und des Vektors b muessen identisch sein.");
		}
		double wert;
		int dim = A.hoehe();
		Matrix C = new Matrix(dim, dim);
		Matrix Ct = new Matrix(dim, dim);
		Matrix y = new Matrix(dim, 1);
		Matrix x = new Matrix (dim, 1);
		
		//Oberes Dreieck mit nullen fuellen
		for (int z=0; z<dim; z++) {
			for (int s=z+1; s<dim; s++) {
				Ct.setElement(z, s, 0.0);
			}
		}
		
		//Unteres Dreieck und Hauptdiagonale berechnen
		for (int i = 0; i < dim; i++)  {
            for (int j = 0; j <= i; j++) {
                double sum = 0.0;
                for (int k = 0; k < j; k++) {
                    sum += Ct.getElement(i, k) * Ct.getElement(j, k);
                }
                if (i == j) {
                	Ct.setElement(i, i, Math.sqrt(A.getElement(i, i) - sum));
                }
                else {
                	Ct.setElement(i, j, ( 1.0 / Ct.getElement(j, j) * (A.getElement(i, j) - sum)) );        
                }
            }
            if (Double.isNaN( Ct.getElement(i, i) ) ) {
                throw new RuntimeException("Cholesky-Verfahren nicht moeglich, da die Matrix A nicht positiv definit ist.");
            }
        }
		C = Ct.transponierte();
		System.out.println("Cholesky-Matrix C: \n" + C.toString());
		System.out.println("Transponierte C^t: \n" + Ct.toString());
		
		// Ct * y = b	Vektor y berechnen		Bsp dim=3
		for (int z=0; z<dim; z++){		//z: 0 bis 2
			wert = b.getElement(z, 0);
			for (int s=0; s<z; s++) {	//s: Garnicht, 0 bis 0, 0 bis 1
				wert = wert - Ct.getElement(z, s) * y.getElement(s, 0);
			}
			wert = wert / Ct.getElement(z, z);
			y.setElement(z, 0, wert);
		}
		System.out.println("Hilfsvektor y mit C^t * y = b \n" + y.toString());
		
		// C * x = y	Vektor x berechnen		Bsp dim=4
		for (int z=dim-1; z>=0; z--){		//z: 3 bis 0
			wert = y.getElement(z, 0);
			for (int s=dim-1; s>z; s--) {	//Garnicht, 3 bis 3, 3 bis 2, 3 bis 1
				wert = wert - C.getElement(z, s) * x.getElement(s, 0);
			}
			wert = wert / C.getElement(z, z);
			x.setElement(z, 0, wert);
		}
		return x;
	} 
	
	private boolean testSymmetrie(Matrix m) {
		boolean flag = true;
		for ( int i = 0; i < m.hoehe(); i++) {
			for ( int j = 0; j< m.breite(); j++ ) {
				if ( m.getElement(i, j) != m.getElement(j, i) ) {
					flag = false;
				}
			}
		}
		return flag;
	}

}