/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorscc;

// Classe para geracao de numeros aleatorios segundos varias distribuicoes
// Apenas a distribuicao exponencial negativa esta definida

public class Aleatorio {
	// Gera um numero segundo uma distribuicao exponencial negativa de media m
	static double exponencial (double m){
		return (-m*Math.log(Math.random()));
	}

        static double[] normal(double m, double d, int randStream){
		// System.out.println("Funcao normal a ser chamada... :-)");

		double v1, v2, w;
		double y[] = new double[2];
		double x[] = new double[2];
		do {
			do {
				v1 = 2 * RandomGenerator.rand(randStream) - 1;
				v2 = 2 * RandomGenerator.rand(randStream) - 1;
				w = Math.pow(v1, 2) + Math.pow(v2, 2);
			} while (w > 1);
			y[0] = v1 * Math.pow(((-2 * Math.log(w)) / w), 0.5);
			y[1] = v2 * Math.pow(((-2 * Math.log(w)) / w), 0.5);
		} while (y[0]<-1 || y[1] <-1 || y[0]>1 || y[1]>1);

		x[0] = m + y[0] * d;
		x[1] = m + y[1] * d;

		/* System.out.println("x1="+x[0]+" x2="+x[1]); */

		return x;
	}
}