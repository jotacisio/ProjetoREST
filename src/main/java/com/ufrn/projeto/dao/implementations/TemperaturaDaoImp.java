package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.ITemperaturaDao;
import com.ufrn.projeto.model.Temperatura;

public class TemperaturaDaoImp extends
        GenericDaoImpl<Temperatura, Integer> implements ITemperaturaDao {

    public TemperaturaDaoImp() {
        super(Temperatura.class);
    }
}
