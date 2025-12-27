package ch.martinelli.demo.crud.person;

import ch.martinelli.demo.crud.db.tables.records.PersonRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
