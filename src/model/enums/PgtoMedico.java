package model.enums;

public enum PgtoMedico {
    AGENDADO("Agendado"),
    PAGO("Pago");

    private String descricao;

    PgtoMedico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

