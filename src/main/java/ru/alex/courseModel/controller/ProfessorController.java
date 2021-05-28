package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.model.ProfessorDto;
import ru.alex.courseModel.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public List<ProfessorDto> getAll() {
        return ProfessorDto.getProfessorDtoList(professorService.getAll());
    }

    @GetMapping("/{id}")
    public ProfessorDto get(@PathVariable long id) {
        return new ProfessorDto(professorService.get(id));
    }

    @PostMapping
    public ProfessorDto add(@RequestBody Professor professor) {
        return new ProfessorDto(professorService.save(professor));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        professorService.deleteProfessor(id);
    }

    @PutMapping("/{id}")
    public ProfessorDto update(@PathVariable long id,
                               @RequestBody Professor professor) {
        return new ProfessorDto(professorService.update(id, professor));
    }
}
