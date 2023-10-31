package br.com.gms.AppPessoas.enums;

public enum TipoContato {
    TELEFONE(0),
    CELULAR(1);

    private int value;

    TipoContato(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

