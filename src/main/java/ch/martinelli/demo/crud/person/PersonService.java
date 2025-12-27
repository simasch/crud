package ch.martinelli.demo.crud.person;

import ch.martinelli.demo.crud.db.tables.records.PersonRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonRecord> findAll(int offset, int limit) {
        return personRepository.findAll(offset, limit);
    }

    public List<PersonRecord> findAll(int offset, int limit, String searchTerm) {
        return personRepository.findAll(offset, limit, searchTerm);
    }

    public Optional<PersonRecord> findById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void save(PersonRecord person) {
        personRepository.save(person);
    }

    public PersonRecord createNew() {
        return personRepository.createNew();
    }
}
