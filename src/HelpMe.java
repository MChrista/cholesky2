
public class HelpMe {

	public static void main(String[] args) {
		Matrix A1 = new Matrix("Testmatrizen/A1.txt");
		Matrix b1 = new Matrix("Testmatrizen/b1.txt");
		System.out.println(A1.toString());
		System.out.println(b1.toString());
		Matrix b2 = new Matrix("Testmatrizen/b2.txt");
		System.out.println(b2.toString());
		
		/*Matrix m = new Matrix(3, 3);
		
		Cholesky spannend = new Cholesky();
		spannend.loese(A1, b1);*/	
	}

}