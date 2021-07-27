package com.generation.blogPessoal.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//anotações são parametros que definem comportamentos pra classes ou propriedade
@Entity // será uma entidade do jpa hibernate
@Table (name = "publicacao") // vai virar uma tabela com o nome publicacao
public class Publicacao {
    
    
    @Id // será uma primary key
    @GeneratedValue (strategy = GenerationType.IDENTITY) //será um valor gerado, e a estrategia será de identidade (?)
    private long id;
    
    @NotNull
    @Size (min = 5, max = 100) //determinar o minino e o maximo de caracteres
    private String titulo;
    
    @NotNull
    @Size (min = 5, max = 500)
    private String texto;
    
    @Temporal (TemporalType.TIMESTAMP) //especificando para o hibernate que se trata de tempo, do tipo timestamp
    private Date data = new java.sql.Date(System.currentTimeMillis()); //ao instanciar o objeto, será armazenado a hora do sistema em que foi instanciado

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
}
