# camel-rest-jetty-auth
Camel Jetty Routes that have basic auth


To run: `mvn package; java -DPORT=8080 -jar target/camel-rest-jetty-auth-1.0-SNAPSHOT.jar`

Then access port 8080 on your machine (not localhost, but the machine name, which is what jetty binds to).

`curl --user ralph:s3cr3t http://compname:8080/hello` succeeds.

If you want to view the routes without basic auth, just comment out the `endpointProperty` value in the camel context.

