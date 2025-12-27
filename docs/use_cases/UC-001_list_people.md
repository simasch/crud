# Use Case: List People

## Overview

**Use Case ID:** UC-001
**Use Case Name:** List People
**Primary Actor:** User
**Goal:** View a list of all people in the system
**Status:** Draft

## Preconditions

- User has access to the application

## Main Success Scenario

1. User navigates to the People list view.
2. System retrieves all people from the database using pagination.
3. System displays a list of people with their basic information.
4. User views the list of people.

## Alternative Flows

### A1: No People Exist

**Trigger:** Database contains no people records (step 2)
**Flow:**

1. System displays an empty list with a message indicating no people exist.
2. Use case ends.

### A2: System Error

**Trigger:** Database connection fails (step 2)
**Flow:**

1. System displays an error message.
2. Use case ends.

## Postconditions

### Success Postconditions

- User sees the list of all people in the system
- List displays relevant information for each person (name, email, phone)

### Failure Postconditions

- User sees an appropriate error message
- No data is modified

## Business Rules

### BR-001: Default Sort Order

People are displayed sorted by last name, then first name in ascending order.

### BR-002: Displayed Fields

The list displays first name, last name, email, and phone for each person.
