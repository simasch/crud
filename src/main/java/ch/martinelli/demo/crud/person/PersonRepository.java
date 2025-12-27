package ch.martinelli.demo.crud.person;

import ch.martinelli.demo.crud.db.tables.records.PersonRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import org.jooq.Condition;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ch.martinelli.demo.crud.db.tables.Person.PERSON;
import static org.jooq.impl.DSL.noCondition;

@Repository
public class PersonRepository {

    private final DSLContext dsl;

    public PersonRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<PersonRecord> findAll(int offset, int limit) {
        return findAll(offset, limit, null);
    }

    public List<PersonRecord> findAll(int offset, int limit, String searchTerm) {
        return dsl.selectFrom(PERSON)
                .where(buildSearchCondition(searchTerm))
                .orderBy(PERSON.LAST_NAME.asc(), PERSON.FIRST_NAME.asc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    public int count() {
        return count(null);
    }

    public int count(String searchTerm) {
        return dsl.fetchCount(dsl.selectFrom(PERSON).where(buildSearchCondition(searchTerm)));
    }

    private Condition buildSearchCondition(String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return noCondition();
        }
        String pattern = "%" + searchTerm.toLowerCase() + "%";
        return PERSON.FIRST_NAME.likeIgnoreCase(pattern)
                .or(PERSON.LAST_NAME.likeIgnoreCase(pattern))
                .or(PERSON.EMAIL.likeIgnoreCase(pattern));
    }

    public Optional<PersonRecord> findById(Long id) {
        return dsl.selectFrom(PERSON)
                .where(PERSON.ID.eq(id))
                .fetchOptional();
    }

    public void save(PersonRecord person) {
        LocalDateTime now = LocalDateTime.now();
        person.setUpdatedAt(now);

        if (person.getId() == null) {
            person.setCreatedAt(now);
            dsl.insertInto(PERSON)
                    .set(person)
                    .returning()
                    .fetchOne();
        } else {
            dsl.update(PERSON)
                    .set(person)
                    .where(PERSON.ID.eq(person.getId()))
                    .execute();
        }
    }

    public PersonRecord createNew() {
        return dsl.newRecord(PERSON);
    }
}
