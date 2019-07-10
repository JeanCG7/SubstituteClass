package com.substituicao.demo.servicos;

import com.substituicao.demo.dto.AulaDTO;
import com.substituicao.demo.dto.DocenteDTO;
import com.substituicao.demo.dto.PlanoDeSubstituicaoDTO;
import com.substituicao.demo.exception.LimiteAulaDiariaSubstituicaoException;
import com.substituicao.demo.exception.ParametroNaoEncontradoException;
import com.substituicao.demo.exception.RequerenteNaoAutorizadoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class PlanoDeSubstituicaoServico {

    private List<PlanoDeSubstituicaoDTO> planos;
    private List<AulaDTO> aulas;
    private List<DocenteDTO> requerentes;

    @GetMapping("/servico/plano")
    public ResponseEntity<List<PlanoDeSubstituicaoDTO>> listar() {
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/servico/plano/{id}")
    public ResponseEntity<PlanoDeSubstituicaoDTO> listarPorId(@PathVariable int id) {
        Optional<PlanoDeSubstituicaoDTO> plano = planos.stream().filter(p -> p.getId() == id).findAny();
        return ResponseEntity.of(plano);
    }

    @PostMapping("/servico/plano")
    public ResponseEntity<PlanoDeSubstituicaoDTO> criar(PlanoDeSubstituicaoDTO plano) throws ParametroNaoEncontradoException, LimiteAulaDiariaSubstituicaoException {
        if (plano.getAula().getAulas() > 6)
            throw new LimiteAulaDiariaSubstituicaoException();

        Optional<AulaDTO> aulaExistente = aulas.stream().filter(a -> a.getId() == plano.getAula().getId()).findAny();
        Optional<DocenteDTO> requerenteExistente = requerentes.stream().filter(r -> r.getId() == plano.getRequerente().getId()).findAny();

        plano.setAula(
                Optional.ofNullable(aulaExistente.get()).orElseThrow(() ->
                        new ParametroNaoEncontradoException(plano.getAula().getId(), "Aula")));
        plano.setRequerente(
                Optional.ofNullable(requerenteExistente .get()).orElseThrow(() ->
                        new ParametroNaoEncontradoException(plano.getRequerente().getId(), "Requirinte")));
        plano.setAprovado(false);

        long maiorPlanoId = Collections.max(planos, Comparator.comparing(c -> c.getId())).getId();
        plano.setId(maiorPlanoId+1);
        planos.add(plano);

        return ResponseEntity.ok(plano);
    }

    @PutMapping("/servico/plano/{id}")
    public ResponseEntity<PlanoDeSubstituicaoDTO> atualizar(@PathVariable int id, @RequestBody PlanoDeSubstituicaoDTO planoReq) throws ParametroNaoEncontradoException{
        Optional<PlanoDeSubstituicaoDTO> plano = planos.stream().filter(p -> p.getId() == id).findAny();

        if (plano.isPresent()) {
            Optional<AulaDTO> aulaExistente = aulas.stream().filter(a -> a.getId() == plano.get().getAula().getId()).findAny();
            Optional<DocenteDTO> requerenteExistente = requerentes.stream().filter(r -> r.getId() == plano.get().getRequerente().getId()).findAny();
            plano.get().setAula(
                    Optional.ofNullable(aulaExistente.get()).orElseThrow(() ->
                            new ParametroNaoEncontradoException(plano.get().getAula().getId(), "Aula")));
            plano.get().setRequerente(
                    Optional.ofNullable(requerenteExistente.get()).orElseThrow(() ->
                            new ParametroNaoEncontradoException(plano.get().getRequerente().getId(), "Requirinte")));
            plano.get().setJustificativa(planoReq.getJustificativa());
        }
        return ResponseEntity.of(plano);
    }

    @PutMapping("/servico/plano/aprovar/{id}")
    public ResponseEntity mudarStatus(@PathVariable int id, @RequestBody DocenteDTO requerente) throws RequerenteNaoAutorizadoException {
        Optional<PlanoDeSubstituicaoDTO> plano = planos.stream().filter(p -> p.getId() == id).findAny();

        if (plano.get().getRequerente().getId() != requerente.getId())
            throw new RequerenteNaoAutorizadoException();

        plano.ifPresent(p -> {
            p.setAprovado(true);
        });

        return ResponseEntity.of(plano);
    }

    @DeleteMapping("/servico/plano/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        if (planos.removeIf(plano-> plano.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }


}
