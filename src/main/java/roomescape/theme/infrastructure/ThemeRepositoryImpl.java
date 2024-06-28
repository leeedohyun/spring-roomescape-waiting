package roomescape.theme.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import roomescape.theme.domain.Theme;
import roomescape.theme.domain.repository.ThemeRepository;

@Repository
public class ThemeRepositoryImpl implements ThemeRepository {
    
    private final JpaThemeRepository jpaThemeRepository;

    public ThemeRepositoryImpl(JpaThemeRepository jpaThemeRepository) {
        this.jpaThemeRepository = jpaThemeRepository;
    }

    @Override
    public Theme save(Theme theme) {
        return jpaThemeRepository.save(theme);
    }

    @Override
    public Optional<Theme> findById(Long id) {
        return jpaThemeRepository.findById(id);
    }

    @Override
    public List<Theme> findAll() {
        return jpaThemeRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return jpaThemeRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaThemeRepository.deleteById(id);
    }
}
