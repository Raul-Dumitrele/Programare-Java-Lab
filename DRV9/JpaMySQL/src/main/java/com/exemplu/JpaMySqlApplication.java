package com.exemplu;

import com.exemplu.entity.Masini;
import com.exemplu.repository.MasiniJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class JpaMySqlApplication implements CommandLineRunner {

	private Logger logger = Logger.getLogger(JpaMySqlApplication.class.getName());

	@Autowired
	MasiniJpaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(JpaMySqlApplication.class, args);
	}

	@Override
	public void run(String... args) {

		System.out.println("------- Continut initial -------");
		repository.findAll().forEach(System.out::println);

		repository.insert(new Masini("MH45DUM", "AUDI", 2011, "Alb", 70000));

		repository.deleteById("MH01AAA");

		System.out.println("\nMasina cu nr MH03CCC:");
		System.out.println(repository.findById("MH03CCC"));

		System.out.println("\nToate masinile:");
		repository.findAll().forEach(System.out::println);

		String marcaCautata = "Dacia";
		long numarDacia = repository.countByMarca(marcaCautata);
		System.out.println("\nNumar masini marca " + marcaCautata + ": " + numarDacia);
		logger.info("Număr mașini marca Dacia: " + numarDacia);

		long sub100k = repository.countByKmUnder100k();
		System.out.println("\nMasini cu sub 100.000 km: " + sub100k);
		logger.info("Masini sub 100k km: " + sub100k);

		System.out.println("\nMasini mai noi de 5 ani:");
		repository.findMasiniNoi5Ani().forEach(System.out::println);
		logger.info("Masini noi: " + repository.findMasiniNoi5Ani().size());
	}
}
