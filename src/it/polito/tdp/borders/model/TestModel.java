package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.borders.db.BordersDAO;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BordersDAO dao = new BordersDAO() ;
		Model model= new Model();
		for(Country c : model.getPaesiAnno(1831)){
			System.out.println("Stato: "+c.toString()+" confini: "+c.getConfinanti());
		}
		
		List<Country> l= new ArrayList<Country>(model.getPaesiAnno(1831));
		Collections.sort(l);
		System.out.println("\n\n");
		
		for(Country c : l){
			System.out.println("Stato: "+c.toString()+" confini: "+c.getConfinanti());
		}

	}

}
