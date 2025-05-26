# PDI-final-assignment-2025
# Project Functional Checklist: Space Exploration Mission Management System

## File Handling
- [x] Load mission data from CSV on program start
- [x] Write updated data back to CSV after every changes
- [x] Maintain correct CSV format with headers and structured astronaut info

## Mission Management
- [x] View all missions
- [x] View only manned missions
- [x] View only unmanned missions
- [x] Add a new mission with optional astronauts
- [x] Edit existing mission details:
  - [x] Edit mission name
  - [x] Edit mission code
  - [x] Edit destination planet
  - [x] Edit launch year
  - [x] Edit success rate
  - [x] Change manned status
  - [x] Add/edit/remove astronauts depending on manned status
- [x] Input validation when editing missions

## Astronaut Management (per Mission)
- [x] Store up to 5 astronauts per mission
- [x] Add new astronauts with validation:
  - [x] Valid name (non-empty)
  - [x] Valid role (non-empty)
  - [x] Age between 0 and 100
  - [x] Valid nationality
- [x] Edit existing astronaut by name
- [x] Remove astronauts when mission becomes unmanned

## Reporting Features
- [x] Display summary of mission success rates:
  - [x] Average
  - [x] Maximum
  - [x] Minimum
- [x] Search and list astronauts by nationality

## User Interaction
- [x] Text-based menu system in `Main.java`
- [x] Supports all required options (1–9)
- [x] Allows skipping optional inputs with Enter
- [x] Handles empty or invalid inputs gracefully
- [x] Allows user to cancel and return to menu

