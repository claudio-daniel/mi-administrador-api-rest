package com.springboot.data.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	private int numElemPorPagina;
	private int paginaActual;
	
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		numElemPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1;
		
		int desde, hasta;
		
		if(totalPaginas <= numElemPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		}
		else {
			if(paginaActual <= numElemPorPagina /2 )
			{
				desde = 1;
				hasta = numElemPorPagina;
			}
			else if(paginaActual >= totalPaginas - numElemPorPagina/2){
				
				desde = totalPaginas - numElemPorPagina + 1;
				hasta = numElemPorPagina;
			}
			else {
				desde = paginaActual - numElemPorPagina /2;
				hasta = numElemPorPagina;
			}
		}
		
		for(int i= 0; i<hasta; i++) {
			 
			paginas.add( new PageItem(desde+i, paginaActual == desde+i));
		}
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public int getNumElemPorPagina() {
		return numElemPorPagina;
	}

	public void setNumElemPorPagina(int numElemPorPagina) {
		this.numElemPorPagina = numElemPorPagina;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PageItem> paginas) {
		this.paginas = paginas;
	}
	
	public boolean isFirst() {
		
		return page.isFirst();
	}
	
	public boolean isLast() {
		
		return page.isLast();
	}
	
	public boolean isHasNext() {
		
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		
		return page.hasPrevious();
	}
}
