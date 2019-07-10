package com.substituicao.demo.exception;

public class ParametroNaoEncontradoException extends Exception{
    public ParametroNaoEncontradoException (long idParametro, String nomeParametro)
    {
        super("O id: " + idParametro + " do parâmetro: " + nomeParametro + " não foi encontrado em nossa base de dados");
    }
}
