# System features
An airline reservation system using Csv data

Explantion of this system:
When you boost this system, it will create three months flight data csv.
If users create an account then it will make users data csv.

0. Menu
    - login
      - buy a ticket
      - cancel the flight
      - show account information
      - exit
    - creat a new account
    - exit

1. create an account
    firstname:20 digit limited
    lastname:20 digit limited
    born of date: yyyyMMdd
    email address:20 digit including @ 
    password:8 - 20 digit you need least a upperletter, lowerletter, number, and     symbol (password should be encrypted)
    passport number: 9 digit

2. login
    ask emailaddress and password to login

3. buying a ticket(reservation)
    ask domestic/international flight
    ask departure and arrival
    ask date (between current date to after three months date)
    show boarding times(per hours)
    show all seats (occupied/vacant)
    ask to confirm

4. airlines
    domestic/international flight

    ex)
    domestic flight
    Japan - Tokyo - Japan - Osaka
                          - Hokaido
          - Osaka - Japan - Tokyo
                          - Hokaido
          - Hokaido -
       
    international flight
    Japan - Tokyo - Canada - Vancouver
                           - Calgary
                           - Toronto
                  - US     - NewYork
                           - Boston
                           - LosAngeles
    
5. cancel
    cancel the seat

6. exit
    exit from system