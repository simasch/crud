package ch.martinelli.demo.crud.person;

import ch.martinelli.demo.crud.db.tables.records.PersonRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ch.martinelli.demo.crud.db.tables.Person.PERSON;

@Repository
public class PersonRepository {

    private final DSLContext dsl;

    public PersonRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<PersonRecord> findAll(int offset, int limit) {
        return dsl.selectFrom(PERSON)
                .orderBy(PERSON.LAST_NAME.asc(), PERSON.FIRST_NAME.asc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    public int count() {
        return dsl.fetchCount(PERSON);
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
