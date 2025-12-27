# Use Case: List and Search People

## Overview

**Use Case ID:** UC-001
**Use Case Name:** List and Search People
**Primary Actor:** User
**Goal:** View a list of all people and search for specific people in the system
**Status:** Draft

## Preconditions

- User has access to the application

## Main Success Scenario

1. User navigates to the People list view.
2. System displays a search field and an empty or populated list area.
3. System retrieves all people from the database using pagination.
4. System displays a list of people with their basic information.
5. User views the list of people.

## Alternative Flows

### A1: User Searches for People

**Trigger:** User enters text in the search field (step 2)
**Flow:**

1. User enters a search term in the search field.
2. System filters the list to show only people whose first name, last name, or email contains the search term (case-insensitive).
3. System displays the filtered results with pagination.
4. Use case continues from step 5 of Main Success Scenario.

### A2: No Results Found

**Trigger:** Search returns no matching records (A1 step 2)
**Flow:**

1. System displays an empty list with a message indicating no matching people were found.
2. User can modify the search term or clear the search to see all people.
3. Use case ends or continues from A1 step 1.

### A3: No People Exist

**Trigger:** Database contains no people records (step 3)
**Flow:**

1. System displays an empty list with a message indicating no people exist.
2. Use case ends.

### A4: System Error

**Trigger:** Database connection fails (step 3)
**Flow:**

1. System displays an error message.
2. Use case ends.

## Postconditions

### Success Postconditions

- User sees the list of people (all or filtered by search)
- List displays relevant information for each person (name, email, phone)

### Failure Postconditions

- User sees an appropriate error message
- No data is modified

## Business Rules

### BR-001: Default Sort Order

People are displayed sorted by last name, then first name in ascending order.

### BR-002: Displayed Fields

The list displays first name, last name, email, and phone for each person.

### BR-003: Search Behavior

- Search is case-insensitive
- Search matches partial text (contains matching)
- Search applies to first name, last name, and email fields
- Search results maintain the default sort order
- Empty search field displays all people
