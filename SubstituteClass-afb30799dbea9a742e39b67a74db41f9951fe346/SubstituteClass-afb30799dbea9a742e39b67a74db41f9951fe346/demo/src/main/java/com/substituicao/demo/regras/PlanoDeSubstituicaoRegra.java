package com.substituicao.demo.regras;

import com.substituicao.demo.dao.PlanoDeSubstituicaoDAO;
import com.substituicao.demo.dto.ChefiaDTO;
import com.substituicao.demo.dto.CoordenadorCursoDTO;
import com.substituicao.demo.dto.PessoaDTO;
import com.substituicao.demo.dto.PlanoDeSubstituicaoDTO;
import com.substituicao.demo.entity.PlanoDeSubstituicao;
import com.substituicao.demo.exception.AprovadorNaoAutorizadoException;
import com.substituicao.demo.exception.DocenteDesativadoException;
import org.springframework.beans.BeanUtils;
/*
    - Regras de negócio não finalizadas
    - Não Cumpriu todas as regras.
    - Faltou a regra de que não poderia ser substituidas mais de 6 aulas por dia.
*/
public class PlanoDeSubstituicaoRegra {

    private PlanoDeSubstituicaoDAO planoDeSubstituicaoDAO;

    private PlanoDeSubstituicao salvar(PlanoDeSubstituicaoDTO plano) throws DocenteDesativadoException
    {
        if(plano.getAula().getProfessor().getAtivo() == false)
            throw new DocenteDesativadoException();
           //condição IF com erros, faz usos de metodos que não estão disponiveis.
        PlanoDeSubstituicao planoEntity = new PlanoDeSubstituicao();
        BeanUtils.copyProperties(plano, planoEntity);

        return this.planoDeSubstituicaoDAO.save(planoEntity);
    }

    private void aprovarPlano(PlanoDeSubstituicaoDTO plano, PessoaDTO pessoa) throws AprovadorNaoAutorizadoException
    {
        if(pessoa instanceof CoordenadorCursoDTO || pessoa instanceof  ChefiaDTO) {
            plano.setAprovado(true);

            PlanoDeSubstituicao planoEntity = new PlanoDeSubstituicao();
            BeanUtils.copyProperties(plano, planoEntity);

            this.planoDeSubstituicaoDAO.save(planoEntity);
        } else {
            throw new AprovadorNaoAutorizadoException();
        }
    }
}
