package com.everis.pages;

import com.everis.map.HomeSmartQualityMap;

public class HomeSmartHospitalityPage extends HomeSmartQualityMap{


    public void InserirValorCampoCpf(String valorCampo, String valor){
        campoDeTexto(valorCampo).sendKeys(valor);
    }
}
