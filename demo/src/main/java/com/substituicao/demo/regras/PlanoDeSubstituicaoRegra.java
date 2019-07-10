//package com.substituicao.demo.regras;
//
//import com.substituicao.demo.dao.PlanoDeSubstituicaoDAO;
//import com.substituicao.demo.dto.ChefiaDTO;
//import com.substituicao.demo.dto.CoordenadorCursoDTO;
//import com.substituicao.demo.dto.PessoaDTO;
//import com.substituicao.demo.dto.PlanoDeSubstituicaoDTO;
//import com.substituicao.demo.entity.PlanoDeSubstituicao;
//import com.substituicao.demo.exception.AprovadorNaoAutorizadoException;
//import com.substituicao.demo.exception.DocenteDesativadoException;
//import org.springframework.beans.BeanUtils;
//
//public class PlanoDeSubstituicaoRegra {
//
//    private PlanoDeSubstituicaoDAO planoDeSubstituicaoDAO;
//
//    private PlanoDeSubstituicao salvar(PlanoDeSubstituicaoDTO plano) throws DocenteDesativadoException
//    {
//        if(plano. getAula().getProfessor().getAtivo() == false)
//            throw new DocenteDesativadoException();
//
//        PlanoDeSubstituicao planoEntity = new PlanoDeSubstituicao();
//        BeanUtils.copyProperties(plano, planoEntity);
//
//        return this.planoDeSubstituicaoDAO.save(planoEntity);
//    }
//
//    private void aprovarPlano(PlanoDeSubstituicaoDTO plano, PessoaDTO pessoa) throws AprovadorNaoAutorizadoException
//    {
//        if(pessoa instanceof CoordenadorCursoDTO || pessoa instanceof  ChefiaDTO) {
//            plano.setAprovado(true);
//
//            PlanoDeSubstituicao planoEntity = new PlanoDeSubstituicao();
//            BeanUtils.copyProperties(plano, planoEntity);
//
//            this.planoDeSubstituicaoDAO.save(planoEntity);
//        } else {
//            throw new AprovadorNaoAutorizadoException();
//        }
//    }
//}
