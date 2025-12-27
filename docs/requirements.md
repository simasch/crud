# Requirements Catalog

## Functional Requirements

| ID     | Title         | User Story                                                                                               | Priority | Status |
|--------|---------------|----------------------------------------------------------------------------------------------------------|----------|--------|
| FR-001 | List People   | As a user, I want to view a list of all people so that I can see who is in the system.                   | High     | Open   |
| FR-002 | View Person   | As a user, I want to view the details of a person so that I can see their information.                   | High     | Open   |
| FR-003 | Create Person | As a user, I want to create a new person so that I can add people to the system.                         | High     | Open   |
| FR-004 | Update Person | As a user, I want to update a person's information so that I can keep their data accurate.               | High     | Open   |
| FR-005 | Delete Person | As a user, I want to delete a person so that I can remove people who are no longer needed in the system. | High     | Open   |
| FR-006 | Search People | As a user, I want to search for people by name so that I can quickly find specific individuals.          | Medium   | Open   |

## Non-Functional Requirements

| ID      | Title           | Requirement                                                  | Category    | Priority | Status |
|---------|-----------------|--------------------------------------------------------------|-------------|----------|--------|
| NFR-001 | Response Time   | All page loads must complete within 0.5 seconds.             | Performance | High     | Open   |
| NFR-002 | Data Validation | All user inputs must be validated before persistence.        | Security    | High     | Open   |
| NFR-003 | User Feedback   | The system must provide clear feedback for all user actions. | Usability   | Medium   | Open   |

## Constraints

| ID    | Title             | Constraint                               | Category  | Priority | Status |
|-------|-------------------|------------------------------------------|-----------|----------|--------|
| C-001 | Runtime Platform  | Backend must run on Java 21 LTS.         | Technical | High     | Open   |
| C-002 | Database Platform | System must use PostgreSQL.              | Technical | High     | Open   |
| C-003 | UI Framework      | Frontend must use Vaadin Flow framework. | Technical | High     | Open   |
