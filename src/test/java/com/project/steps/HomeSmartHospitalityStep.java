package com.everis.steps;

import com.everis.pages.HomeSmartHospitalityPage;
import io.cucumber.java.pt.Dado;

public class HomeSmartHospitalityStep {



    @Dado("^que insire o \"(.*?)\"$")
    public void inserirCpfNoCampo(String valor){
        HomeSmartHospitalityPage homeSmartHospitalityPage = new HomeSmartHospitalityPage();
        String[] valores = valor.split(",");
        homeSmartHospitalityPage.InserirValorCampoCpf(valores[0], valores[1]);
    }
}
