
public class HelpMe {

	public static void main(String[] args) {
		/*Matrix A1 = new Matrix("Testmatrizen/A2.txt");
		System.out.println(A1.toString());
		
		Matrix b1 = new Matrix("Testmatrizen/b2.txt");
		System.out.println(b1.toString());
		
		Cholesky c = new Cholesky();
		Matrix x = c.loese(A1, b1);
		System.out.println("x:\n" + x.toString());*/
		Matrixgenerator mg=new Matrixgenerator(1000);
		System.out.println("Matrix");
		mg.toFile("Testmatrizen/fuenfhundert.txt");
		System.out.println("ferig");
	}

}