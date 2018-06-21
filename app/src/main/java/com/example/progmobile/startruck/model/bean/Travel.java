package com.example.progmobile.startruck.model.bean;

public class Travel {

    private int idTravel;
    private String cityOrigem;
    private String cityDestino;
    private String dateSaída;
    private String datePrevista;
    private String dataChegada;
    private String placaCaminhao;
    private String motorista;
    private String distancia;
    private int idUser;


    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public int getIdTravel() {
        return idTravel;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdTravel(int idTravel) {
        this.idTravel = idTravel;
    }

    public String getCityOrigem() {
        return cityOrigem;
    }

    public void setCityOrigem(String cityOrigem) {
        this.cityOrigem = cityOrigem;
    }

    public String getCityDestino() {
        return cityDestino;
    }

    public void setCityDestino(String cityDestino) {
        this.cityDestino = cityDestino;
    }

    public String getDateSaída() {
        return dateSaída;
    }

    public void setDateSaída(String dateSaída) {
        this.dateSaída = dateSaída;
    }

    public String getDatePrevista() {
        return datePrevista;
    }

    public void setDatePrevista(String datePrevista) {
        this.datePrevista = datePrevista;
    }

    public String getPlacaCaminhao() {
        return placaCaminhao;
    }

    public void setPlacaCaminhao(String placaCaminhao) {
        this.placaCaminhao = placaCaminhao;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
}
