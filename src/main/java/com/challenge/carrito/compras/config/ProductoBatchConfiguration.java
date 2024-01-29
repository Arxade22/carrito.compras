package com.challenge.carrito.compras.config;

import com.challenge.carrito.compras.model.ProductoDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import javax.sql.DataSource;

@Configuration
public class ProductoBatchConfiguration {


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ProductoBatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job productoJob(Step stepProductos) {
        return jobBuilderFactory.get("productoJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepProductos)
                .end()
                .build();
    }

    @Bean
    public Step stepProductos(ItemReader<ProductoDTO> readerProductos,
                              ItemProcessor<ProductoDTO, ProductoDTO> processorProductos,
                              ItemWriter<ProductoDTO> writerProductos) {
        return stepBuilderFactory.get("stepProductos")
                .<ProductoDTO, ProductoDTO>chunk(10)
                .reader(readerProductos)
                .processor(processorProductos)
                .writer(writerProductos)
                .build();
    }

    @Bean
    public FlatFileItemReader<ProductoDTO> readerProductos() {
        return new FlatFileItemReaderBuilder<ProductoDTO>()
                .name("productoItemReader")
                .resource(new ClassPathResource("productos.xlsx")) // Reemplaza con tu ubicación y nombre de archivo
                .delimited()
                .names("nombre", "precio")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(ProductoDTO.class);
                }})
                .lineTokenizer(productoLineTokenizer())
                .build();
    }

    @Bean
    public LineTokenizer productoLineTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("nombre", "precio");
        return tokenizer;
    }
}

