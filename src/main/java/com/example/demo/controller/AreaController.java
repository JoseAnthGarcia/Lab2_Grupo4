package com.example.demo.controller;

import com.example.demo.entity.AreaEntity;
import com.example.demo.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @GetMapping("/listar")
    public String listArea(Model model){
        model.addAttribute("listaAreas", areaRepository.findAll());
        return "area/list";
    }

    @PostMapping("/guardar")
    public String saveArea(AreaEntity area){
        areaRepository.save(area);
        return "redirect:/area/listar";
    }

    @GetMapping("/editar")
    public String editArea(@RequestParam("id") int id, Model model){
        Optional<AreaEntity> areaOpt = areaRepository.findById(id);

        if(areaOpt.isPresent()){
            model.addAttribute("area",areaOpt.get());
            return "area/edit";
        }else{
            return "redirect:/area/listar";
        }

    }

    @GetMapping("/eliminar")
    public String deleteArea(@RequestParam("id") int id){
        Optional<AreaEntity> areaOpt = areaRepository.findById(id);

        if(areaOpt.isPresent()){
            areaRepository.delete(areaOpt.get());
        }

        return "redirect:/area/listar";
    }


}
