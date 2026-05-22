package com.crud.oracle;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crud.oracle.model.Pessoa;
import com.crud.oracle.repository.PessoaRepository;


@SpringBootApplication
public class OracleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OracleApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PessoaRepository pessoaRepository) {
		return args -> {
			pessoaRepository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> {
						Pessoa p = new Pessoa();
						p.setNome("Pessoa" + i);
						p.setSobreNome("sobreNome" + i);
						p.setTelefone("(62) 99999-9999");
						p.setEmail("Pessoa" + i + "@email.com");
						return p;
					})
					.map(v -> pessoaRepository.save(v))
					.forEach(System.out::println);
		};
	}

}
