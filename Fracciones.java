
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 65536 / 2);
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public String nextString() {
		return next();
	}

	public String nextLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public BigInteger nextBigInteger() {
		return new BigInteger(next());
	}

	public BigDecimal nextBigDecimal() {
		return new BigDecimal(next());
	}

}

public class Fracciones {

	public static int numPares(int n) { /*dado un numero n=p1^e1*..*pk^ek, devuelve (2*p1+1)*...*(2*pk+1)*/
		int cont = 1;
		int t;
		for (int i = 0; i<prim.size() && prim.get(i)*prim.get(i)<=n; i++) {
			int primo = prim.get(i);
			t = 0;
			while (n % primo == 0) {
				n /= primo;
				t++;
			}
			if (t > 0)
				cont *= (2 * t + 1);
		}
		if (n > 1)
			cont *= (2 * 1 + 1);
		return cont % 2 == 0 ? cont / 2 : (cont + 1) / 2;
	}

	public static boolean[] primos;
	public static ArrayList<Integer> prim;

	public static void main(String[] args) {
		primos = new boolean[(int) Math.pow(2, 16)]; /*para factorizar un numero n=1..2^31-1, necesito comprobar sus divisores primos hasta sqrt(n)
		para hallar todos los numeros primos hasta sqrt(n) utilizo la criba de eratostenes */
		prim = new ArrayList<Integer>(); 
		for (int i = 2; i <= 254; i++) /*para hacer la criba de los numeros hata 2^16 solo necesito ir hasta 2^8*/
			if (!primos[i]) {
				for (int j = 2 * i; j < primos.length; j += i) { //marco todos los multiplos de ese numero primo
					primos[j] = true;
				}
			}
		for (int i = 2; i < Math.pow(2, 16); i++) {
			if (!primos[i])
				prim.add(i);
		}/*creo una lista de numero primos y los añado,*/
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = 0;
		while (true) {
			try {
				n = in.nextInt();
			} catch (Exception e) {
				break;
			}
			System.out.println(numPares(n));

		}
		out.close();

	}
}
