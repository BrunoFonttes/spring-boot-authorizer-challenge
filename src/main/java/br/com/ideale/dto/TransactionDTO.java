package br.com.ideale.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TransactionDTO {
    @NotBlank(message = "O numero do cartao eh obrigatorio.")
    private String numeroCartao;

    @NotBlank(message = "A senha do cartao eh obrigatorio.")
    private String senhaCartao;

    @NotNull(message = "O valor da transacao eh obrigatorio.")
    @Min(value = 0, message = "O valor minimo eh 0")
    private Float valor;
}
