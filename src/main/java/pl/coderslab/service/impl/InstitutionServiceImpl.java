package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Institution;
import pl.coderslab.repository.InstitutionRepository;
import pl.coderslab.service.InstitutionService;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public Institution findById(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public void save(Institution institution) {
        institutionRepository.save(institution);
    }
}
