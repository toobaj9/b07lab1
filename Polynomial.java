import java.lang.Math;
public class Polynomial {
	//fields
	double[] coeff;

	//methods
	public Polynomial() {
		this.coeff = new double[]{0};
	}
	public Polynomial(double [] arr) {
		this.coeff = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			this.coeff[i] = arr[i];
		}
	}
	public Polynomial add(Polynomial p_array) {
		int length = this.coeff.length;
		if (p_array.coeff.length > length)
			length = p_array.coeff.length;

		double[] result = new double[length];
		for (int i = 0; i < this.coeff.length; i++) {
			result[i] = this.coeff[i];
		}
		for (int i = 0; i < p_array.coeff.length; i++) {
			result[i] += p_array.coeff[i];
		}
		return new Polynomial(result);
	}
	public double evaluate(double val) {
		double result = 0;
		for (int i = 0; i < this.coeff.length; i++) {
			result += this.coeff[i] * Math.pow(val, i);
		}
		return result;
	}
	public boolean hasRoot(double value) {
		return this.evaluate(value) == 0;
	}
}

