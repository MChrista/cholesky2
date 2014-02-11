import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Matrixgenerator {
	int x,y,z;
	int breite;
	Matrix m;
	
	public Matrixgenerator(int breite){
		m=new Matrix(breite, breite);
		this.breite=breite;
		this.generateMatrix();
	}
	public Matrix getMatrix(){
		return m;
	}
	
	
	private void generateMatrix(){
		for(int i=0;i<breite;i++){
			for(int j=0;j<breite;j++){
				if(i==j){
					m.setElement(i, j, 2.0);//m.setElement(zeile, spalte, wert);
					System.out.println(i+" "+j);
				}else if(j==(i+1) || j==(i-1)){
					m.setElement(i, j, -1.0);
				}else{
					m.setElement(i, j, 0.0);
				}
			}
		}
	}
	
	public void solvedVector(){
		
	}
 	public String toString() {
 		String temp ="";
 		
 		for ( int i =0; i < breite; i++ ) {
 			for ( int j = 0; j < breite; j++ ) {
 				temp += m.getElement(i,j);
 				temp += " ";
 			}
 			temp += "\r\n";
 		}
 		return temp;
 	}
 	
 	public void toFile(String dateiname) {
 		File out = new File(dateiname);
 		FileWriter fw;
		try {
			fw = new FileWriter(out);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
 		BufferedWriter buffwrite = new BufferedWriter(fw);
 		try {
			String temp = breite + " " + breite + "\r\n" + this.toString();
			buffwrite.append(temp);
			buffwrite.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
 		
 		
 	}

}
