# Use Case: Manage Person

## Overview

**Use Case ID:** UC-002
**Use Case Name:** Manage Person
**Primary Actor:** User
**Goal:** View, create, or update a person's information
**Status:** Draft

## Preconditions

- User has access to the application

## Main Success Scenario (Update Person)

1. User views the list of people (UC-001).
2. User selects a person from the list.
3. System retrieves the person's details from the database.
4. System displays the person's information in an editable form.
5. User modifies the person's information.
6. User saves the changes.
7. System validates the input data.
8. System updates the person record in the database.
9. System displays a success message.

## Alternative Flows

### A1: Create New Person

**Trigger:** User wants to add a new person (step 1)
**Flow:**

1. User clicks the "Add Person" button.
2. System displays an empty person form.
3. User enters the person's information.
4. User saves the new person.
5. System validates the input data.
6. System creates the person record in the database.
7. System displays a success message.
8. Use case ends.

### A2: View Only

**Trigger:** User only wants to view details without editing (step 4)
**Flow:**

1. User views the person's information.
2. User closes the form without making changes.
3. Use case ends.

### A3: Cancel Edit

**Trigger:** User decides not to save changes (step 5 or A1 step 3)
**Flow:**

1. User cancels the operation.
2. System discards any unsaved changes.
3. Use case ends.

### A4: Validation Failure

**Trigger:** Input data fails validation (step 7 or A1 step 5)
**Flow:**

1. System highlights invalid fields with error messages.
2. User corrects the invalid data.
3. Use case continues at step 6 or A1 step 4.

### A5: Person Not Found

**Trigger:** Selected person no longer exists in the database (step 3)
**Flow:**

1. System displays an error message indicating the person was not found.
2. System refreshes the people list.
3. Use case ends.

### A6: System Error

**Trigger:** Database operation fails (step 3, 8, or A1 step 6)
**Flow:**

1. System displays an error message.
2. User data is preserved in the form.
3. Use case ends.

## Postconditions

### Success Postconditions

- For create: New person record exists in the database
- For update: Person record is updated with new information
- For view: No data is modified
- Timestamps (created_at, updated_at) are set appropriately

### Failure Postconditions

- No data is modified in the database
- User sees an appropriate error message

## Business Rules

### BR-001: Required Fields

First name and last name are required and cannot be empty.

### BR-002: Email Format

If provided, email must be in a valid email format.

### BR-003: Field Lengths

- First name: maximum 100 characters
- Last name: maximum 100 characters
- Email: maximum 255 characters
- Phone: maximum 20 characters

### BR-004: Displayed Fields

The form displays all editable person attributes: first name, last name, email, phone, and birth date.

### BR-005: Audit Timestamps

- created_at is set automatically when a person is created
- updated_at is set automatically when a person is modified
