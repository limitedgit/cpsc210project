# Building Reservation Manager

## An application that automates the reservations of buildings and the rooms inside

The purpose of the application is to aid in the management of a building that is accessible to the public. A user is able to add rooms to the program and adding bookings of those rooms. The program automatically determines whether a room can be booked or is already booked.

The main targeted audience of the project will be facilities that own buildings that can be reserved by large amounts of people, such as those of a library. The application would be superior to keeping records on paper or typed documents as it will automatically check whether a room is unavailable, improving the efficiency of the task and removing the error of booking a booked/maintenance needed room or building.

I built this project because I was interested in the software that automated the management of records. While the task of record keeping is simple, handling large amounts of data is tiring and invites error when done manually. I think the automation of such tasks is one of the best qualities of modern life, the fact that the click of a mouse has replaced flipping the pages of thick record book. As well, since this kind of software is relatively common, I feel that the amount of available knowledge would help my understanding of modern techniques.

## User Stories
- As a user I want to be able to add rooms to the building
- As a user I want to be able to see a list of the rooms of the building
- As a user I want to be able to book a room if a room is unbooked
- As a user I want to be able to clear the bookings of a room
- As a user I want to be able to  remove rooms from the building in case of a mistake
- As a user I want to able to save my building with rooms and schedule
- As a user I want to able to reload my building from when I last saved it

## Phase 4: Task 2 
- The Map interface was used in the Classes Room and Date
- In the class Room a Hashmap is used to associate a date object with a booking object 
This was so that every Date can be booked or unbooked, and this made it possible to determine if a room could be booked on a certain time/date.
- In the Date class a Hashmap is used to associate a month and a day to create a calendar
This hash map calendar was implemented so that if a room was booked for multiple hours or days, the correct end time could be calculated as it stored the days of each month of that year.



