package com.challenge.carrito.compras.config;

import com.challenge.carrito.compras.model.ClienteDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ClienteBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ClienteBatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job clienteJob(Step stepClientes) {
        return jobBuilderFactory.get("clienteJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepClientes)
                .end()
                .build();
    }

    @Bean
    public Step stepClientes(ItemReader<ClienteDTO> readerClientes,
                             ItemProcessor<ClienteDTO, ClienteDTO> processorClientes,
                             ItemWriter<ClienteDTO> writerClientes) {
        return stepBuilderFactory.get("stepClientes")
                .<ClienteDTO, ClienteDTO>chunk(10)
                .reader(readerClientes)
                .processor(processorClientes)
                .writer(writerClientes)
                .build();
    }

    @Bean
    public FlatFileItemReader<ClienteDTO> readerClientes() {
        return new FlatFileItemReaderBuilder<ClienteDTO>()
                .name("clienteItemReader")
                .resource(new ClassPathResource("clientes.xlsx")) // Reemplaza con tu ubicación y nombre de archivo
                .delimited()
                .names("nombres", "apellidos", "antecedentes", "telefono", "email")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(ClienteDTO.class);
                }})
                .lineTokenizer(clienteLineTokenizer())
                .build();
    }

    @Bean
    public LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("nombres", "apellidos", "antecedentes", "telefono", "email");
        return tokenizer;
    }
}
