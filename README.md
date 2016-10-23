# Webhooks

# A REST API is exposed with five basic operations, accordingly to the requirements:

1.	 To register a new destination (URL), a POST call must be triggered on ”/destination” resource, passing a “url” object via JSON.
2.	To list all destinations registered a GET call must be triggered on “/destination” resource.
3.	To delete a destination, a DELETE call must be triggered on “/destination/{id}” resource, passing the id (GUID) of the destination.
4.	To post a new message to a specific destination a POST call must be triggered on “/post-message” resource, passing the destination ID, content type and the message content. Besides, a “Content-MD5” header parameter must be set with the HMAC signature.

# The file “webhooks-architecture.png” illustrates our architecture.
