package pl.coderslab.service;

import pl.coderslab.entity.Institution;

import java.util.List;


public interface InstitutionService {
    List<Institution> findAll();

    Institution findById(Long id);

    void deleteById(Long id);

    void save(Institution institution);
}
