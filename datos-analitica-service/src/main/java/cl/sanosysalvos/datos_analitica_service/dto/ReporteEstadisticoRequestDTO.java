package cl.sanosysalvos.datos_analitica_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReporteEstadisticoRequestDTO {

    @NotBlank(message = "La comuna es obligatoria")
    private String comuna;

    @NotBlank(message = "La especie es obligatoria (ej: Perro, Gato)")
    private String especie;

    @NotNull(message = "La cantidad de extravíos es obligatoria")
    @Min(value = 0, message = "La cantidad de extravíos no puede ser negativa")
    private Integer cantidadExtravios;

    @NotNull(message = "La cantidad de recuperados es obligatoria")
    @Min(value = 0, message = "La cantidad de recuperados no puede ser negativa")
    private Integer cantidadRecuperados;

    // Getters y Setters
    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = comuna; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public Integer getCantidadExtravios() { return cantidadExtravios; }
    public void setCantidadExtravios(Integer cantidadExtravios) { this.cantidadExtravios = cantidadExtravios; }
    public Integer getCantidadRecuperados() { return cantidadRecuperados; }
    public void setCantidadRecuperados(Integer cantidadRecuperados) { this.cantidadRecuperados = cantidadRecuperados; }
}