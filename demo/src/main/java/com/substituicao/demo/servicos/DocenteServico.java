package com.substituicao.demo.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import com.substituicao.demo.dto.*;
import com.substituicao.demo.entity.Docente;
import com.substituicao.demo.entity.Pessoa;
import com.substituicao.demo.viewmodels.AulaListModel;
import com.substituicao.demo.viewmodels.TurmaModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
public class DocenteServico {
    private List<DocenteDTO> docentes;
    private List<PessoaDTO> pessoas;

    public DocenteServico(){

        docentes = Stream.of(
                DocenteDTO.builder().id(1).nome("Gabriel Costa").email("gabriel@utf.com").documentoDocente("DOC111")
                        .ativo(true).build(),
                DocenteDTO.builder().id(2).nome("Willian Watanabe").email("william@utf.com").documentoDocente("DOC222")
                        .ativo(true).build())
                .collect(Collectors.toList());
    }

    @GetMapping("/servico/docentes")
    public ResponseEntity<List<DocenteDTO>> listar() {

        return ResponseEntity.ok(docentes);
    }
}

