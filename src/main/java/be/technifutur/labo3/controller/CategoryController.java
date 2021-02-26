package be.technifutur.labo3.controller;

import be.technifutur.labo3.dto.CategoryDTO;
import be.technifutur.labo3.entity.Category;
import be.technifutur.labo3.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/categories")
public class CategoryController implements RestControllable<Category, CategoryDTO, Integer> {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(this.categoryService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDTO> getOne(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(this.categoryService.getById(integer));
    }

    @Override
    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.insert(category));
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<Boolean> update(@RequestBody Category category, @PathVariable("id") Integer integer) {
        return ResponseEntity.ok(this.categoryService.update(category, integer));
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(this.categoryService.delete(integer));
    }
}
