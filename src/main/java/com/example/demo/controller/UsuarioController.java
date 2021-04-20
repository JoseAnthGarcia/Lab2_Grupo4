package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;



    @GetMapping("/usuario/listar")
    public String usuarioLista(Model model){

        model.addAttribute("usuarioList", usuarioRepository.findAll());
        return "usuario/list";
    }
    @GetMapping("/usuario/new")
    public  String usuarioNew(){
        return "usuario/newForm";
    }


    @PostMapping("/usuario/save")
    public  String usuarioSave (Usuario usuario, RedirectAttributes attr){
        usuarioRepository.save(usuario);
        attr.addFlashAttribute("msg", "Usuario creado exitosamente");
        return "redirect:/usuario";
    }
    @GetMapping("/usuario/edit")
    public  String usuarioEdit(@RequestParam("correo") String correo, Model model){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(correo);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            model.addAttribute("usuario", usuario);
            return "usuario/editForm";
        }else{
            return "redirect:/usuario";
        }
    }

    @GetMapping ("/shipper/delete")
    public  String deleteUsuario(@RequestParam("correo") String correo){
        Optional<Usuario> usuarioOptional =usuarioRepository.findById(correo);
        if(usuarioOptional.isPresent()){
            usuarioRepository.deleteById(correo);

            return "redirect:/usuario";
        }
        return "redirect:/usuario";
    }
}
