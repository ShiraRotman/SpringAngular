Elections System Prototype
--------------------------
1. Assumption: The same user will not vote twice or more in parallel, including clicking the Vote button again while a
previous request is processed.
2. Users are identified and represented by their name instead of a number. The names are unique and are used to log in
and vote.
3. Right now there's no indication in the UI that a request to the server failed for any reason. If a request
has returned with a status of 200 but contained no data, a generic error message is displayed. Also, when the
server handles a request and finds it invalid, it just sends back a response with an empty body and not a detailed
error message.
4. The security mechanism has to be improved. Right now it uses basic authentication which sends clear-text credentials
with every request. If the user refreshes the page, the data is lost and (s)he has to log in again. Also, no CSRF
protection is applied. However, the current solution has an advantage in that it doesn't use any session data in the
server-side, and is thus more scalable. Applying a more complex mechanism may require using sessions, so scalability
will have to be taken into account.