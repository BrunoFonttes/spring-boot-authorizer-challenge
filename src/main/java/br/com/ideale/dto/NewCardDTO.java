package br.com.ideale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NewCardDTO {
    @NotBlank(message = "O numero do cartao eh obrigatorio.")
    private final String numeroCartao;

    @NotBlank(message = "A senha eh obrigatoria.")
    private final String senha;

    public NewCardDTO(String numeroCartao, String senha){
        this.numeroCartao = numeroCartao;
        this.senha = senha;
    }
}
