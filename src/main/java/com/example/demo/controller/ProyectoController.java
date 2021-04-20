package com.example.demo.controller;

import com.example.demo.entity.Proyecto;
import com.example.demo.repository.ProyectoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    ProyectoRepository proyectoRepository ;

    @Autowired
    UsuarioRepository usuarioRepository;



    @GetMapping("/listar")
    public String shipperList(Model model){

        model.addAttribute("proyectoLista", proyectoRepository.findAll());
        return "proyecto/listaProyectos";
    }
    @GetMapping("/agregar")
    public  String proyectoNew(Model model){

        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "proyecto/nuevoProyecto";
    }


    @PostMapping("/guardar")
    public  String proyectoSave (Proyecto proyecto){
        System.out.println(proyecto.getUsuario_owner());
        proyectoRepository.save(proyecto);
        return "redirect:/proyecto/listar";
    }
    @GetMapping("/edit")
    public  String editProyecto(@RequestParam("idproyecto") int idproyecto, Model model){
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(idproyecto);
        if(proyectoOptional.isPresent()){
            Proyecto proyecto = proyectoOptional.get();
            //progress----
            model.addAttribute("pesoActividadesFinalizadas", proyectoRepository.pesosActividadesFinalizados()*1.0);
            model.addAttribute("pesoActividades", proyectoRepository.pesosTodasActividades());
            //-----
            model.addAttribute("proyecto", proyecto);
            model.addAttribute("listaUsuarios", usuarioRepository.findAll());
            return "proyecto/editarProyecto";
        }else{
            return "redirect:/proyecto/listar";
        }
    }

    @GetMapping ("/delete")
    public  String deleteProyecto(@RequestParam("idproyecto") int idproyecto){
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(idproyecto);
        if(proyectoOptional.isPresent()){
            proyectoRepository.deleteById(idproyecto);
        }
        return "redirect:/proyecto/listar";
    }
}
