package br.com.fiap.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.fiap") 
public class SpringTest
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringTest.class, args);
    }
}