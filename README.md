# Webhooks Minone Team Solution:
Team:
- Felipe Menezes Cardoso https://br.linkedin.com/in/felipemcardoso
- Daniel Faria : https://br.linkedin.com/in/daniel-de-faria-monte-alegre-souza-b34382107
- Manoel Messias: https://linkedin.com/in/manoel-menezes-64b241a

Key points:
- HMAC shared secret key for each url/destination.
- RabbitMQ for queue implementation for each url/destination.
- Postgres database for storing destination information.
- REST API (Spring, JAX-RS)
- Single Page Web Application (AngularJS, Bootstrap, HTML 5, CSS 3)
- Firebase just to store and show in the front end informations of attempts to post message in the backend.

# Short Description

- AMPQ: A new AMPQ queue is created for each registered destination. The AMPQ queue has two goals: to ensure the message ordering and provide scalability.

Our solution can be easily deployed on more servers to achieve a better throughput.

- Exponencial BACKOFF: During the message expiration window (24 hours), we retry to send it using an exponential backoff algorithm, ie, after every failure, we increase the time for a new attempt. The purpose of this solution is to reduce the network overhead, since a shorter time attempt would probbaly continue to fail. For demonstration purposes, we limit the expiration window in 2 minutes (the requirement asked for a 24 hours window)..

- HMAC: We also generate a shared secret in order to sign the message content that will be sent to the destination. We are exposing it in the frontend only for testing purposes. Obviously, in a production environment this secret should be shared securely. Our backend checks authenticity o every request.

- PostgreSQL: We use PostgreSQL to store the Registered Destination, but we could also use some key-value NoSQL Database (e.g. Redis) to retrieve the Destination and verify the message signature more quickly.

- FireBase: We use the firebase only to display in the frontend the operations taking place in the backend, especially regarding the attempts failed. Thus, firebase should not be considered as a key point in our solution.

- Frontend: We created the frontend to demonstrate the basic operations of the Rest API and to illustrate, via log, the backoff mechanism we have created. We use AngularJS, Bootstrap, CSS3, Html5 and Firebase client.

Demo video: https://youtu.be/RiM0Cjv8I-4

# A REST API is exposed with five basic operations, according to the requirements:

- @POST /webhooks/post-message :Request the webhook to post a message to the destination

### Params example

```javascript
{
	"destinationId":"0b1ce089-9046-4af4-9de6-01c16558d4bb",
	"contentType":"text/html",
    "content":"<p>message content</p>"	
}
```

### Headers: HMAC of destination secret key and content.


```javascript
{
	"Content-MD5":""    
}
```

### It can be found in the front end web application a javascript code to generate the Content-MD5 header

```javascript
var hmacString = CryptoJS.enc.Base64.stringify(CryptoJS.HmacSHA1(content, secret));
```

- @POST /destination :Creates a new Destination and also create a Queue to support it.

- @GET /destination :Retrieve Destination list

- @DELTE /destination/{destinationId} : Detele a specific destination

- @POST /callback : A dummy end-point just to test a succeciful request

