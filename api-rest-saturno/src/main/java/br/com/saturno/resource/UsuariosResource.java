package br.com.saturno.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.saturno.model.Usuario;
import br.com.saturno.repository.Usuarios;

@RestController
@RequestMapping("/usuarios")
public class UsuariosResource {
	
	@Autowired
	private Usuarios usuarios;
	
	//Adicionar novo Usuário
	@PostMapping
	public Usuario adicionar(@Valid @RequestBody Usuario usuario){
		return usuarios.save(usuario);
	}
	
	//Listar todos os usuários
	@GetMapping
	public List<Usuario> list(){
		return usuarios.findAll();
	}
	
	//Buscar Usuário 
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id){
		Usuario usuario = usuarios.findOne(id);
		
		if (usuario == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Usuario usuario) {
		Usuario existente = usuarios.findOne(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(usuario, existente, "id");
		
		existente = usuarios.save(existente);
		
		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Usuario usuario = usuarios.findOne(id);
		
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		
		usuarios.delete(usuario);
		
		return ResponseEntity.noContent().build();
	}

	
}
