package cl.sanosysalvos.datos_analitica_service.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reportes_estadisticos")
public class ReporteEstadistico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String comuna;
    private String especie; // Ej: "Perro", "Gato"
    private Integer cantidadExtravios;
    private Integer cantidadRecuperados;
    private LocalDate mesRegistro;

    public ReporteEstadistico() {
        this.mesRegistro = LocalDate.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = comuna; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public Integer getCantidadExtravios() { return cantidadExtravios; }
    public void setCantidadExtravios(Integer cantidadExtravios) { this.cantidadExtravios = cantidadExtravios; }
    public Integer getCantidadRecuperados() { return cantidadRecuperados; }
    public void setCantidadRecuperados(Integer cantidadRecuperados) { this.cantidadRecuperados = cantidadRecuperados; }
    public LocalDate getMesRegistro() { return mesRegistro; }
    public void setMesRegistro(LocalDate mesRegistro) { this.mesRegistro = mesRegistro; }
}