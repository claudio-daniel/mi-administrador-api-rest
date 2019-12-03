package com.springboot.data.app.util.paginator;

public class PageItem {
	
	private int numero;
	private boolean esActual;

	public PageItem(int numPagina, boolean esActual) {
		super();
		this.numero = numPagina;
		this.esActual = esActual;
	}


	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isEsActual() {
		return esActual;
	}

	public void setEsActual(boolean esActual) {
		this.esActual = esActual;
	}
	
	

}
