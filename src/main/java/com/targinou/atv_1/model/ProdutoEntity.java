package com.targinou.atv_1.model;

import com.targinou.atv_1.model.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;

    private String marca;

    private LocalDate dataFabricacao;

    private LocalDate dataValidade;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String lote;

    private boolean ativo;

}
