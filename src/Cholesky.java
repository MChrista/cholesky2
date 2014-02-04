
public class Cholesky {
	
	public Cholesky() {
		
	}
	public Matrix loese(Matrix A, Matrix b) {
		/* A * x = b	Zu lösende Gleichung, x gesucht
		 * A = C * Ct	A zerlegen mit Cholesky in C und C-transponiert
		 * Ct * y = b	Kann man einfach lösen nach y
		 * C * x = y	Kann man nun nach x lösen
		 */
		
		if (A.hoehe() != A.breite()) {
			System.out.println("Cholesky-Zerlegung nicht moeglich, da die Matrix A nicht symmetrisch ist.");
			return null;
		}
		if (b.breite() != 1) {
			System.out.println("Vektor b muss die Breite 1 haben.");
			return null;
		}
		if (A.hoehe() != b.hoehe()) {
			System.out.println("Gleichungssystem nicht loesbar. Hoehe der Matrix A und des Vektors b muss identisch sein.");
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
				C.setElement(z, s, 0.0);
			}
		}
		
		//Unteres Dreieck und Hauptdiagonale berechnen
		for (int z=0; z<dim; z++) {
			for (int s=0; s<dim; s++) {
				wert = A.getElement(z, s);
				for (int i=1; i<s-1; i++) {
					wert = wert - A.getElement(z, i) * A.getElement(s, i);
				}
				if (z>s) {
					wert = wert / A.getElement(s, s);
					C.setElement(z, s, wert);
				}
				else if (wert > 0) {
					wert = Math.sqrt(wert);
					C.setElement(z, z, wert);
				}
				else {
					System.out.println("Cholesky-Verfahren nicht moeglich, da die Matrix A nicht symmetrisch positiv definit ist.");
					return null;
				}
			}
		}
		Ct = C.transponierte();
		
		// Ct * y = b	Vektor y berechnen		Bsp dim=3
		for (int z=dim-1; z>=0; z--){		//z: 2 bis 0
			wert = b.getElement(z, 0);
			for (int s=dim-1; s>z; s--) {	//s: Garnicht, 2 bis 2, 2 bis 1
				wert = wert - Ct.getElement(z, s) * y.getElement(s, 0);
			}
			wert = wert / Ct.getElement(z, z);
			y.setElement(z, 0, wert);
		}
		
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
	

}