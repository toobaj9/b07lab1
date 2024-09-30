import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException; 
public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		double [] c1 = {1, 0, -4};
		int [] exp1 = {2, 1, 0};
		Polynomial p1 = new Polynomial(c1, exp1);
		System.out.println("p1 evaluated at x=3: " + p1.evaluate(3));
		
		double [] c2 = {2, -2};
		int [] exp2 = {1, 0};
		Polynomial p2 = new Polynomial(c2, exp2);

		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));

		Polynomial multi = p1.multiply(p2);
		System.out.println("Product of p1 and p2 evaluated at x=2: " + multi.evaluate(2));

		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		// adding a polynomial to the file
		String fileName = "polynomial_p1.txt";
        	p1.saveToFile(fileName);
        	System.out.println("p1 saved to " + fileName);

        	// Test case 5: Read polynomial from file
        	try {
            		File file = new File(fileName);
            		Polynomial pFromFile = new Polynomial(file);
            		System.out.println("Polynomial read from file evaluated at x=2: " + pFromFile.evaluate(2));
        	} catch (FileNotFoundException e) {
            		System.out.println("File not found: " + e.getMessage());
        	}
	}
}



