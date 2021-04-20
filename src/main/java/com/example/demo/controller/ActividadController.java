package com.example.demo.controller;

import com.example.demo.entity.Actividad;
import com.example.demo.entity.Proyecto;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ActividadRepository;
import com.example.demo.repository.ProyectoRepository;
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
@RequestMapping("/actividad")
public class ActividadController {
    @Autowired
    ProyectoRepository proyectoRepository ;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ActividadRepository actividadRepository;

    @GetMapping("/new")
    public  String actividadNew(@RequestParam("idproyecto") int idproyecto, Model model){
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(idproyecto);
        if(proyectoOptional.isPresent()){
            Proyecto proyecto = proyectoOptional.get();
            model.addAttribute("proyecto", proyecto);
            model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "actividad/nuevaActividad";
        }else{
            return "redirect:/proyecto/listar";
        }
    }
    @PostMapping("/guardarE")
    public  String actividadSaveE (Actividad actividad, RedirectAttributes attr){

        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(actividad.getIdproyecto());
        if(proyectoOptional.isPresent()){
            actividadRepository.save(actividad);
            Proyecto proyecto = proyectoOptional.get();
            attr.addFlashAttribute("msg", "Actividad actualizada exitosamente");
            return "redirect:/proyecto/edit?idproyecto="+ proyecto.getIdproyecto();
        }else{
            return "redirect:/proyecto/listar";
        }
    }
    @PostMapping("/guardar")
    public  String actividadSave (Actividad actividad, RedirectAttributes attr){

        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(actividad.getIdproyecto());
        if(proyectoOptional.isPresent()){

            actividadRepository.save(actividad);
            Proyecto proyecto = proyectoOptional.get();
            attr.addFlashAttribute("msgPrimary", "Actividad creada exitosamente");
            return "redirect:/proyecto/edit?idproyecto="+ proyecto.getIdproyecto();
        }else{
            return "redirect:/proyecto/listar";
        }
    }

    @GetMapping("/cancelar")
    public  String cancelarRegistro(@RequestParam("idproyecto") int idproyecto, Model model){
        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(idproyecto);
        if(proyectoOptional.isPresent()){
            Proyecto proyecto = proyectoOptional.get();
            return "redirect:/proyecto/edit?idproyecto="+ proyecto.getIdproyecto();
        }else{
            return "redirect:/proyecto/listar";
        }
    }
    @GetMapping("/edit")
    public  String editActividad(@RequestParam("idactividad") int idactividad, Model model){
        Optional<Actividad> actividadOptional = actividadRepository.findById(idactividad);
        if(actividadOptional.isPresent()){
            Actividad actividad = actividadOptional.get();
            model.addAttribute("actividad", actividad);
            model.addAttribute("listaUsuarios", usuarioRepository.findAll());
            return "actividad/editarActividad";
        }else{
            return "redirect:/proyecto/listar";
        }
    }

    @GetMapping ("/delete")
    public  String deleteActividad(@RequestParam("idactividad") int idactividad, RedirectAttributes attr) {
        Optional<Actividad> actividad = actividadRepository.findById(idactividad);

        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(actividad.get().getIdproyecto());
        if(proyectoOptional.isPresent()){

            actividadRepository.deleteById(actividad.get().getIdactividad());
            Proyecto proyecto = proyectoOptional.get();

            attr.addFlashAttribute("msgDanger", "Actividad eliminada exitosamente");

            return "redirect:/proyecto/edit?idproyecto="+ proyecto.getIdproyecto();

        }else{

            return "redirect:/proyecto/listar";
        }
    }
}
