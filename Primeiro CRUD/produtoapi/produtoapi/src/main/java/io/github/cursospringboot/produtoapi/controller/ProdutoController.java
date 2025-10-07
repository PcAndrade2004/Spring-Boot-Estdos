package io.github.cursospringboot.produtoapi.controller;

import io.github.cursospringboot.produtoapi.model.Produto;
import io.github.cursospringboot.produtoapi.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // Marca a classe para receber sequisições
@RequestMapping("produtos") // Vai dizer qual é a URL do produto
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    //salvar
    @PostMapping // criar produto (Quero enviar dados/criar dados)
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Produto recebido " + produto);

        var id = UUID.randomUUID().toString();//gera códigos unicos
        produto.setId(id);

        produtoRepository.save(produto);
        return produto;
    }

    // Buscar produto por ID
    @GetMapping("{id}")
    public Produto buscarProdutoPorId( @PathVariable("id") String id) {
        //busca pelo (id) e depois verifica se o produto está presente, caso contrario retorna null
        return produtoRepository.findById(id).orElse(null);
    }

    // Deletando produto por ID
    @DeleteMapping("{id}")
    public void deletarPorId(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }

    //Atualizando produto
    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id,
                          @RequestBody Produto produto) {

        produto.setId(id);
        produtoRepository.save(produto);
    }

    //Bucar produto com Parametro
    @GetMapping
    public List<Produto> buscarProduto( @RequestParam("nome") String nome) {

        return produtoRepository.findByNome(nome);
    }

}
