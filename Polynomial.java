import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.io.FileNotFoundException;
public class Polynomial {
	//fields
	double[] coeff;
	int[] exp;

	//methods
	public Polynomial() {
		this.coeff = new double[]{0};
		this.exp = new int[]{0};
	}
	public Polynomial(double [] arr, int [] exp_arr) {
		int nonZeroCount = 0;
     		for (int i = 0; i < arr.length; i++) {
        		if (arr[i] != 0) {
            			nonZeroCount++;
        		}
    		}
		this.coeff = new double[nonZeroCount];
     		this.exp = new int[nonZeroCount];
		int index = 0;
    		for (int i = 0; i < arr.length; i++) {
        		if (arr[i] != 0) {
            			this.coeff[index] = arr[i];
            			this.exp[index] = exp_arr[i];
            			index++;
        		}
   		}

	}
	public Polynomial add(Polynomial p_array) {
		int coeff_len = p_array.coeff.length + this.coeff.length;
		int exp_len = p_array.exp.length + this.exp.length;

		int[] result_exp = new int[exp_len]; // max length of exp array can be made by adding the two arrays with diff. values.
		double[] result_coeff = new double[coeff_len];

		for (int i = 0; i < this.coeff.length; i++) {
			result_coeff[i] = this.coeff[i];
			result_exp[i] = this.exp[i];
		}
		int j = 0;
		int new_len = this.exp.length;
		for (int i = 0; i < p_array.exp.length; i++) {
			int index = findExponent(result_exp, p_array.exp[i], new_len);
			if (index != -1) {
				result_coeff[index] += p_array.coeff[i];
			} else {
				result_coeff[new_len] = p_array.coeff[i];
				result_exp[new_len] = p_array.exp[i];
				new_len++;
			}
		}
		double[] new_coeff = Arrays.copyOf(result_coeff, new_len);
		int[] new_exp = Arrays.copyOf(result_exp, new_len); 
		return new Polynomial(new_coeff, new_exp);
	}
	public double evaluate(double val) {
		double result = 0;
		for (int i = 0; i < this.coeff.length; i++) {
			result += this.coeff[i] * Math.pow(val, exp[i]);
		}
		return result;
	}
	public boolean hasRoot(double value) {
		return this.evaluate(value) == 0;
	}
	public Polynomial multiply(Polynomial P) {
		double[] res_coeff = new double[this.coeff.length * P.coeff.length];
		int[] res_exp = new int[this.coeff.length * P.coeff.length];
		int k = 0;
		for (int i = 0 ; i < this.coeff.length; i++) {
			
			for (int j = 0; j < P.coeff.length; j++) {
				int added_exp = this.exp[i] + P.exp[j];
				double multi_coeff = this.coeff[i] * P.coeff[j];
				int index = findExponent(res_exp, added_exp, k);
				if (index != -1) {
					res_coeff[index] += multi_coeff;
				} else {
					res_coeff[k] = multi_coeff;
					res_exp[k] = added_exp;
					k++;
				}
			}
		}
		int nonZeroCount = 0;
    		for (int i = 0; i < k; i++) {
        		if (res_coeff[i] != 0) {
            			nonZeroCount++;
       			 }
   		}
		double[] new_coeff = new double[nonZeroCount];
                int[] new_exp = new int[nonZeroCount];

		int index = 0;
    		for (int i = 0; i < k; i++) {
        		if (res_coeff[i] != 0) {
            			new_coeff[index] = res_coeff[i];
            			new_exp[index] = res_exp[i];
            			index++;
       			}
      		}
		return new Polynomial(new_coeff, new_exp);
	}

 	private int findExponent(int[] expArray, int exponent, int size) {
        	for (int i = 0; i < size; i++) {
            		if (expArray[i] == exponent) {
                		return i; // Return the index if found
            		}
        	}
        	return -1; // Return -1 if not found
   	}
	public Polynomial(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int[] expo = new int[0];
		double[] coeff = new double[0];
		double val;
		
        	if (scanner.hasNextLine()) {
            		String poly = scanner.nextLine();
			String[] arr = poly.split("(?=[+-])");
			expo = new int[arr.length];
			coeff = new double[arr.length];
			for (int i = 0; i < arr.length; i++) {
				if (!arr[i].contains("x")) {
					expo[i] = 0;
					val = Double.parseDouble(arr[i]);
				}
				else {
					String[] subpart = arr[i].split("x");
					if (subpart.length > 1) {
						int exp = Integer.parseInt(subpart[1]);
						expo[i] = exp;
					} else {
						expo[i] = 1;
					}
					val = Double.parseDouble(subpart[0]);
				}
				
			        coeff[i] = val;
				
			}
		}
		scanner.close(); 
		this.coeff = coeff; 
                this.exp = expo; 
		
	}
	public void saveToFile(String File_name) {
		String text = "";
		try {
			for (int j = 0; j < coeff.length; j++) {
				text = text + String.valueOf(coeff[j]);
				if (exp[j] != 0) {
					text = text + "x";
					if (exp[j] != 1)
						text = text + String.valueOf(exp[j]);
					
				}
				if ((j+ 1) <= (coeff.length - 1) && coeff[j+1] > 0) {
					text = text + "+";
				}
			}
			FileWriter writer = new FileWriter(File_name);
			writer.write(text);
			System.out.println("Polynomial added Successfully");
			writer.close();
		} catch(IOException e) {
			System.out.println("An error occurred while writing to the file.");
                        e.printStackTrace();
		}
		
	}
}

