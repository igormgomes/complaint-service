package br.com.reclamacao.service.exception;

public class ReclamacaoNotFoundException extends NotFoundException {

    public ReclamacaoNotFoundException() {
        super("Nenhuma reclamação encontrada");
    }

    public ReclamacaoNotFoundException(String message) {
        super(message);
    }
}
