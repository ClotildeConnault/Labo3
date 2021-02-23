package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.CategoryDTO;
import be.technifutur.labo3.entity.Category;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.CategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CategoryService implements Crudable<Category, CategoryDTO, Integer> {

    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    public CategoryService(CategoryRepository categoryRepository, Mapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toCategoryDTO)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public CategoryDTO getById(Integer integer) {
        return mapper.toCategoryDTO(categoryRepository.findById(integer).orElseThrow(() -> new NoSuchElementException("L'utilisateur' avec l'id " + integer + " n'a pas été trouvé")));
    }

    @Override
    public boolean insert(Category category) {
        Category newCategory = this.categoryRepository.save(category);
        return this.categoryRepository.findById(newCategory.getId()).isPresent();
    }

    @Override
    public boolean update(Category category, Integer integer) {
        Category oldCategory = this.categoryRepository.getOne(integer);
        Category categoryToTest = new Category(oldCategory.getId(), oldCategory.getName());
        category.setId(integer);
        this.categoryRepository.save(category);
        return !categoryToTest.equals(this.categoryRepository.getOne(integer));
    }

    @Override
    public boolean delete(Integer integer) {
        this.categoryRepository.deleteById(integer);
        return this.categoryRepository.findById(integer).isEmpty();
    }

}
