package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AreaRepository areaRepository;

    @GetMapping("/listar")
    public String usuarioLista(Model model){

        model.addAttribute("usuarioList", usuarioRepository.findAll());
        return "usuario/list";
    }
    @GetMapping("/new")
    public  String usuarioNew(Model model){
        model.addAttribute("listaAreas", areaRepository.findAll());
        return "usuario/newForm";
    }


    @PostMapping("/guardarE")
    public  String usuarioSaveE (Usuario usuario, RedirectAttributes attr){
        usuarioRepository.save(usuario);
        attr.addFlashAttribute("msg", "Usuario actualizado exitosamente");
        return "redirect:/usuario/listar";
    }
    @PostMapping("/guardar")
    public  String usuarioSave (Usuario usuario, RedirectAttributes attr){
        usuarioRepository.save(usuario);
        attr.addFlashAttribute("msgPrimary", "Usuario creado exitosamente");
        return "redirect:/usuario/listar";
    }
    @GetMapping("/edit")
    public  String usuarioEdit(@RequestParam("correo") String correo, Model model){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(correo);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            model.addAttribute("usuario", usuario);
            model.addAttribute("listaAreas", areaRepository.findAll());
            return "usuario/editForm";
        }else{
            return "redirect:/usuario";
        }
    }

    @GetMapping ("/delete")
    public  String deleteUsuario(@RequestParam("correo") String correo, RedirectAttributes attr){
        Optional<Usuario> usuarioOptional =usuarioRepository.findById(correo);
        if(usuarioOptional.isPresent()){
            usuarioRepository.deleteById(correo);
            attr.addFlashAttribute("msgDanger", "Usuario eliminado exitosamente");
        }
        return "redirect:/usuario/listar";
    }
}
