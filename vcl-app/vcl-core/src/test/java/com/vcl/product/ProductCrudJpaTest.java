package com.vcl.product;

import static com.vcl.persistence.VclPersistUnits.VCL_CORE;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.vcl.product.Product;

@Ignore("Ignored because a META-INF/persistence.xml " + 
		"file is required to get this test to run.")
public class ProductCrudJpaTest {
	
	private EntityManager em;
	
	@Before
	public void setup() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(VCL_CORE.getPersistUnitName());
		em = emf.createEntityManager();
	}
	
	@Test
	public void testCreate() {
		Product p = new Product();
		p.setIndexNo("TEST");
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
		deleteProduct(p.getIndexNo());
		
		em.close();
		
		
	}
	
	private void deleteProduct(String indexNo){
		em.getTransaction().begin();
		Product prodToDelete = em.find(Product.class, indexNo);
		if (prodToDelete != null){
			em.remove(prodToDelete);
		}
		em.getTransaction().commit();
	}
	
	
}
