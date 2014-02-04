import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Matrix { 
	
	@SuppressWarnings("rawtypes")
	Vector mat;
	
	public Matrix(String dateiname) {
		File matDat = new File(dateiname);
		BufferedReader buffRead = null;
		
		try {
			FileReader fr = new FileReader(matDat);
			buffRead = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		}
		
		int[] size = null;
		try {
			size = readHeadder(buffRead.readLine());
		} catch (Exception e1) {
			System.out.println("Die Matrix Datei ist Leer");
		}	
		int i =0;
		
		mat = new Vector(size[1]);
		while ( i < size[1] ) {
			mat.add(i, new Vector(size[0]) );
			i++;
		}
		
		i = 0;
		while ( i < size[0] ) { // zeilen durchgehen
			double[] tempLine;
			String temp ="";
			try {
				if ( (temp = buffRead.readLine()) != null ) {
					tempLine = readLinen(temp);
					for ( int j = 0; j < size[1]; j++) { // spalten durchgehen
						addElement(i,j, tempLine[j]);
					}
				i++;
				}
				else {
					i++;
				}
			} catch (Exception e) {
				System.out.println("Keine Matrix angegeben");
			}			
		}
		
	}
	
 	public Matrix(int zeilen, int spalten) {
 		 
 		mat = new Vector(spalten);
 		
 		while ( spalten != 0 ) {
 			mat.add(spalten, new Vector(zeilen) );
 			spalten--;
 		}
 	}
 	
 	public void setElement(int zeile, int spalte, double wert) {
 		Vector temp = (Vector) mat.get(spalte);
 		temp.set(zeile, wert);
 		mat.set(spalte, temp);
 		
 	}
 	
 	private void addElement(int zeile, int spalte, double wert) {
 		Vector temp = (Vector) mat.get(spalte);
 		temp.add(zeile, wert);
 		mat.set(spalte, temp);
 		
 	}
 	
 	public double getElement(int zeile, int spalte) {
 		Vector temp = (Vector)  mat.get(spalte);
 		return (double) temp.get(zeile);
 	}
 	
 	public double[] zeile(int _zeile) {
 		int i =0;
 		double[] temp = new double[mat.size()];
 		
 		
 		while ( i < mat.size() ) {
 			temp[i] = getElement(_zeile, i);
 			i++;
 		}
 		return temp;
 	}
 	
 	public double[] spalte(int _spalte) {
 		int i =0;
 		double[] temp = new double[mat.size()-1];
 		
 		while ( i < mat.size() ) {
 			int j =0;
 			while ( j < ( (Vector)mat.get(i) ).size() ) {
 				temp[i] = (double)( (Vector)mat.get(j) ).get(i);
 				j++;
 			} 				
 			i++;
 		}
 		return temp;
 	}
 	
 	public int hoehe() {
 		return ((Vector)mat.get(0)).size();
 	}
 	
 	public int breite() {
 		return mat.size();
 	}
 	
 	public String toString() {
 		String temp ="";
 		
 		for ( int i =0; i < breite(); i++ ) {
 			for ( int j = 0; j < hoehe(); j++ ) {
 				temp += getElement(j,i);
 				temp += " ";
 			}
 			temp += "\n";
 		}
 		return temp;
 	}
 	
 	public void toFile(String dateiname) {
	 
 	}
 	
 	public Matrix transponierte() {
 		Vector tmp=(Vector)mat.get(0);
 		System.out.println("Zeilen: "+mat.size());
 		System.out.println("Spalten: "+tmp.size());
 		Matrix matr=new Matrix(tmp.size(),mat.size());//spalte, zeile, urspr: zeile, spalte
 		int spalten=0;
 		while(spalten != tmp.size()){
 	 		int zeilen=0;
 	 		while(zeilen != mat.size()){ //durchläuft alle zeilen der neuen matrix
 	 			matr.setElement(zeilen, spalten, getElement(spalten, zeilen));
 	 			zeilen++;
 	 		}
 	 		spalten++;
 		}
 		
 		return matr;
	 
 	}
 	
 	private int[] readHeadder(String line) {
 		String[] temp = line.split(" ");
 		int[] dimensions = new int[2];
 		
 		try {
 			dimensions[0] = Integer.parseInt(temp[0]);
 			dimensions[1] = Integer.parseInt(temp[1]);
 		}
 		catch ( Exception e ) {
 			System.out.println("Sie Haben falsche Angaben im Datei-Headder gemacht");
 		}
 		return dimensions;
 	}
 	
 	private double[] readLinen(String line) {
 		String[] temp = line.split(" ");
 		double[] dubbel = new double[temp.length];
 		int i = 0;
 		
 		while ( i < temp.length ) {
 			try {
 				dubbel[i] = Double.parseDouble(temp[i]);
 				i++;
 			}
 			catch ( Exception e ) {
 				System.out.println("Falsche Angabe in der Datei");
 			}
 		}
 		return dubbel;
 		
 	}
} 
