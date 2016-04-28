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

}