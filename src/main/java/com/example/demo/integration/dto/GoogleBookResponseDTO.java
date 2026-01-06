package com.example.demo.integration.dto;

import java.util.List;

public class GoogleBookResponseDTO {

    private List<GoogleBookItemDTO> items;

    public List<GoogleBookItemDTO> getItems() {
        return items;
    }

    public void setItems(List<GoogleBookItemDTO> items) {
        this.items = items;
    }
}
