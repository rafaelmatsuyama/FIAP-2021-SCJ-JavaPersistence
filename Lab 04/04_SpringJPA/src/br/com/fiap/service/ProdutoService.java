package br.com.fiap.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;

@Component
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public void add(Produto produto) {
        produtoRepository.save(produto);
    }

    @Transactional(readOnly=true)
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Transactional
    public void addAll(Collection<Produto> produtos) {
        for (Produto produto : produtos) {
            produtoRepository.save(produto);
        }
    }

    @Transactional(readOnly=true)
    public List<Produto> findByName(String nome) {
        return produtoRepository.findByName(nome);
    }
}