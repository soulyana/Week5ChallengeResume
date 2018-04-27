package me.soulyana.demo.Repositories;

import me.soulyana.demo.Models.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education, Long> {
}
