package br.com.gms.AppPessoas.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import br.com.gms.AppPessoas.model.Contato;
import br.com.gms.AppPessoas.model.Pessoa;
import br.com.gms.AppPessoas.dto.PessoaMalaDiretaDTO;
import br.com.gms.AppPessoas.exception.ResourceNotFoundException;
import br.com.gms.AppPessoas.service.ContatoService;
import br.com.gms.AppPessoas.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {

    private PessoaService pessoaService;
    private ContatoService contatoService;

    @Autowired
    public PessoaResource(PessoaService pessoaService, ContatoService contatoService) {
        this.pessoaService = pessoaService;
        this.contatoService = contatoService;
    }

    @GetMapping
    @Operation(summary = "Retorna todas as pessoas")
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.getAll();
        if (pessoas.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pessoas);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova pessoa")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa newPessoa = pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPessoa);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados de uma pessoa por ID")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.getPessoaById(id);
        if (pessoa == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/maladireta/{id}")
    @Operation(summary = "Retorna os dados de uma pessoa por ID para mala direta")
    public ResponseEntity<PessoaMalaDiretaDTO> getPessoaMalaDiretaById(@PathVariable Long id) {
        PessoaMalaDiretaDTO pessoaMalaDiretaDTO = pessoaService.getPessoaMalaDiretaById(id);
        if (pessoaMalaDiretaDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pessoaMalaDiretaDTO);
    }

    @PostMapping("/{id}/contatos")
    @Operation(summary = "Adiciona um novo Contato a uma Pessoa")
    public ResponseEntity<Pessoa> addContatoToPessoa(@PathVariable Long id, @RequestBody Contato contato) {
        Pessoa pessoa = pessoaService.getPessoaById(id);
        
        if (pessoa == null) {
            throw new ResourceNotFoundException("Pessoa com ID " + id + " não encontrada");
        }
        if (contato.getTipocontato() == null || contato.getContato() == null) {
            return ResponseEntity.badRequest().build();
        }
        contato.setPessoa(pessoa);
        Contato novoContato = contatoService.save(contato);
        pessoa.addContato(novoContato);
        Pessoa pessoaAtualizada = pessoaService.save(pessoa);
        
        return ResponseEntity.ok(pessoaAtualizada);
    }
    @GetMapping("/{idPessoa}/contatos")
    @Operation(summary = "Lista todos os Contatos de uma Pessoa")
    public ResponseEntity<List<Contato>> getContatosByPessoa(@PathVariable Long idPessoa) {
        Pessoa pessoa = pessoaService.getPessoaById(idPessoa);
        if (pessoa == null) {
            throw new ResourceNotFoundException("Pessoa com ID " + idPessoa + " não encontrada");
        }
        List<Contato> contatos = pessoa.getContatos();
        return ResponseEntity.ok(contatos);
    }

    @PutMapping
    @Operation(summary = "Atualiza uma pessoa existente")
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
        Pessoa newPessoa = pessoaService.update(pessoa);
        if (newPessoa == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newPessoa);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma pessoa pelo ID")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

