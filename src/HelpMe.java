
public class HelpMe {

	public static void main(String[] args) {
		Matrix A1 = new Matrix("C:\\Users\\Kmp\\Documents\\GitHub\\Test_Cholesky\\Matrizen\\A4");
		System.out.println(A1.toString());
		
		Matrix b1 = new Matrix("C:\\Users\\Kmp\\Documents\\GitHub\\Test_Cholesky\\Matrizen\\b4");
		System.out.println(b1.toString());
		
		Cholesky c = new Cholesky();
		Matrix x = c.loese(A1, b1);
		System.out.println("x:\n" + x.toString());

	}

}