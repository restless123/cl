import java.util.*;
import java.math.*;

public class i1
{
	public static void main(String args[])
	{
		 Scanner sc=new Scanner(System.in);

		 int p,q,phi,d=0,e,i;
		 BigInteger	P,C,N;
		 String msg; 

		 System.out.println("Enter the number to be encrypted and decrypted");
		 msg = sc.next();
		 
		 System.out.println("Enter 1st prime number p");
		 p=sc.nextInt();
		 
		 System.out.println("Enter 2nd prime number q");
		 q=sc.nextInt();
		 
		 N=BigInteger.valueOf(p*q);
		 System.out.println("The value of N = "+N);
		 
		 phi=(p-1)*(q-1);
		 System.out.println("The value of phi = "+phi); 
		 
		 for(e=2;e<phi;e++)
		 {
			 if(gcd(e,phi)==1) // e is for public key exponent
			 { 
				 break;
			 }
		 }

		 System.out.println("The value of e = "+e); 

		 for(i=0;i<=100;i++)
		 {
			 int x=1+(i*phi);
			 if(x%e==0)      //d is for private key exponent
			 {
				 d=x/e;
				 break;
			 }
		 }

		 System.out.println("The value of d = "+d); 

		 P = new BigInteger(msg);	
		// baseval.modpow(exponentval,modval)
		 C = P.modPow(BigInteger.valueOf(e),N);
		 P = C.modPow(BigInteger.valueOf(d),N);
	     
		 System.out.println("Encrypted message is : "+C);
		 System.out.println("Derypted message is : "+P);	 
	}	
		 
	static int gcd(int x, int y)
	{
		if(y==0)
		 return x; 
		else
		 return gcd(y,x%y);
		
	}
}


//10 7 17