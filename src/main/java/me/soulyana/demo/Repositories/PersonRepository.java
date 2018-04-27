package me.soulyana.demo.Repositories;

import me.soulyana.demo.Models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
