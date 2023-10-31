package br.com.gms.AppPessoas.dto;

import java.util.Objects;

public record PessoaMalaDiretaDTO(
    Long id,
    String nome,
    String malaDireta
) {
    public PessoaMalaDiretaDTO {
        Objects.requireNonNull(id);
        Objects.requireNonNull(nome);
        Objects.requireNonNull(malaDireta);
    }
}
