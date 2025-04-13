// src/main/java/com/example/demo/dtos/LeagueSummaryDTO.java
package com.example.demo.dtos;

public class LeagueSummaryDTO {
    private String name;
    private String urlImagen;

    public LeagueSummaryDTO() {}

    public LeagueSummaryDTO(String name, String urlImagen) {
        this.name = name;
        this.urlImagen = urlImagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
