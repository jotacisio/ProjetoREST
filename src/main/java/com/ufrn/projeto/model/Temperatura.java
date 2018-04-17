package com.ufrn.projeto.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMPERATURA")
public class Temperatura implements Serializable {

    @Id
    @Column(name = "id_temperatura")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private double value;
    @Column(name = "data_coleta", nullable = false)
    private Date dataColeta;

    public Temperatura(double value, Date dataColeta) {
        this.value = value;
        this.dataColeta = dataColeta;
    }

    public void setId(Integer id) { this.id = id; }
    public void setValue(double value) { this.value = value; }
    public void setDataColeta(Date dataColeta) { this.dataColeta = dataColeta; }
    public Integer getId() { return id; }
    public double getValue() { return value; }
    public Date getDataColeta() { return dataColeta; }
    //Equals and Hash
}
