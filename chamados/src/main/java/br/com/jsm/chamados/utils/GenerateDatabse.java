package br.com.jsm.chamados.utils;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenerateDatabse {
	
	public static void main(String[] args) {
		Properties cfg = new Properties();
		cfg.setProperty("hibernate.hdm2ddl.auto", "update");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default", cfg);
		EntityManager em = emf.createEntityManager();
		
		em.close();
		emf.close();
	}
}
