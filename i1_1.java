import java.io.*;
import java.math.*;
import java.util.*;

public class i1_1
{
	private BigInteger p, q, N, phi, e, d;	
	private int bitlength = 1024;
	private Random r;
	
	public RSAString()
	{
		r = new Random();
		p = BigInteger.probablePrime(bitlength, r);
		q = BigInteger.probablePrime(bitlength, r);
		
		N = p.multiply(q);
		
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		e = BigInteger.probablePrime(bitlength / 2, r);
	
		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
		{
			e.add(BigInteger.ONE);
		}

		d = e.modInverse(phi);
	}
	public RSAString(BigInteger e, BigInteger d, BigInteger N)
	{
		this.e = e;
		this.d = d;
		this.N = N;
	}
	
	public static void main(String[] args) throws IOException
	{
		RSAString rsa = new RSAString();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the plain text:");
		String teststring = br.readLine();

		System.out.println("Encrypting String: " + teststring);
		System.out.println("String in Bytes: "+ bytesToString(teststring.getBytes()));

		// encrypt
		byte[] encrypted = rsa.encrypt(teststring.getBytes());
		System.out.println("Encrypted String: " + new String(encrypted));

		// decrypt
		byte[] decrypted = rsa.decrypt(encrypted);
		
		System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
		System.out.println("Decrypted String: " + new String(decrypted));
	}
	private static String bytesToString(byte[] encrypted)
	{
		String test = "";
		for (byte b : encrypted)
		{
		test += Byte.toString(b);
		}
		return test;
	}
	// Encrypt message
	public byte[] encrypt(byte[] message)
	{
		return (new BigInteger(message)).modPow(e, N).toByteArray();
	}
	// Decrypt message
	public byte[] decrypt(byte[] message)
	{
		return (new BigInteger(message)).modPow(d, N).toByteArray();
	}
}