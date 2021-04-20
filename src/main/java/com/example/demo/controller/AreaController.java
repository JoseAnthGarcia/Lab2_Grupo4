package com.example.demo.controller;

import com.example.demo.entity.AreaEntity;
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
@RequestMapping("/area")
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public String listArea(Model model){
        model.addAttribute("listaAreas", areaRepository.findAll());
        return "area/list";
    }

    @GetMapping("/agregar")
    public String newArea(){
        return "area/newAreaForm";
    }

    @PostMapping("/guardarE")
    public String saveAreaE(AreaEntity area, RedirectAttributes attr){
        areaRepository.save(area);
        attr.addFlashAttribute("msg", "Área actualizada exitosamente");
        return "redirect:/area/listar";
    }
    @PostMapping("/guardar")
    public String saveArea(AreaEntity area, RedirectAttributes attr){
        areaRepository.save(area);
        attr.addFlashAttribute("msgPrimary", "Área creada exitosamente");
        return "redirect:/area/listar";
    }

    @GetMapping("/editar")
    public String editArea(@RequestParam("id") int id, Model model){
        Optional<AreaEntity> areaOpt = areaRepository.findById(id);

        if(areaOpt.isPresent()){
            model.addAttribute("listaUsuarioArea", usuarioRepository.findByIdarea(id));
            model.addAttribute("area", areaOpt.get());
            return "area/edit";
        }else{
            return "redirect:/area/listar";
        }

    }

    @GetMapping("/eliminar")
    public String deleteArea(@RequestParam("id") int id, RedirectAttributes attr){
        Optional<AreaEntity> areaOpt = areaRepository.findById(id);

        if(areaOpt.isPresent()){
            areaRepository.delete(areaOpt.get());
            attr.addFlashAttribute("msgDanger", "Área eliminada exitosamente");
        }

        return "redirect:/area/listar";
    }


}
