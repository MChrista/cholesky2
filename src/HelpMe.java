
public class HelpMe {

	public static void main(String[] args) {
		Matrix A1 = new Matrix("C:\\Users\\Kmp\\Documents\\GitHub\\Test_Cholesky\\Matrizen\\A1.txt");
		System.out.println(A1.toString());
		
		Matrix b1 = new Matrix("C:\\Users\\Kmp\\Documents\\GitHub\\Test_Cholesky\\Matrizen\\b1.txt");
		System.out.println(b1.toString());
		
		Cholesky c = new Cholesky();
		Matrix x = c.loese(A1, b1);
		System.out.println("x:\n" + x.toString());
		
		double kobold = 1.2649110640673518;
		System.out.println(kobold);
	}

}