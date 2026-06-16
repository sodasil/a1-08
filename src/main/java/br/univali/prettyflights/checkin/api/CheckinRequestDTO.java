package br.univali.prettyflights.checkin.api;

public class CheckinRequestDTO {
    private String localizador;
    private String assentoEscolhido;
    private int quantidadeBagagens;

    // Getters e Setters básicos
    public String getLocalizador() { return localizador; }
    public void setLocalizador(String localizador) { this.localizador = localizador; }
    
    public String getAssentoEscolhido() { return assentoEscolhido; }
    public void setAssentoEscolhido(String assentoEscolhido) { this.assentoEscolhido = assentoEscolhido; }

    public int getQuantidadeBagagens() { return quantidadeBagagens; }
    public void setQuantidadeBagagens(int quantidadeBagagens) { this.quantidadeBagagens = quantidadeBagagens; }
}