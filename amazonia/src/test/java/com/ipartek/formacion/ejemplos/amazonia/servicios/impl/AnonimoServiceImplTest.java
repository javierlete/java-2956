package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ipartek.formacion.ejemplos.amazonia.dtos.CredencialesDto;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

@SpringBootTest
class AnonimoServiceImplTest {
	@Autowired
	private AnonimoService anonimoService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAutenticar() {
		assertNotNull(anonimoService);
		
		assertTrue(anonimoService.autenticar(null).isEmpty());
		
		assertTrue(anonimoService.autenticar(new CredencialesDto(null, null)).isEmpty());

		assertTrue(anonimoService.autenticar(new CredencialesDto("javier@email.net", "INCORRECTA")).isEmpty());
		
		var javier = anonimoService.autenticar(new CredencialesDto("javier@email.net", "javier"));
		
		assertTrue(javier.isPresent());
		
		assertEquals("Javier", javier.get().nombre());
		assertEquals("javier@email.net", javier.get().email());
		assertEquals("ADMINISTRADOR", javier.get().rol());

		var pepe = anonimoService.autenticar(new CredencialesDto("pepe@email.net", "pepe"));
		
		assertTrue(pepe.isPresent());
		
		assertEquals("Pepe", pepe.get().nombre());
		assertEquals("pepe@email.net", pepe.get().email());
		assertEquals("USUARIOS", pepe.get().rol());
	}

}
