package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao;
	private UndirectedGraph<Country, DefaultEdge> grafo;
	
	public Model(){
		this.dao= new BordersDAO();
	}
	
	private void creaGrafo(int anno){
		this.grafo= new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(grafo, dao.loadAllCountries(anno));
		
		for(Country c : grafo.vertexSet()){
			for(Country cc : dao.getConfini(anno, c)){
				DefaultEdge e=grafo.addEdge(c, cc);
				//System.out.println(e);
			}
		}
		
		//System.out.println(grafo);
	}
	
	public List<Country> getPaesiAnno(int anno){
		this.creaGrafo(anno);
		List<Country> l= new ArrayList<Country>(grafo.vertexSet());
		Collections.sort(l);
		return l;
	}
	
}
