## Overview

This application makes calculations based on the user's input. The client connects
to the server and requests to calculate a certain equation. The server returns the
correct answer. The server sends the error message if the equation is impossible to
calculate(i.e division by 0).

## TCP

The server and the client establish connection via Transmission Control Protocol(TCP).
The client establishes the connection. It has to know the IP address
of the server. The server listens on TCP port 1234.
The server closes the connection when the requested command or the error message has
been sent.

## Messages

Once the connection is established the server greets the client with a message:

"Hello. Please enter the numbers and operation you want to do with them."

Then the client enters a desired operation in the form of a string and numbers to calculate.

"ADD 10 20"

The server responds with resulted number.

"30"

If the operation is not possible, like `DIVIDE 10 0` or `SQRT -10` the server returns an error message.

"Impossible operation: <operation>"

## Example dialogs

### Client Functions

- connect()

### Server Functions

- bind()

- listen()

- accept()

### Common Functions

- socket()

- read()

- write()

- add(float num1, float num2)

- subtract(float num1,float num2)

- multiply(float num1,float num2)

- divide(float num1,float num2)

- log(float num, int base)

- sin(float num,boolean isDeg)

- cos(float num,boolean isDeg)

- tan(float num,boolean isDeg)

- sqrt(float num)

- pow(float num,float pow)

- percent(float num)
