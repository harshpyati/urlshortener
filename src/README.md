This is a simple application to shorten urls. 

www.youtube.com -> urlr.com/<random-id>

**Architecture**: 

Currently: 

user requests for a shortened url: 

Flow: 

1. Pseudo Random ID Generation
2. Encoding of the Id generated from step 1
3. Returning the Id to the user

Redirects: 

{{server-ip}}/{id} 

Stack: Java, SpringBoot, Psql