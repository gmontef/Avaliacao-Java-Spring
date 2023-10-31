package br.com.gms.AppPessoas.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import br.com.gms.AppPessoas.exception.ResourceNotFoundException;
import br.com.gms.AppPessoas.model.Contato;
import br.com.gms.AppPessoas.service.ContatoService;
import br.com.gms.AppPessoas.service.PessoaService;

@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {

    private ContatoService contatoService;

    @Autowired
    public ContatoResource(ContatoService contatoService, PessoaService pessoaService) {
        this.contatoService = contatoService;
    }
    @GetMapping
    @Operation(summary = "Lista todos os Contatos")
    public ResponseEntity<List<Contato>> getAllContatos() {
        List<Contato> contatos = contatoService.getAll();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados de um Contato por ID")
    public ResponseEntity<Contato> getById(@PathVariable Long id) {
        Contato contato = contatoService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Contato com ID " + id + " não encontrado"));
        return ResponseEntity.ok(contato);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um Contato existente")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato updatedContato) {
        Contato existingContato = contatoService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Contato com ID " + id + " não encontrado"));
        existingContato.setTipocontato(updatedContato.getTipocontato());
        existingContato.setContato(updatedContato.getContato());
        Contato savedContato = contatoService.update(existingContato);
        return ResponseEntity.ok(savedContato);
    
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um Contato por ID")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Contato contatoParaExcluir = contatoService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Contato com ID " + id + " não encontrado"));
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
